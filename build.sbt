name := "play-upload"

version := "1.0"

lazy val `play-upload` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq( cache , ws, filters )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  