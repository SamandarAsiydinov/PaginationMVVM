package com.example.paginationapp.viewmode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.paginationapp.factory.CharacterFactory
import com.example.paginationapp.factory.CharacterListDataSource
import com.example.paginationapp.model.CharacterData
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainViewModel : ViewModel() {

    private var characterList: LiveData<PagedList<CharacterData>>? = null

    init {
        initPaging()
    }

    private fun initPaging() {
        val factory = CharacterFactory()
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(30)
            .build()

        val executors = Executors.newFixedThreadPool(5)
        characterList = LivePagedListBuilder(factory, config)
            .setFetchExecutor(executors)
            .build()
    }

    fun getRecyclerListObserver(): LiveData<PagedList<CharacterData>>? {
        return characterList
    }
}