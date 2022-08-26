package com.tomsk.alykov.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tomsk.alykov.shoppinglist.R
import com.tomsk.alykov.shoppinglist.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    //var onShopItemLongClickListener: OnShopItemLongClickListener? = null
    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: OnShopItemClickListener? = null

    var shopList = listOf<ShopItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    class ShopItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val layout = when(viewType) {
            100 -> R.layout.item_shop_enabled
            101 -> R.layout.item_shop_disabled
            else -> throw java.lang.RuntimeException("unknown view type")
        }
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disabled, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        var status = ""
        if (shopItem.enabled) status = "Active" else status = "Not Active"

        holder.itemView.setOnLongClickListener {
            //onShopItemLongClickListener?.onShopItemLongClick(shopItem)
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }

        holder.itemView.setOnClickListener {
            onShopItemClickListener?.onShopItemClick(shopItem)
            //onShopItemLongClickListener?.invoke(shopItem)
            true
        }



        holder.tvName.text = "${shopItem.name}  $status"
        holder.tvCount.text = shopItem.count.toString()

/*        if (shopItem.enabled) {
            holder.tvName.text = "${shopItem.name}  $status"
            holder.tvCount.text = shopItem.count.toString()
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.system_accent1_100))
        } else {
            holder.tvName.text = "${shopItem.name}  $status"
            holder.tvCount.text = shopItem.count.toString()
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.darker_gray))
        }*/
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enabled) {
          100
        } else {
          101
        }
        //return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    interface OnShopItemLongClickListener {
        fun onShopItemLongClick(shopItem: ShopItem)
    }

    interface OnShopItemClickListener {
        fun onShopItemClick(shopItem: ShopItem)
    }
}