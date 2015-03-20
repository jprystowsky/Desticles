package io.mapping.desticles.database.model

import io.mapping.desticles.database.MobileWorldContentItemProvider
import io.mapping.desticles.model.InventoryDefinitionItem

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

object DestinyInventoryItemDefinitions extends MobileWorldContentItemProvider[DestinyInventoryItemDefinitions, DestinyInventoryItemDefinition, InventoryDefinitionItem] {
	override val items = TableQuery[DestinyInventoryItemDefinitions]

	override def getTableItems(implicit s: Session): List[DestinyInventoryItemDefinition] = items.list

	override def getItems(implicit s: Session): List[InventoryDefinitionItem] = {
		for (item <- getTableItems) yield readJson(item.json)
	}
}