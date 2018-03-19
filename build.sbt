name := """twitter-bot"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.twitter4j" % "twitter4j-core" % "4.0.5",
  "org.pac4j" % "play-pac4j" % "3.0.1",    //play用のpac4jラッパー
  "org.pac4j" % "pac4j-oauth" % "1.9.2"         //pac4jのOAuthライブラリ
)
