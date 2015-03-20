package io.mapping.desticles.database

import io.mapping.desticles.database.model.BaseTableRow
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.read

import scala.slick.driver.SQLiteDriver.simple.Session
import scala.slick.lifted.{AbstractTable, TableQuery}

trait MobileWorldContentItemProvider[TTable <: AbstractTable[_], TRow <: BaseTableRow, TStrongModel] {
	protected implicit val formats = DefaultFormats

	val items: TableQuery[TTable]

	def getTableItems(implicit s: Session): List[TRow]

	def getItems(implicit s: Session): List[TStrongModel]

	protected def readJson(s: String)(implicit m: Manifest[TStrongModel]) = read[TStrongModel](s)
}
