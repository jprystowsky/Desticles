package io.mapping.desticles.database

import io.mapping.desticles.controller.ManifestController
import io.mapping.desticles.database.model.{DestinyInventoryItemDefinitions, DestinyInventoryItemDefinition}
import io.mapping.desticles.model.database.DestinyInventoryItemDefinition

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
