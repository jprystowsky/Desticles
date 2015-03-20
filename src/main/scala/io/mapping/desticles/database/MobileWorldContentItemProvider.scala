package io.mapping.desticles.database

import scala.slick.driver.SQLiteDriver.simple.Session
import scala.slick.lifted.{AbstractTable, TableQuery}

trait MobileWorldContentItemProvider[TTable <: AbstractTable[_], TRow] {
	val items: TableQuery[TTable]

	def getTableItems(implicit s: Session): List[TRow]
}
