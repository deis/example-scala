# Scala Quick Start Guide

This guide will walk you through deploying a Scala application on Deis.

## Usage

```
$ deis create
Creating application... done, created proper-sandwich
Git remote deis added
$ git push deis master
Counting objects: 51, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (17/17), done.
Writing objects: 100% (51/51), 7.86 KiB | 0 bytes/s, done.
Total 51 (delta 14), reused 51 (delta 14)
-----> Scala app detected
-----> Installing OpenJDK 1.6...done
-----> Downloading SBT...done
-----> Running: sbt compile stage
       [...]
       [success] Total time: 27 s, completed May 22, 2014 9:53:50 PM
       [info] Wrote start script for mainClass := Some(Web) to /tmp/scala_buildpack_build_dir/target/start
       [success] Total time: 0 s, completed May 22, 2014 9:53:50 PM
-----> Discovering process types
       Procfile declares types -> web
-----> Compiled slug size is 104M
remote: -----> Building Docker image
remote: Uploading context 108.8 MB
remote: Uploading context
remote: Step 0 : FROM deis/slugrunner
remote:  ---> 5567a808891d
remote: Step 1 : RUN mkdir -p /app
remote:  ---> Using cache
remote:  ---> 4096b5c0b838
remote: Step 2 : ADD slug.tgz /app
remote:  ---> b0b779d62105
remote: Removing intermediate container e55e40bff3c0
remote: Step 3 : ENTRYPOINT ["/runner/init"]
remote:  ---> Running in 70b123b22566
remote:  ---> 978e24c796c9
remote: Removing intermediate container 70b123b22566
remote: Successfully built 978e24c796c9
remote: -----> Pushing image to private registry
remote:
remote:        Launching... done, v2
remote:
remote: -----> proper-sandwich deployed to Deis
remote:        http://proper-sandwich.local.deisapp.com
remote:
remote:        To learn more, use `deis help` or visit http://deis.io
remote:
To ssh://git@local.deisapp.com:2222/proper-sandwich.git
 * [new branch]      master -> master
$ curl http://proper-sandwich.local.deisapp.com
Powered by Deis
```

## Additional Resources

* [Get Deis](http://deis.io/get-deis/)
* [GitHub Project](https://github.com/deis/deis)
* [Documentation](http://docs.deis.io/)
* [Blog](http://deis.io/blog/)
