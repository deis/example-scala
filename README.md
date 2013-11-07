# Scala Quick Start Guide

This guide will walk you through deploying a Scala application on Deis.

## Prerequisites

* A [User Account](http://docs.deis.io/en/latest/client/register/) on a [Deis Controller](http://docs.deis.io/en/latest/terms/controller/).
* A [Deis Formation](http://docs.deis.io/en/latest/gettingstarted/concepts/#formations) that is ready to host applications

If you do not yet have a controller or a Deis formation, please review the [Deis installation](http://docs.deis.io/en/latest/installation/) instructions.

## Setup your workstation

* Install [RubyGems](http://rubygems.org/pages/download) to get the `gem` command on your workstation
* Install [Foreman](http://ddollar.github.com/foreman/) with `gem install foreman`
* Install [sbt](http://www.scala-sbt.org/) with `brew install sbt`

## Clone your Application

If you want to use an existing application, no problem.  You can also use the Deis sample application located at <https://github.com/opdemand/example-scala-sbt>.  Clone the example application to your local workstation:

    $ git clone https://github.com/opdemand/example-scala-sbt.git
    $ cd example-scala-sbt

## Prepare your Application

To use a Scala application with Deis, you will need to conform to 3 basic requirements:

 1. Use [sbt](http://www.scala-sbt.org/) to manage application detection and dependencies
 2. Use [Foreman](http://ddollar.github.com/foreman/) to manage processes
 3. Use [Environment Variables](https://help.ubuntu.com/community/EnvironmentVariables) to manage configuration inside your application

If you're deploying the example application, it already conforms to these requirements.

#### 1. Use sbt to manage application detection and dependencies

Deis identifies your app as being a [Scala](http://www.scala-lang.org/) app by the existence of `project/build.properties`, which should contain the correct version of Scala you want to use:

	sbt.version=0.12.0

`sbt` requires that you explicitly declare your dependencies using a `build.sbt` file in the root directory of your project. Here is a very basic example:

	import com.typesafe.startscript.StartScriptPlugin
	
	seq(StartScriptPlugin.startScriptForClassesSettings: _*)
	
	name := "hello"
	
	version := "1.0"
	
	scalaVersion := "2.9.2"
	
	resolvers += "twitter-repo" at "http://maven.twttr.com"
	
	libraryDependencies ++= Seq("com.twitter" % "finagle-core" % "1.9.0", "com.twitter" % "finagle-http" % "1.9.0")
    
You can then use `sbt clean compile stage` to build your application.

The `xbst-start-script-plugin` by Typesafe appends a stage task to `sbt` that generates start scripts for your app. To ensure that the plugin works, create this file in `project/build.sbt`

	resolvers += Classpaths.typesafeResolver
	
	addSbtPlugin("com.typesafe.startscript" % "xsbt-start-script-plugin" % "0.5.3")

#### 2. Use Foreman to manage processes

Deis relies on a [Foreman](http://ddollar.github.com/foreman/) `Procfile` that lives in the root of your repository.  This is where you define the command(s) used to run your application.  Here is an example `Procfile`:

    web: target/start Web

This tells Deis to run `web` workers using the command `target/start Web`. You can test this locally by running `foreman start`.

	$ foreman start
	12:43:49 web.1  | started with pid 50822
	12:43:50 web.1  | Starting on port:5000
	12:43:50 web.1  | Started.

You should now be able to access your application locally at <http://localhost:5000>.

#### 3. Use Environment Variables to manage configuration

Deis uses environment variables to manage your application's configuration. For example, your application listener must use the value of the `PORT` environment variable. The following code snippet demonstrates how this can work inside your application:

    val port = Properties.envOrElse("PORT", "8080").toInt

## Create a new Application

Per the prerequisites, we assume you have access to an existing Deis formation. If not, please review the Deis [installation instuctions](http://docs.deis.io/en/latest/gettingstarted/installation/).

Use the following command to create an application on an existing Deis formation.

    $ deis create --formation=<formationName> --id=<appName>
	Creating application... done, created <appName>
	Git remote deis added
    
If an ID is not provided, one will be auto-generated for you.

## Deploy your Application

Use `git push deis master` to deploy your application.

	$ git push deis master
	Delta compression using up to 4 threads.
	Compressing objects: 100% (3/3), done.
	Writing objects: 100% (6/6), 486 bytes, done.
	Total 6 (delta 2), reused 0 (delta 0)
	       Scala app detected
	-----> Installing OpenJDK 1.6...

Once your application has been deployed, use `deis open` to view it in a browser. To find out more info about your application, use `deis info`.

## Scale your Application

To scale your application's [Docker](http://docker.io) containers, use `deis scale` and specify the number of containers for each process type defined in your application's `Procfile`. For example, `deis scale web=8`.

	$ deis scale web=8
	Scaling containers... but first, coffee!
	done in 19s
	
	=== <appName> Containers
	
	--- web: `target/start Web`
	web.1 up 2013-10-28T18:06:19.065Z (scalaFormation-runtime-1)
	web.2 up 2013-10-30T18:50:04.192Z (scalaFormation-runtime-1)
	web.3 up 2013-10-30T18:50:04.210Z (scalaFormation-runtime-1)
	web.4 up 2013-10-30T18:50:04.225Z (scalaFormation-runtime-1)
	web.5 up 2013-10-30T18:50:04.241Z (scalaFormation-runtime-1)
	web.6 up 2013-10-30T18:50:04.258Z (scalaFormation-runtime-1)
	web.7 up 2013-10-30T18:50:04.277Z (scalaFormation-runtime-1)
	web.8 up 2013-10-30T18:50:04.297Z (scalaFormation-runtime-1)


## Configure your Application

Deis applications are configured using environment variables. The example application includes a special `POWERED_BY` variable to help demonstrate how you would provide application-level configuration. 

	$ curl -s http://yourapp.yourformation.com
	Powered by Deis
	$ deis config:set POWERED_BY=Scala
	=== <appName>
	POWERED_BY: Scala
	$ curl -s http://yourapp.yourformation.com
	Powered by Scala

`deis config:set` is also how you connect your application to backing services like databases, queues and caches. You can use `deis run` to execute one-off commands against your application for things like database administration, initial application setup and inspecting your container environment.

	$ deis run ls -la
	total 56
	drwxr-xr-x  9 root root 4096 Oct 30 18:48 .
	drwxr-xr-x 57 root root 4096 Oct 30 18:52 ..
	-rw-r--r--  1 root root   58 Oct 30 18:47 .gitignore
	drwxr-xr-x  3 root root 4096 Oct 30 18:47 .ivy2
	drwxr-xr-x  6 root root 4096 Oct 30 18:47 .jdk
	drwxr-xr-x  2 root root 4096 Oct 30 18:48 .profile.d
	-rw-r--r--  1 root root  252 Oct 30 18:48 .release
	drwxr-xr-x  5 root root 4096 Oct 30 18:47 .sbt_home
	-rw-r--r--  1 root root   21 Oct 30 18:47 Procfile
	-rw-r--r--  1 root root  337 Oct 30 18:47 build.sbt
	drwxr-xr-x  5 root root 4096 Oct 30 18:48 project
	drwxr-xr-x  3 root root 4096 Oct 30 18:47 src
	-rw-r--r--  1 root root   25 Oct 30 18:47 system.properties
	drwxr-xr-x  4 root root 4096 Oct 30 18:48 target

## Troubleshoot your Application

To view your application's log output, including any errors or stack traces, use `deis logs`.

    $ deis logs
	Oct 28 18:06:35 ip-172-31-19-251 kindly-idealist[web.1]: Starting on port:10001
	Oct 28 18:06:35 ip-172-31-19-251 kindly-idealist[web.1]: Started.
	Oct 29 15:16:18 ip-172-31-19-251 kindly-idealist[web.1]: Starting on port:10001
	Oct 29 15:16:18 ip-172-31-19-251 kindly-idealist[web.1]: Started.

## Additional Resources

* [Get Deis](http://deis.io/get-deis/)
* [GitHub Project](https://github.com/opdemand/deis)
* [Documentation](http://docs.deis.io/)
* [Blog](http://deis.io/blog/)
