import sbt.Keys.scalacOptions
import sbt.librarymanagement.ModuleID

ThisBuild / scalaVersion := "3.4.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "org.bargsten"
ThisBuild / organizationName := "Joachim Bargsten"

val scalacOptionsNoWarnings: List[String] = List(
  "-encoding",
  "utf8",
  "-feature",
  "-nowarn"
)

lazy val xmlDependencies = Seq(
  "org.dispatchhttp" % "dispatch-core_2.13" % "1.2.0" excludeAll ExclusionRule(organization = "org.scala-lang.modules"), // scala-steward:off
  "javax.xml.bind" % "jaxb-api" % "2.3.1",
  "org.scala-lang.modules" %% "scala-xml" % "2.2.0",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0"
)

lazy val root = (project in file("."))
  .enablePlugins(ScalaxbPlugin)
  .settings(
    name := "xml-zio-buff-golems",
    Compile / scalaxb / scalaxbPackageName := "org.bargsten.xb.generated",
    Compile / scalaxb / scalaxbXsdSource := (Compile / resourceDirectory).value / "xsd",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.21",
      "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0",
      "dev.zio" %% "zio-test" % "2.0.21" % Test
    ) ++ xmlDependencies,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    scalacOptions := scalacOptionsNoWarnings
  )
