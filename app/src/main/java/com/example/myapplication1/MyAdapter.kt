package com.example.myapplication1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (val context: Context, val userList: List<MyDataItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var listener: OnItemClickInteractionListener? = null

       inner class ViewHolder (itemView:View) : RecyclerView.ViewHolder (itemView) {
            var userId: TextView
            var title : TextView
            var myDataItem : MyDataItem?= null

            init {
                userId = itemView.findViewById(R.id.userId)
                title = itemView.findViewById(R.id.userDesc)

                itemView.setOnClickListener{
//                    val position: Int = adapterPosition
//                    val intent= Intent(itemView.context, RepoDetails::class.java) //creating intent ... it willl open repodetails class
//                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    intent.putExtra(Constants.KEY_INTENT_DATA, myDataItem) // pasisng the data in the intent view (mydataitem)
//                    context.startActivity(intent) //trigerring to strat the intent
                }
            }
            fun bindView(myDataItem: MyDataItem){
                this.myDataItem = myDataItem
                userId.text= myDataItem.name
                title.text = myDataItem.description
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
    fun onItemClick(myDataItem: MyDataItem) //method created
}