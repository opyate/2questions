name := "query-rdf"

version := "0.1"

scalaVersion := "2.11.2"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8",
  "-feature"
)

resolvers += "spray" at "http://repo.spray.io/"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.2"

libraryDependencies += "junit" % "junit" % "4.11"

libraryDependencies += "io.spray" %% "spray-client" % "1.3.2-20140909"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.5"

libraryDependencies += "io.spray" %% "spray-json" % "1.2.6"

libraryDependencies += "org.scalactic" % "scalactic_2.11" % "2.2.2"

libraryDependencies += "joda-time" % "joda-time" % "2.4"

libraryDependencies += "org.joda" % "joda-convert" % "1.7"

