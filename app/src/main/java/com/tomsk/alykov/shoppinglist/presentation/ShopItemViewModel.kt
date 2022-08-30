package com.tomsk.alykov.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomsk.alykov.shoppinglist.data.ShopListRepositoryImpl
import com.tomsk.alykov.shoppinglist.domain.AddShopItemUseCase
import com.tomsk.alykov.shoppinglist.domain.EditShopItemUseCase
import com.tomsk.alykov.shoppinglist.domain.GetShopItemUseCase
import com.tomsk.alykov.shoppinglist.domain.ShopItem

class ShopItemViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    //val errorInputNameMLD = MutableLiveData<Boolean>()
    private val _errorInputName  = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
    get() = _errorInputName

    private val _errorInputCount  = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
    get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
    get() = _shouldCloseScreen



    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }

        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
        //если inputName не равен нулл то вернем его и обрежем пробелы, в противном случае ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt()?: 0
        } catch (e: java.lang.Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    public fun resetErrorInputName() {
        _errorInputName.value = false
    }

    public fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}