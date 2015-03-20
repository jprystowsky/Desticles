package io.mapping.desticles.database.model

import io.mapping.desticles.database.MobileWorldContentItemProvider

import scala.slick.driver.SQLiteDriver.simple._

case class DestinyInventoryItemDefinition(
                                         id: Long,
                                         json: String
	                                         ) extends BaseTableRow(id, json)

class DestinyInventoryItemDefinitions(tag: Tag) extends Table[DestinyInventoryItemDefinition](tag, "DestinyInventoryItemDefinition") {
	def id = column[Long]("id", O.PrimaryKey)
	def json = column[String]("json")
	def * = (id, json) <> (DestinyInventoryItemDefinition.tupled, DestinyInventoryItemDefinition.unapply _)
}

object DestinyInventoryItemDefinitions extends MobileWorldContentItemProvider[DestinyInventoryItemDefinitions, DestinyInventoryItemDefinition] {
	override val items = TableQuery[DestinyInventoryItemDefinitions]

	override def getTableItems(implicit s: Session): List[DestinyInventoryItemDefinition] = items.list
}