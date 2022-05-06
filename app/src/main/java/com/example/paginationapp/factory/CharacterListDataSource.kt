package com.example.paginationapp.factory

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationapp.model.CharacterData
import com.example.paginationapp.model.RickAndMortyList
import com.example.paginationapp.network.RetroInstance
import com.example.paginationapp.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListDataSource : PageKeyedDataSource<Int, CharacterData>() {
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterData>) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(params.key)
        call.enqueue(object : retrofit2.Callback<RickAndMortyList> {
            override fun onResponse(
                call: Call<RickAndMortyList>,
                response: Response<RickAndMortyList>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()?.results!!, params.key + 1)
                }
            }

            override fun onFailure(call: Call<RickAndMortyList>, t: Throwable) {
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterData>) {
        Log.d("@@@@@", "Int")
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterData>
    ) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(1)
        call.enqueue(object : Callback<RickAndMortyList> {
            override fun onResponse(
                call: Call<RickAndMortyList>,
                response: Response<RickAndMortyList>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()?.results!!, null, 2)
                }
            }

            override fun onFailure(call: Call<RickAndMortyList>, t: Throwable) {
                Log.d("@@@@@", "${t.message}")
            }
        })
    }
}