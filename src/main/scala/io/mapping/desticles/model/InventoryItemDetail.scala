package io.mapping.desticles.model

case class InventoryItemDetail(
	                              data: InventoryItemDetailData,
	                              definitions: Option[Map[String, InventoryDefinitionItem]]
	                              )

case class InventoryItemDetailData(
	                                  item: InventoryItem,
	                                  talentNodes: Array[InventoryItemDetailDataNode],
	                                  statsOnNodes: Map[String, Option[InventoryItemDetailDataStatsOnNode]],
	                                  characterStatsToDisplay: AnyVal, // null?
	                                  materialItemHashes: Option[Array[Long]]
	                                  )

case class InventoryItemDetailDataNode(
	                                      nodeIndex: Int,
	                                      nodeHash: Long,
	                                      state: Int,
	                                      stateId: String,
	                                      isActivated: Boolean,
	                                      stepIndex: Int,
	                                      materialsToUpgrade: Option[Array[InventoryItemDetailDataNodeMaterial]],
	                                      activationGridLevel: Int,
	                                      progressPercent: Float
	                                      )

case class InventoryItemDetailDataNodeMaterial(
	                                              itemHash: Long,
	                                              deleteOnAction: Boolean,
	                                              count: Long,
	                                              omitFromRequirements: Boolean
	                                              )

case class InventoryItemDetailDataStatsOnNode(
	                                             currentNodeStats: Option[Array[UpperBoundedStat]],
	                                             nextNodeStats: Option[Array[UpperBoundedStat]]
	                                             )