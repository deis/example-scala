import com.typesafe.sbt.SbtNativePackager._

packageArchetype.java_application
name := "example-scala"
organization := "com.example"
version := "2.0.0"
scalaVersion := "2.11.8"
fork in run := true
parallelExecution in ThisBuild := false

lazy val versions = new {
  val finatra = "2.3.0"
  val logback = "1.1.7"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

assemblyMergeStrategy in assembly := {
  case "BUILD" => MergeStrategy.discard
  case "META-INF/io.netty.versions.properties" => MergeStrategy.last
  case other => MergeStrategy.defaultMergeStrategy(other)
}

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % versions.finatra,
  "ch.qos.logback" % "logback-classic" % versions.logback
)
