package io.mapping.desticles.database

import io.mapping.desticles.database.model.BaseTableRow
import org.json4s.DefaultFormats

import scala.slick.driver.SQLiteDriver.simple.Session
import scala.slick.lifted.{AbstractTable, TableQuery}

trait MobileWorldContentItemProvider[TTable <: AbstractTable[_], TRow <: BaseTableRow, TStrongModel] {
	protected implicit val formats = DefaultFormats

	val items: TableQuery[TTable]

	def getTableItems(implicit s: Session): List[TRow]

	def getItems(implicit s: Session): List[TStrongModel]
}
