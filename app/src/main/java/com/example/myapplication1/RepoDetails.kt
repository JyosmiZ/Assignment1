package com.example.myapplication1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class RepoDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repo_details)
        val repoData = intent.getParcelableExtra<MyDataItem>(Constants.KEY_INTENT_DATA)
        findViewById<TextView>(R.id.title).text = repoData?.archive_url.toString()
        findViewById<TextView>(R.id.msg1).text = repoData?.id.toString()
        findViewById<TextView>(R.id.msg2).text = repoData?.commits_url.toString()
        findViewById<TextView>(R.id.msg3).text = repoData?.downloads_url.toString()
//        Toast.makeText(this,repoData?.id.toString(),Toast.LENGTH_LONG).show()
    }
}