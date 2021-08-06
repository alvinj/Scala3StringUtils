name := "StringUtils"
version := "0.1"
scalaVersion := "3.0.1"

libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % "latest.integration",
    "org.scalatest" %% "scalatest" % "latest.integration" % "test",
    "org.scalacheck" %% "scalacheck" % "1.15.4"
    // "org.scalactic" %% "scalactic" % "3.2.9",
    // "org.scalatest" %% "scalatest" % "3.2.9" % "test",
    // "org.scalacheck" %% "scalacheck" % "1.15.4"
)

// see https://tpolecat.github.io/2017/04/25/scalac-flags.html for scalacOptions descriptions
scalacOptions ++= Seq(
    "-deprecation",
    "-explain",
    "-explain-types",
    "-new-syntax",
    "-unchecked",
    "-Xfatal-warnings",
    // "-Xmigration"
)
