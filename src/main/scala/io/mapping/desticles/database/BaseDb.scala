package io.mapping.desticles.database

import scala.slick.driver.SQLiteDriver.simple._

class BaseDb(fileName: String) {
	def getDatabaseJdbcString: String = Seq("jdbc", "sqlite", fileName).mkString(":")

	lazy val database = Database.forURL(getDatabaseJdbcString)
}
