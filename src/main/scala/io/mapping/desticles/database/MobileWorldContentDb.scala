package io.mapping.desticles.database

import io.mapping.desticles.controller.ManifestController
import io.mapping.desticles.model.database.{BaseTableRow, DestinyInventoryItemDefinitions, DestinyInventoryItemDefinition}

trait MobileWorldContent {
	def getInventoryItemDefinitions: List[DestinyInventoryItemDefinition]
}

object MobileWorldContentDb extends BaseDb(ManifestController.getMobileWorldContentDatabasePath) with MobileWorldContent {
	// Figure out how to use generically
	private def getItems[TTable <: MobileWorldContentItemTable[TRow], TRow <: BaseTableRow](tIn: TTable): List[TRow] = {
		database.withSession {
			implicit session => tIn.getItems
		}
	}

	override def getInventoryItemDefinitions: List[DestinyInventoryItemDefinition] = {
		database.withSession {
			implicit session => DestinyInventoryItemDefinitions.getItems
		}
	}
}
