package com.example.myapplication1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter (val context: Context, val userList: List<MyDataItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var listener: OnItemClickInteractionListener? = null

       inner class ViewHolder (itemView:View) : RecyclerView.ViewHolder (itemView) {
            var userId: TextView
            var title : TextView
            var img : ImageView
            var myDataItem : MyDataItem?= null

            init {
                userId = itemView.findViewById(R.id.userId)
                title = itemView.findViewById(R.id.userDesc)
                img = itemView.findViewById(R.id.image)

            }
            fun bindView(myDataItem: MyDataItem){
                this.myDataItem = myDataItem
                userId.text= myDataItem.name
                title.text = myDataItem.description

                Glide.with(context).load(myDataItem.owner?.avatar_url).into(img)
                itemView.setOnClickListener {
                    listener?.onItemClick(myDataItem)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
       holder.bindView(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
interface OnItemClickInteractionListener{ //Interface created
    fun onItemClick(myDataItem: MyDataItem) //method created≤
}