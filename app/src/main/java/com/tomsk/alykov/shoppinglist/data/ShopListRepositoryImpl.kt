package com.tomsk.alykov.shoppinglist.data

import com.tomsk.alykov.shoppinglist.domain.ShopItem
import com.tomsk.alykov.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0


    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
        shopItem.id = autoIncrementId
        autoIncrementId ++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        //return shopList[shopItemId]
        return shopList.find { it.id == shopItemId } ?: throw RuntimeException("element $shopItemId not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList() //отправляем компию
    }
}