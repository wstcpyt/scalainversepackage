import _root_.sbtassembly.Plugin.AssemblyKeys._
import _root_.sbtassembly.Plugin.MergeStrategy
import _root_.sbtassembly.Plugin._

name := "scalainversepackage"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies  ++= Seq(
  // other dependencies here
  "org.scalanlp" %% "breeze" % "0.11.2",
  // native libraries are not included by default. add this if you want them (as of 0.7)
  // native libraries greatly improve performance, but increase jar sizes.
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % "0.11.2",
  // the visualization library is distributed separately as well.
  // It depends on LGPL code.
  "org.scalanlp" %% "breeze-viz" % "0.11.2",
  //scala test dependency
  "org.scalatest" % "scalatest_2.10" % "2.1.3" % "test",
  //Apache spark
  "org.apache.spark" %% "spark-core" % "1.5.1" % "provided"
)


assemblySettings
mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}
