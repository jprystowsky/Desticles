package io.mapping.desticles.model

case class CharacterActivities(
	                              data: CharacterActivitiesData,
	                              definitions: CharacterActivitiesDefinitions
	                              )

case class CharacterActivitiesData(
	                                  dateActivityStarted: java.util.Date,
	                                  available: Option[Array[CharacterActivity]]
	                                  )

case class CharacterActivity(
	                            activityHash: Long,
	                            isNew: Boolean,
	                            canLead: Boolean,
	                            canJoin: Boolean,
	                            isCompleted: Boolean,
	                            isVisible: Boolean
	                            )

case class CharacterActivitiesDefinitions(
	                                         activities: Option[Map[String, CharacterActivityDefinition]],
	                                         destinations: Option[Map[String, CharacterActivityDestination]],
	                                         places: Option[Map[String, CharacterActivityPlace]],
	                                         activityTypes: Option[Map[String, CharacterActivityType]],
	                                         items: Option[Map[String, CharacterActivityItem]],
	                                         buckets: Option[Map[String, CharacterActivityBucket]],
	                                         // all null?
	                                         stats: AnyVal,
	                                         perks: AnyVal,
	                                         talentGrids: AnyVal,
	                                         statGroups: AnyVal,
	                                         progressionMappings: AnyVal,
	                                         sources: AnyVal,
	                                         progressions: AnyVal,
	                                         materialRequirements: AnyVal
	                                         )

case class CharacterActivityDefinition(
	                                      activityHash: Long,
	                                      activityName: String,
	                                      activityDescription: String,
	                                      icon: String,
	                                      releaseIcon: String,
	                                      releaseTime: Long,
	                                      activityLevel: Long,
	                                      completionFlagHash: Long,
	                                      activityPower: BigDecimal,
	                                      minParty: Int,
	                                      maxParty: Int,
	                                      maxPlayers: Int,
	                                      destinationHash: Long,
	                                      placeHash: Long,
	                                      activityTypeHash: Long,
	                                      tier: Int,
	                                      pgcrImage: String,
	                                      rewards: Option[Array[CharacterActivityDefinitionReward]],
	                                      skulls: Option[Array[ActivitySkull]] // null?
	                                      )

case class CharacterActivityDefinitionReward(
	                                            rewardItems: Option[Array[ActivityRewardItem]]
	                                            )

case class ActivityRewardItem(
	                             itemHash: Long,
	                             value: Long
	                             )

case class ActivitySkull(
	                        displayName: String,
	                        description: String,
	                        icon: String
	                        )

case class CharacterActivityDestination(
	                                       destinationHash: Long,
	                                       destinationName: Option[String],
	                                       destinationDescription: Option[String],
	                                       icon: String,
	                                       placeHash: Long,
	                                       destinationIdentifier: String,
	                                       locationIdentifier: Option[String]
	                                       )

case class CharacterActivityPlace(
	                                 placeHash: Long,
	                                 placeName: String,
	                                 placeDescription: String,
	                                 icon: String
	                                 )

case class CharacterActivityType(
	                                activityTypeHash: Long,
	                                identifier: String,
	                                activityTypeName: String,
	                                activityTypeDescription: Option[String],
	                                icon: String,
	                                order: Int,
	                                activeBackgroundVirtualPath: Option[String],
	                                completedBackgroundVirtualPath: Option[String],
	                                hiddenOverrideVirtualPath: Option[String],
	                                tooltipBackgroundVirtualPath: Option[String],
	                                enlargedActiveBackgroundVirtualPath: Option[String],
	                                enlargedCompletedBackgroundVirtualPath: Option[String],
	                                enlargedHiddenOverrideVirtualPath: Option[String],
	                                enlargedTooltipBackgroundVirtualPath: Option[String],
	                                statGroup: Option[String],
	                                friendlyUrlId: Option[String]
	                                )

case class CharacterActivityItem(
	                                itemHash: Long,
	                                itemName: String,
	                                icon: String,
	                                secondaryIcon: String,
	                                actionName: String,
	                                actionDescription: Option[String],
	                                tierTypeName: String,
	                                tierType: Int,
	                                bucketTypeHash: Long,
	                                primaryBaseStatHash: Long,
	                                stats: AnyVal, // null?
	                                perkHashes: AnyVal, // null?
	                                specialItemType: Int,
	                                talentGridHash: Long,
	                                hasGeometry: Boolean,
	                                statGroupHash: Long,
	                                itemLevels: Option[Array[AnyVal]], // null?
	                                qualityLevel: Long,
	                                rewardItemHash: Long,
	                                values: AnyVal, // null?
	                                itemType: Int,
	                                itemSubType: Int,
	                                classType: Int,
	                                nonTransferrable: Boolean,
	                                exclusive: Int,
	                                maxStackSize: Int
	                                )

case class CharacterActivityBucket(
	                                  bucketHash: Long,
	                                  bucketName: Option[String],
	                                  bucketDescription: Option[String],
	                                  scope: Int,
	                                  category: Int,
	                                  bucketOrder: Int,
	                                  bucketIdentifier: String,
	                                  itemCount: Int,
	                                  location: Int,
	                                  hasTransferDestination: Boolean
	                                  )