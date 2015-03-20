name := "Desticles"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
	"org.scalaj" %% "scalaj-http" % "1.1.4",
	"org.json4s" %% "json4s-native" % "3.2.11",
	"com.typesafe" % "config" % "1.2.1",
	"commons-io" % "commons-io" % "2.4",
	"net.lingala.zip4j" % "zip4j" % "1.3.2",
	"com.typesafe.slick" %% "slick" % "2.1.0",
	"org.slf4j" % "slf4j-nop" % "1.7.10",
	"org.xerial" % "sqlite-jdbc" % "3.8.7"
)