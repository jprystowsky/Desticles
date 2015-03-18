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
                          items: Array[InventoryDataBucketItem],
                          bucketHash: Long
	                          )

case class InventoryDataBucketItem(
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
	                                  progression: ItemProgression,
	                                  talentGridHash: Long,
	                                  nodes: Array[InventoryDataBucketItemNode],
	                                  useCustomDyes: Boolean,
	                                  artRegions: Map[String, Int],
	                                  isEquipment: Boolean,
	                                  isGridComplete: Boolean,
	                                  perks: Array[InventoryDataBucketItemPerk],
	                                  location: Int,
	                                  transferStatus: Int
	                                  )

case class InventoryDataBucketItemNode(
	                                      isActivated: Boolean,
	                                      stepIndex: Int
	                                      )

case class InventoryDataBucketItemPerk(
	                                      iconPath: String,
	                                      perkHash: Long,
	                                      isActive: Boolean
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
	                                  primaryBaseStat: Option[InventoryDefinitionItemBaseStat],
	                                  baseStats: Option[Array[InventoryDefinitionItemBaseStat]],
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

case class InventoryDefinitionItemBaseStat(
	                                          statHash: Long,
	                                          value: Long,
	                                          minimum: Long,
	                                          maximum: Long
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
	                                       overrides: Map[String, InventoryDefinitionStatGroupOverride]
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

case class InventoryDefinitionStatGroupOverride(
	                                               statHash: Long,
	                                               displayName: String,
	                                               displayDescription: String,
	                                               displayIcon: String
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