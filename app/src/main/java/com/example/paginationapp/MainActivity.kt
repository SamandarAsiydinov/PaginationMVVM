package com.example.paginationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginationapp.adapter.RvAdapter
import com.example.paginationapp.databinding.ActivityMainBinding
import com.example.paginationapp.viewmode.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var rvAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        rvAdapter = RvAdapter()
        setupRv()
        initViewModel()
    }

    private fun setupRv() = binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = rvAdapter
        val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        addItemDecoration(decoration)
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getRecyclerListObserver()?.observe(this) { list ->
            if (list != null) {
                rvAdapter.submitList(list)
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}