package com.tomsk.alykov.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomsk.alykov.shoppinglist.data.ShopListRepositoryImpl
import com.tomsk.alykov.shoppinglist.domain.DeleteShopItemUseCase
import com.tomsk.alykov.shoppinglist.domain.EditShopItemUseCase
import com.tomsk.alykov.shoppinglist.domain.GetShopListUseCase
import com.tomsk.alykov.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    //val shopList = MutableLiveData<List<ShopItem>>()
    val shopList = getShopListUseCase.getShopList()

/*    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }
*/
    fun deleteShopItem(item: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(item)
        //val list = getShopListUseCase.getShopList()
        //shopList.value = list
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        //val list = getShopListUseCase.getShopList()
        //shopList.value = list
    }




}