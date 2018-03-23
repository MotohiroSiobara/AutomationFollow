name := """twitter-bot"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.twitter4j" % "twitter4j-core" % "4.0.5",
  "com.adrianhurt" %% "play-bootstrap" % "1.2-P25-B3-RC2"
)
