package io.mapping.desticles.database

import io.mapping.desticles.controller.ManifestController
import io.mapping.desticles.database.model.{DestinySandboxPerkDefinitions, DestinyStatDefinitions, DestinyInventoryItemDefinitions}
import io.mapping.desticles.model.{Perk, DbStat, InventoryDefinitionItem}

trait MobileWorldContent {
	def getInventoryItemDefinitions: List[InventoryDefinitionItem]
	def getStatDefinitions: List[DbStat]
	def getPerkDefinitions: List[Perk]
}

object MobileWorldContentDb extends BaseDb(ManifestController.getMobileWorldContentDatabasePath) with MobileWorldContent {
	override def getInventoryItemDefinitions: List[InventoryDefinitionItem] = {
		database.withSession {
			implicit session => DestinyInventoryItemDefinitions.getItems
		}
	}

	override def getStatDefinitions: List[DbStat] = {
		database.withSession {
			implicit session => DestinyStatDefinitions.getItems
		}
	}

	override def getPerkDefinitions: List[Perk] = {
		database.withSession {
			implicit session => DestinySandboxPerkDefinitions.getItems
		}
	}
}
