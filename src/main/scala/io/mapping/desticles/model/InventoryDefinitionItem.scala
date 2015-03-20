package io.mapping.desticles.model

case class InventoryDefinitionItem(
	                                  itemHash: Long,
	                                  itemName: Option[String],
	                                  itemDescription: Option[String],
	                                  icon: String,
	                                  secondaryIcon: String,
	                                  actionName: Option[String],
	                                  actionDescription: Option[String],
	                                  tierTypeName: Option[String],
	                                  tierType: Int,
	                                  itemTypeName: Option[String],
	                                  bucketTypeHash: Long,
	                                  primaryBaseStatHash: Long,
	                                  stats: Map[String, FullyBoundedStat],
	                                  primaryBaseStat: Option[FullyBoundedStat],
	                                  baseStats: Option[Array[FullyBoundedStat]],
	                                  perkHashes: Option[Array[Long]],
	                                  itemIdentifier: Option[String],
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
	                                  exclusive: Int,
	                                  maxStackSize: Option[Int]
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