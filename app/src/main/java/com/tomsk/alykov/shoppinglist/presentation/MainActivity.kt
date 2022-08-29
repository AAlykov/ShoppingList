package com.tomsk.alykov.shoppinglist.presentation

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.tomsk.alykov.shoppinglist.R
import com.tomsk.alykov.shoppinglist.domain.ShopItem
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    val TAG = "AADebug"
    private lateinit var viewModel: MainViewModel
    private var count = 0
    //private lateinit var llShopList: LinearLayout
    private lateinit var adapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //llShopList = findViewById(R.id.ll_shop_list)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
//            Log.d("AADebug", it.toString())
            //adapter.shopList = it
            adapter.submitList(it)

        }

    }


    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ShopListAdapter()
        rvShopList.adapter = adapter
//        adapter.onShopItemLongClickListener = object : ShopListAdapter.OnShopItemLongClickListener {
//            override fun onShopItemLongClick(shopItem: ShopItem) {
//                viewModel.changeEnableState(shopItem)
//            }
//
//        }
        adapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }

        adapter.onShopItemClickListener = object : ShopListAdapter.OnShopItemClickListener {
            override fun onShopItemClick(shopItem: ShopItem) {
                Log.d(TAG, "onShopItemClick: ")
            }
        }


        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }
}