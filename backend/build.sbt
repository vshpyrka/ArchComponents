name := "backend"
 
version := "1.0" 
      
lazy val `backend` = (project in file(".")).enablePlugins(PlayJava)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  javaJdbc ,
  evolutions,
  ehcache ,
  javaWs ,
  guice ,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.jooq" % "jooq" % "3.10.6",
  "org.jooq" % "jooq-meta" % "3.10.6",
  "org.jooq" % "jooq-codegen" % "3.10.6",
  "org.hibernate" % "hibernate-core" % "5.2.5.Final"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )


lazy val codegen = taskKey[Unit]("Generate jooq files")

codegen := {
  (runMain in Compile).toTask(" org.jooq.util.GenerationTool jooq/jooq-config.xml").value
}
