package io.mapping.desticles.database

import io.mapping.desticles.controller.ManifestController
import io.mapping.desticles.database.model.DestinyInventoryItemDefinitions
import io.mapping.desticles.model.InventoryDefinitionItem

trait MobileWorldContent {
	def getInventoryItemDefinitions: List[InventoryDefinitionItem]
}

object MobileWorldContentDb extends BaseDb(ManifestController.getMobileWorldContentDatabasePath) with MobileWorldContent {
	override def getInventoryItemDefinitions: List[InventoryDefinitionItem] = {
		database.withSession {
			implicit session => DestinyInventoryItemDefinitions.getItems
		}
	}
}
