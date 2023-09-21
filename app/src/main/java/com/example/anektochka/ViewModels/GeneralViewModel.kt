package com.example.anektochka.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anektochka.Model.Interfaces.JokesApi
import com.example.anektochka.Model.Item.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(): ViewModel() {

    private lateinit var jokes : MutableList<Post>
    private  var jokesApi: JokesApi
    val posts = MutableLiveData<MutableList<Post>>()

    init {
        Log.d("Yatoro","GVM is init")
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().baseUrl("https://official-joke-api.appspot.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
         jokesApi = retrofit.create(JokesApi::class.java)
    }

    fun setNewList(){
        CoroutineScope(Dispatchers.IO).launch {
            jokes = jokesApi.getJokes()
            withContext(Dispatchers.Main) {
                    posts.value = jokes
            }
        }
    }

}