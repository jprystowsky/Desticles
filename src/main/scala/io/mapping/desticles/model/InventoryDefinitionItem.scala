package io.mapping.desticles.model

case class InventoryDefinitionItem(
	                                  itemHash: Long,
	                                  itemName: String,
	                                  itemDescription: String,
	                                  icon: String,
	                                  secondaryIcon: String,
	                                  actionName: String,
	                                  actionDescription: String,
	                                  tierTypeName: String,
	                                  tierType: Int,
	                                  itemTypeName: String,
	                                  bucketTypeHash: Long,
	                                  primaryBaseStat: Option[FullyBoundedStat],
	                                  baseStats: Option[Array[FullyBoundedStat]],
	                                  perkHashes: Option[Array[Long]],
	                                  itemIdentifier: String,
	                                  specialItemType: Int,
	                                  talentGridHash: Long,
	                                  equippingBlock: Option[InventoryDefinitionItemEquippingBlock],
	                                  hasGeometry: Boolean,
	                                  statGroupHash: Int,
	                                  itemLevels: Option[Array[Long]],
	                                  qualityLevel: Int,
	                                  rewardItemHash: Int,
	                                  values: AnyVal,
	                                  itemType: Int,
	                                  itemSubType: Int,
	                                  classType: Int,
	                                  nonTransferrable: Boolean,
	                                  exclusive: Int
	                                  )

case class InventoryDefinitionItemEquippingBlock(
	                                                weaponSandboxPatternIndex: Int,
	                                                gearArtArrangementIndex: Int,
	                                                defaultDyes: Option[Array[ItemDye]],
	                                                lockedDyes: Option[Array[ItemDye]],
	                                                customDyes: Option[Array[ItemDye]],
	                                                customDyeExpression: InventoryDefinitionItemEquippingBlockCustomDyeExpression,
	                                                weaponPatternHash: Long
	                                                )

case class InventoryDefinitionItemEquippingBlockCustomDyeExpression(
	                                                                   steps: AnyVal
	                                                                   )