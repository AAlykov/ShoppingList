package com.tomsk.alykov.shoppinglist.domain

class GetShopItemFromIDUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemFromID (id: Int): ShopItem {
        return shopListRepository.getShopItemFromID(id)
    }
}