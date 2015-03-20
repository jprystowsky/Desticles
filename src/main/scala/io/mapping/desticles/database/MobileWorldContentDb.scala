package io.mapping.desticles.database

import io.mapping.desticles.controller.ManifestController
import io.mapping.desticles.database.model.{DestinyInventoryItemDefinition, DestinyInventoryItemDefinitions}

trait MobileWorldContent {
	def getInventoryItemDefinitions: List[DestinyInventoryItemDefinition]
}

object MobileWorldContentDb extends BaseDb(ManifestController.getMobileWorldContentDatabasePath) with MobileWorldContent {
	override def getInventoryItemDefinitions: List[DestinyInventoryItemDefinition] = {
		database.withSession {
			implicit session => DestinyInventoryItemDefinitions.getTableItems
		}
	}
}
