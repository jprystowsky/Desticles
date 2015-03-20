package io.mapping.desticles.database.model

import io.mapping.desticles.database.MobileWorldContentItemProvider
import io.mapping.desticles.model.mobileWorldContent.InventoryItemDefinition
import org.json4s.DefaultFormats

import scala.slick.driver.SQLiteDriver.simple._

import org.json4s.native.Serialization.read

case class DestinyInventoryItemDefinition(
                                         id: Long,
                                         json: String
	                                         ) extends BaseTableRow(id, json)

class DestinyInventoryItemDefinitions(tag: Tag) extends Table[DestinyInventoryItemDefinition](tag, "DestinyInventoryItemDefinition") {
	def id = column[Long]("id", O.PrimaryKey)
	def json = column[String]("json")
	def * = (id, json) <> (DestinyInventoryItemDefinition.tupled, DestinyInventoryItemDefinition.unapply _)
}

object DestinyInventoryItemDefinitions extends MobileWorldContentItemProvider[DestinyInventoryItemDefinitions, DestinyInventoryItemDefinition, InventoryItemDefinition] {
	override val items = TableQuery[DestinyInventoryItemDefinitions]

	override def getTableItems(implicit s: Session): List[DestinyInventoryItemDefinition] = items.list

	override def getItems(implicit s: Session): List[InventoryItemDefinition] = {
		for (item <- getTableItems) yield read[InventoryItemDefinition](item.json)
	}
}