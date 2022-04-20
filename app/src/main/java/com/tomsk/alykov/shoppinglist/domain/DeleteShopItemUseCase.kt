package com.tomsk.alykov.shoppinglist.domain

class DeleteShopItemUseCase (private val shopListRepository: ShopListRepository){
    fun deleteShopItem(shopItem: ShopItem): ShopItem {
        shopListRepository.deleteShopItem(shopItem)
    }
}