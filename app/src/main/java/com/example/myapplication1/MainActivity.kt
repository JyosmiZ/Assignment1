package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.github.com/orgs/fossasia/"

class MainActivity : AppCompatActivity(),OnItemClickInteractionListener {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayout: LinearLayout

    private var layoutManager: RecyclerView.LayoutManager?= null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recyclerview).layoutManager = layoutManager

        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>?,
                response: Response<List<MyDataItem>?>?){
                val responseBody = response?.body() !!
                myAdapter = MyAdapter(baseContext, responseBody)
                findViewById<RecyclerView>(R.id.recyclerview).adapter = myAdapter
                myAdapter.listener = this@MainActivity // initialize listener
               // myAdapter.notifyDataSetChanged()

            }
//            {
//                Log.d("MainActivity", "onResponse: "+response?.toString())
//                val responseBody = response?.body()  // ? id null then response body will be null
//                val myStringBuilder = StringBuilder()
//                responseBody?.forEach { myData ->
//                    myStringBuilder.append("Name ")
//                    myStringBuilder.append(myData.name)
//                    myStringBuilder.append("\n")
//                    myStringBuilder.append("Description ")
//                    myStringBuilder.append(myData.description)
//                    myStringBuilder.append("\n\n")
//
//                }
////for each loop
//                println(myStringBuilder)
//                findViewById<TextView>(R.id.txtId).text = myStringBuilder
//            }

            override fun onFailure(call: Call<List<MyDataItem>?>?, t: Throwable?) {
                d("MainActivity", "onFailure: "+t?.message)
            }
        }
    )
    }

    override fun onItemClick(myDataItem : MyDataItem) { //current activity to target activity
        val intent= Intent(this, RepoDetails::class.java) //creating intent ... it will open repodetails class // this ... is main activity
        intent.putExtra(Constants.KEY_INTENT_DATA,myDataItem) // pasisng the data in the intent view (mydataitem)
        this.startActivity(intent) //trigerring to strat the intent
    }
}
