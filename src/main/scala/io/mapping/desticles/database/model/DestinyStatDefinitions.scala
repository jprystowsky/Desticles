package io.mapping.desticles.database.model

import io.mapping.desticles.database.MobileWorldContentItemProvider
import io.mapping.desticles.model.DbStat

import scala.slick.driver.SQLiteDriver
import scala.slick.driver.SQLiteDriver.simple._
import scala.slick.lifted

case class DestinyStatDefinition(
	                                id: Long,
	                                json: String
	                                ) extends BaseTableRow(id, json)

class DestinyStatDefinitions(tag: Tag) extends Table[DestinyStatDefinition](tag, "DestinyStatDefinition") {
	def id = column[Long]("id", O.PrimaryKey)

	def json = column[String]("json")

	def * = (id, json) <>(DestinyStatDefinition.tupled, DestinyStatDefinition.unapply _)
}

object DestinyStatDefinitions extends MobileWorldContentItemProvider[DestinyStatDefinitions, DestinyStatDefinition, DbStat] {
	override val items: lifted.TableQuery[DestinyStatDefinitions] = TableQuery[DestinyStatDefinitions]

	override def getTableItems(implicit s: SQLiteDriver.simple.Session): List[DestinyStatDefinition] = items.list

	override def getItems(implicit s: SQLiteDriver.simple.Session): List[DbStat] = {
		for (item <- getTableItems) yield readJson(item.json)
	}
}
