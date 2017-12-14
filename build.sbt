lazy val root = project.in(file("."))
  .settings(
    name := "apiseed",
    scalaVersion := "2.12.4",
    resolvers += Resolver.bintrayRepo("buildo", "maven"),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.0.10",
      "de.heikoseeberger" %% "akka-http-circe" % "1.18.0",
      "io.circe" %% "circe-core" % "0.8.0",
      "io.circe" %% "circe-generic" % "0.8.0",
      "io.buildo" %% "wiro-http-server" % "0.5.2",
      "org.scalatest" %% "scalatest" % "3.0.4" % "test"
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
  )
