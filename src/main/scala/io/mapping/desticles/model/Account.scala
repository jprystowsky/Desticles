package io.mapping.desticles.model

case class Account(data: AccountData)

case class AccountData(membershipId: String,
                       membershipType: Int,
                       characters: Array[PlayerCharacter],
                       clanName: String,
                       clanTag: String,
                       inventory: PlayerInventory,
                       grimoireScore: Long)