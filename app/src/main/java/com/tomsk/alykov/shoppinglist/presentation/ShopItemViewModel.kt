package com.tomsk.alykov.shoppinglist.presentation

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

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)
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
            //TODO: show error input name
            result = false
        }
        if (count <= 0) {
            //TODO: show error input count
            result = false
        }
        return result
    }
}