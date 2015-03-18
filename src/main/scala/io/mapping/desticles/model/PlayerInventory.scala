package io.mapping.desticles.model

case class PlayerInventory(buckets: PlayerInventoryBucket,
	                          currencies: Array[PlayerInventoryCurrency])

case class PlayerInventoryBucket(Invisible: Array[AnyVal],
                                 Item: Array[AnyVal],
                                 Currency: Array[AnyVal]) // null?

case class PlayerInventoryCurrency(itemHash: Long,
                                   value: Long)