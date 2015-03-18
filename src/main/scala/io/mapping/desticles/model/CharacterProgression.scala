package io.mapping.desticles.model

case class CharacterProgression(
	                               data: CharacterProgressionData
	                               )

case class CharacterProgressionData(
	                                   progressions: Option[Array[Progression]],
	                                   levelProgression: Option[Progression],
	                                   baseCharacterLevel: Long,
	                                   isPrestigeLevel: Boolean,
	                                   factionProgressionHash: Long,
	                                   percentToNextLevel: BigDecimal
	                                   ) extends CharacterLevel