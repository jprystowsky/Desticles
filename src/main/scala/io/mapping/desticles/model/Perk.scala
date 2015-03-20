package io.mapping.desticles.model

case class Perk(
	               perkHash: Long,
	               displayName: Option[String],
	               displayDescription: Option[String],
	               displayIcon: String,
	               isDisplayable: Boolean,
	               perkGroups: Option[PerkGroup]
	               )

case class PerkGroup(
	                    weaponPerformance: Option[Int],
	                    impactEffects: Option[Int],
	                    guardianAttributes: Int,
	                    lightAbilities: Int,
	                    damageTypes: Int
	                    )