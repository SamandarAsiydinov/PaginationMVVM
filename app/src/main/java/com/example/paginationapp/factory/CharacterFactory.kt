package com.example.paginationapp.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.paginationapp.model.CharacterData

class CharacterFactory: DataSource.Factory<Int, CharacterData>() {

    private var mutableLiveData: MutableLiveData<CharacterListDataSource>? = null

    init {
        mutableLiveData = MutableLiveData()
    }

    override fun create(): DataSource<Int, CharacterData> {
        val listDataSource = CharacterListDataSource()
        mutableLiveData?.postValue(listDataSource)
        return listDataSource
    }
}