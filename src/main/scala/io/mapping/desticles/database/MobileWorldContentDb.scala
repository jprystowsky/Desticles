package io.mapping.desticles.database

import io.mapping.desticles.controller.ManifestController
import io.mapping.desticles.database.model.DestinyInventoryItemDefinitions
import io.mapping.desticles.model.mobileWorldContent.InventoryItemDefinition

trait MobileWorldContent {
	def getInventoryItemDefinitions: List[InventoryItemDefinition]
}

object MobileWorldContentDb extends BaseDb(ManifestController.getMobileWorldContentDatabasePath) with MobileWorldContent {
	override def getInventoryItemDefinitions: List[InventoryItemDefinition] = {
		database.withSession {
			implicit session => DestinyInventoryItemDefinitions.getItems
		}
	}
}
