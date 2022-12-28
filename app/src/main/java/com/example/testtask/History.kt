package com.example.testtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.databinding.ActivityHistoryBinding
import com.example.testtask.databinding.ActivityMainBinding

class History : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var recyclerView: RecyclerView

    private lateinit var sqliteHelper: SQLiteHelper

    private var adapter: HistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sqliteHelper = SQLiteHelper(this)

        recyclerView = binding.listitems
        initRecyclerView()

        getReqs()

    }


    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HistoryAdapter()
        recyclerView.adapter = adapter
    }

    private fun getReqs(){
        val requestsList = sqliteHelper.getAllRequests()

        adapter?.addItems(requestsList)
    }
}