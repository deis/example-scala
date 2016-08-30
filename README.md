# Scala Quick Start Guide

This guide will walk you through deploying a Scala application on [Deis Workflow][].

## Usage

```console
$ git clone https://github.com/deis/example-scala.git
$ cd example-scala
$ deis create
Creating Application... done, created honest-knapsack
Git remote deis added
remote available at ssh://git@deis-builder.deis.rocks:2222/honest-knapsack.git
$ git push deis master
Counting objects: 77, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (28/28), done.
Writing objects: 100% (77/77), 11.15 KiB | 0 bytes/s, done.
Total 77 (delta 21), reused 76 (delta 20)
Starting build... but first, coffee!
-----> Scala app detected
-----> Installing OpenJDK 1.8... done
-----> Priming Ivy cache... done
-----> Running: sbt compile stage
Downloading sbt launcher for 0.13.12:
  From  http://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/0.13.12/sbt-launch.jar
    To  /tmp/scala_buildpack_build_dir/.sbt_home/launchers/0.13.12/sbt-launch.jar
...
[info] Compiling 2 Scala sources to /tmp/scala_buildpack_build_dir/target/scala-2.11/classes...
[info] 'compiler-interface' not yet compiled for Scala 2.11.8. Compiling...
[info]   Compilation completed in 21.773 s
[success] Total time: 58 s, completed Aug 31, 2016 6:58:35 PM
[info] Wrote /tmp/scala_buildpack_build_dir/target/scala-2.11/example-scala_2.11-2.0.0.pom
[info] Packaging /tmp/scala_buildpack_build_dir/target/scala-2.11/example-scala_2.11-2.0.0.jar ...
[info] Done packaging.
[success] Total time: 1 s, completed Aug 31, 2016 6:58:36 PM
-----> Dropping ivy cache from the slug
-----> Dropping sbt boot dir from the slug
-----> Dropping compilation artifacts from the slug
-----> Discovering process types
Procfile declares types -> web
-----> Compiled slug size is 179M
Build complete.
Launching App...
Done, honest-knapsack:v2 deployed to Workflow

Use 'deis open' to view this application in your browser

To learn more, use 'deis help' or visit https://deis.com/

To ssh://git@deis-builder.deis.rocks:2222/honest-knapsack.git
 * [new branch]      master -> master
$ curl http://honest-knapsack.deis.rocks
Powered by Deis
Release v2 on honest-knapsac-web-1501393759-4769j

```

## Additional Resources

* [GitHub Project](https://github.com/deis/workflow)
* [Documentation](https://deis.com/docs/workflow/)
* [Blog](https://deis.com/blog/)

[Deis Workflow]: https://github.com/deis/workflow#readme
