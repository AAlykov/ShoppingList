package com.tomsk.alykov.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase (private val shopListRepository: ShopListRepository) {

    //fun getShopList(): List<ShopItem> {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}