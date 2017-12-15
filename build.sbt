lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.0.10"
lazy val akkaHttpCirce = "de.heikoseeberger" %% "akka-http-circe" % "1.18.0"
lazy val circeCore = "io.circe" %% "circe-core" % "0.8.0"
lazy val circeGeneric = "io.circe" %% "circe-generic" % "0.8.0"
lazy val wiroHttpServer = "io.buildo" %% "wiro-http-server" % "0.5.2"
lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.4" % "test"
lazy val typesafeConfig = "com.typesafe" % "config" % "1.3.2"
lazy val root = project.in(file("."))
  .settings(
    name := "apiseed",
    scalaVersion := "2.12.4",
    resolvers += Resolver.bintrayRepo("buildo", "maven"),
    libraryDependencies ++= Seq(
      akkaHttp,
      akkaHttpCirce,
      circeCore,
      circeGeneric,
      wiroHttpServer,
      scalatest,
      typesafeConfig
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
  )
