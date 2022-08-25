package com.tomsk.alykov.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tomsk.alykov.shoppinglist.domain.ShopItem
import com.tomsk.alykov.shoppinglist.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl: ShopListRepository {

    //private val shopList = mutableListOf<ShopItem>()
    private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })

    private var autoIncrementId = 0

    private val shopListLD = MutableLiveData<List<ShopItem>>()

    init { //
        for (i in 0 until 1000) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }


    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
        shopItem.id = autoIncrementId
        autoIncrementId ++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
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

    //override fun getShopList(): List<ShopItem> {
    override fun getShopList(): LiveData<List<ShopItem>> {
        //return shopList.toList() //отправляем копию
        return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}