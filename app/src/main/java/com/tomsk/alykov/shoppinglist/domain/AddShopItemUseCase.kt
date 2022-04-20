package com.tomsk.alykov.shoppinglist.domain

class AddShopItemUseCase (private val shopListRepository: ShopListRepository){
    fun addShopItem(shopItem: ShopItem): ShopItem {
        shopListRepository.addShopItem(shopItem)
    }
}