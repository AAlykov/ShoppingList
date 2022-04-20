package com.tomsk.alykov.shoppinglist.domain

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem): ShopItem
    fun deleteShopItem(shopItem: ShopItem): ShopItem
    fun editShopItem(shopItem: ShopItem): ShopItem
    fun getShopItemFromID (id: Int): ShopItem
    fun getShopList(): List<ShopItem>
}