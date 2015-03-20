package io.mapping.desticles.database.model

import io.mapping.desticles.database.MobileWorldContentItemProvider
import io.mapping.desticles.model.Perk

import scala.slick.driver.SQLiteDriver
import scala.slick.driver.SQLiteDriver.simple._
import scala.slick.lifted

case class DestinySandboxPerkDefinition(
	                                       id: Long,
	                                       json: String
	                                       ) extends BaseTableRow(id, json)

class DestinySandboxPerkDefinitions(tag: Tag) extends Table[DestinySandboxPerkDefinition](tag, "DestinySandboxPerkDefinition") {
	def id = column[Long]("id", O.PrimaryKey)

	def json = column[String]("json")

	def * = (id, json) <>(DestinySandboxPerkDefinition.tupled, DestinySandboxPerkDefinition.unapply _)
}

object DestinySandboxPerkDefinitions extends MobileWorldContentItemProvider[DestinySandboxPerkDefinitions, DestinySandboxPerkDefinition, Perk] {
	override val items: lifted.TableQuery[DestinySandboxPerkDefinitions] = TableQuery[DestinySandboxPerkDefinitions]

	override def getTableItems(implicit s: SQLiteDriver.simple.Session): List[DestinySandboxPerkDefinition] = items.list

	override def getItems(implicit s: SQLiteDriver.simple.Session): List[Perk] = {
		for (item <- getTableItems) yield readJson(item.json)
	}
}