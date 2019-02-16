
version := "1.0"

ThisBuild / scalaVersion := "2.12.8"


mainClass in (Compile,run) :=  Some("test.Main")

fork in run := true
javaOptions in run  ++= Seq( "-Xms3g", "-Xmx5g", "-Xss3096k")

lazy val chessTest = (project in file("."))
  .settings(
    name := "chess-proj",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )