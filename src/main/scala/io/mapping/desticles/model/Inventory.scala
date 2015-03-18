package io.mapping.desticles.model

case class Inventory(
	                    data: InventoryData,
	                    definitions: Option[InventoryDefinition]
	                    )

case class InventoryData(
	                        buckets: InventoryDataBuckets,
	                        currencies: Array[InventoryDataCurrency]
	                        )

case class InventoryDataBuckets(
	                               Invisible: AnyVal, // null?
	                               Equippable: Array[InventoryDataBucket],
	                               Item: AnyVal, // null?
	                               Currency: AnyVal // null?
	                               )

case class InventoryDataBucket(
                          items: Array[InventoryItem],
                          bucketHash: Long
	                          )

case class InventoryDataCurrency(
	                                itemHash: Long,
	                                value: Long
	                                )

case class InventoryDefinition(
	                              items: Map[String, InventoryDefinitionItem],
	                              buckets: Map[String, InventoryDefinitionBucket],
	                              perks: Map[String, InventoryDefinitionPerk],
	                              talentGrids: Map[String, InventoryDefinitionTalentGrid],
	                              statGroups: Map[String, InventoryDefinitionStatGroup],
	                              progressionMappings: AnyVal, // null?
	                              stats: Map[String, InventoryDefinitionStat],
	                              sources: AnyVal, // null?
	                              progressions: Map[String, InventoryDefinitionProgression],
	                              materialRequirements: AnyVal, // null?
	                              flags: AnyVal // null?
	                              )

case class InventoryDefinitionBucket(
	                                    bucketHash: Long,
	                                    bucketName: String,
	                                    bucketDescription: String,
	                                    scope: Int,
	                                    category: Int,
	                                    bucketOrder: Int,
	                                    bucketIdentifier: String,
	                                    itemCount: Int,
	                                    location: Int,
	                                    hasTransferDestination: Boolean
	                                    )

case class InventoryDefinitionPerk(
	                                  perkHash: Long,
	                                  displayName: String,
	                                  displayDescription: String,
	                                  displayIcon: String,
	                                  isDisplayable: Boolean
	                                  )

case class InventoryDefinitionTalentGrid(
	                                        gridHash: Long,
	                                        maxGridLevel: Long,
	                                        gridLevelPerColumn: Int,
	                                        progessionHash: Long,
	                                        nodes: Array[InventoryDefinitionTalentGridNode],
	                                        calcMaxGridLevel: Long,
	                                        calcProgressToMaxLevel: Long
	                                        )

case class InventoryDefinitionTalentGridNode(
	                                            nodeHash: Long,
	                                            row: Int,
	                                            column: Int,
	                                            prerequisiteNodeIndexes: Option[Array[Int]],
	                                            binaryPairNodeIndex: Int,
	                                            autoUnlocks: Boolean,
	                                            lastStepRepeats: Boolean,
	                                            isRandom: Boolean,
	                                            isRandomRepurchasable: Boolean,
	                                            steps: Array[InventoryDefinitionTalentGridNodeStep],
	                                            exclusiveWithNodes: Option[Array[Long]],
	                                            randomStartProgressionBarAtProgression: Long
	                                            )

case class InventoryDefinitionTalentGridNodeStep(
	                                                nodeStepHash: Long,
	                                                nodeStepName: String,
	                                                nodeStepDescription: String,
	                                                interactionDescription: String,
	                                                icon: String,
	                                                damageType: Int,
	                                                activationRequirement: InventoryDefinitionTalentGridNodeStepActivationRequirement,
	                                                canActivateNextStep: Boolean,
	                                                nextStepIndex: Int,
	                                                isNextStepRandom: Boolean,
	                                                perkHashes: Option[Array[Long]],
	                                                startProgressionBarAtProgress: Long,
	                                                statHashes: Option[Array[Long]],
	                                                affectsQuality: Boolean
	                                                )

case class InventoryDefinitionTalentGridNodeStepActivationRequirement(
	                                                                     gridLevel: Int,
	                                                                     materialRequirementHashes: Array[AnyVal] // null?
	                                                                     )

case class InventoryDefinitionStatGroup(
	                                       statGroupHash: Long,
	                                       maximumValue: Long,
	                                       uiPosition: Int,
	                                       scaledStats: Array[InventoryDefinitionScaledStatGroupStat],
	                                       overrides: Map[String, DisplayableStat]
	                                       )

case class InventoryDefinitionScaledStatGroupStat(
	                                                 statHash: Long,
	                                                 maximumValue: Int,
	                                                 displayAsNumeric: Boolean,
	                                                 displayInterpolation: Array[InventoryDefinitionScaledStatGroupStatInterpolation]
	                                                 )

case class InventoryDefinitionScaledStatGroupStatInterpolation(
	                                                              value: Long,
	                                                              weight: Long
	                                                              )

case class InventoryDefinitionStat(
	                                  statHash: Long,
	                                  statName: String,
	                                  statDescription: String,
	                                  icon: String,
	                                  statIdentifier: String,
	                                  interpolate: Boolean
	                                  )

case class InventoryDefinitionProgression(
	                                         progressionHash: Long,
	                                         name: String,
	                                         scope: Int,
	                                         repeatLastStep: Boolean,
	                                         icon: Option[String],
	                                         steps: Array[InventoryDefinitionProgressionStep]
	                                         )

case class InventoryDefinitionProgressionStep(
	                                             progressTotal: Long
	                                             )