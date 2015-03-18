package io.mapping.desticles.model

case class InventoryItem(
	                        itemHash: Long,
	                        bindStatus: Int,
	                        isEquipped: Boolean,
	                        itemInstanceId: String,
	                        itemLevel: Long,
	                        stackSize: Int,
	                        qualityLevel: Long,
	                        stats: Array[UpperBoundedStat],
	                        canEquip: Boolean,
	                        equipRequirementLevel: Option[Long],
	                        unlockFlagHashRequiredToEquip: Long,
	                        cannotEquipReason: Int,
	                        damageType: Int,
	                        damageTypeNodeIndex: Int,
	                        damageTypeStepIndex: Int,
	                        progression: Progression,
	                        talentGridHash: Long,
	                        nodes: Array[InventoryItemNode],
	                        useCustomDyes: Boolean,
	                        artRegions: Map[String, Int],
	                        isEquipment: Boolean,
	                        isGridComplete: Boolean,
	                        perks: Array[InventoryItemPerk],
	                        location: Int,
	                        transferStatus: Int
	                        )

case class InventoryItemNode(
	                            isActivated: Boolean,
	                            stepIndex: Int
	                            )

case class InventoryItemPerk(
	                            iconPath: String,
	                            perkHash: Long,
	                            isActive: Boolean
	                            )