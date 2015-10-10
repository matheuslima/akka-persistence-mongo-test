name := "akka-persistence-mongo-test"

version := "1.0"

scalaVersion := "2.11.7"

val akkaVersion = "2.4.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion,
  "org.reactivemongo" %% "reactivemongo" % "0.11.6",
  "org.mongodb" %% "casbah" % "2.8.1",
  "com.github.scullxbones" %% "akka-persistence-mongo-rxmongo" % "1.0.7",
  "com.github.scullxbones" %% "akka-persistence-mongo-casbah" % "1.0.7",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)