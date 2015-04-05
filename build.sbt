name := "Trie"

organization := "com.lesbroot"

version := "0.0.1"

scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc()
)

initialCommands := "import com.lesbroot.trie._"

