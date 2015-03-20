package io.mapping.desticles.model.database

import io.mapping.desticles.database.MobileWorldContentItemTable

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

object DestinyInventoryItemDefinitions extends MobileWorldContentItemTable[DestinyInventoryItemDefinition] {
	private val inventoryItemDefinitions = TableQuery[DestinyInventoryItemDefinitions]

	override def getItems(implicit s: Session): List[DestinyInventoryItemDefinition] = inventoryItemDefinitions.list
}