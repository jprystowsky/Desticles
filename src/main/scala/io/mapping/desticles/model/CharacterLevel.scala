package io.mapping.desticles.model

trait CharacterLevel {
	def baseCharacterLevel: Long
	def isPrestigeLevel: Boolean
	def percentToNextLevel: BigDecimal
}
