name := """scalac-chess-test"""

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

scalacOptions += Seq("-optimise")
mainClass in (Compile,run) :=  Some("Main")

fork in run := true
javaOptions in run  ++= Seq( "-Xms3g", "-Xmx5g", "-Xss3096k")