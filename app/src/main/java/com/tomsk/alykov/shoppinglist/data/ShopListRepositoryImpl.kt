package com.tomsk.alykov.shoppinglist.data

import com.tomsk.alykov.shoppinglist.domain.ShopItem
import com.tomsk.alykov.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

//сделаем его object, который по сути является single-tone - где бы мы не обратились к объекту это всегда будет один и тот же эксземпляр
object ShopListRepositoryImpl: ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId
            autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItemFromID(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItemFromID(id: Int): ShopItem {
        return shopList.find { it.id == id }  ?: throw RuntimeException("Element not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }

}