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
-----> Installing OpenJDK 1.7... done
-----> Priming Ivy cache... done
-----> Running: sbt compile stage
Downloading sbt launcher for 0.13.8:
  From  http://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/0.13.8/sbt-launch.jar
    To  /tmp/scala_buildpack_build_dir/.sbt_home/launchers/0.13.8/sbt-launch.jar
       [info] Loading global plugins from /tmp/scala_buildpack_build_dir/.sbt_home/plugins
       [info] Updating {file:/tmp/scala_buildpack_build_dir/.sbt_home/plugins/}global-plugins...
       [info] Resolving org.scala-lang#scala-library;2.10.4 ...
       [info] Resolving org.scala-sbt#sbt;0.13.8 ...
...
       [info] Compiling 1 Scala source to /tmp/scala_buildpack_build_dir/target/scala-2.10/classes...
       [success] Total time: 6 s, completed Jun 9, 2016 8:01:58 PM
       [info] Wrote /tmp/scala_buildpack_build_dir/target/scala-2.10/example-scala_2.10-1.1.pom
       [info] Packaging /tmp/scala_buildpack_build_dir/target/scala-2.10/example-scala_2.10-1.1.jar ...
       [info] Done packaging.
       [success] Total time: 1 s, completed Jun 9, 2016 8:01:59 PM
-----> Dropping ivy cache from the slug
-----> Dropping sbt boot dir from the slug
-----> Dropping compilation artifacts from the slug
-----> Discovering process types
       Procfile declares types -> web
-----> Compiled slug size is 78M
Build complete.
Launching App...
Done, honest-knapsack:v2 deployed to Deis

Use 'deis open' to view this application in your browser

To learn more, use 'deis help' or visit https://deis.com/

To ssh://git@deis-builder.deis.rocks:2222/honest-knapsack.git
 * [new branch]      master -> master
$ curl http://honest-knapsack.deis.rocks
Powered by Deis
```

## Additional Resources

* [GitHub Project](https://github.com/deis/workflow)
* [Documentation](https://deis.com/docs/workflow/)
* [Blog](https://deis.com/blog/)

[Deis Workflow]: https://github.com/deis/workflow#readme
