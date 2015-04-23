
enablePlugins(JavaAppPackaging)

name := "example-scala"

version := "1.1"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq("com.twitter" % "finagle-http_2.10" % "6.18.0")
