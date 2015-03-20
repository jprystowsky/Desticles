package io.mapping.desticles.database

import scala.slick.driver.SQLiteDriver.simple.Session

trait MobileWorldContentItemTable[T] {
	def getItems(implicit s: Session): List[T]
}
