package com.hank.ccm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URL

class MyViewModel : ViewModel() {
    private val TAG: String? = MyViewModel::class.java.simpleName

    //    val url = "https://api.jsonserve.com/pcLzBT"
    val url = "http://delexons.ddns.net:81/JsonData/words_json.txt"
    var wordsData = MutableLiveData<List<Word>>()
    var movieData = MutableLiveData<List<Movie>>()

    fun vmJson() {
        viewModelScope.launch(Dispatchers.IO) {
            val json = URL(url).readText()
            val jsonArray = JSONObject(json).getJSONArray("words")
            for (i in 0 until jsonArray.length() - 1) {
                val w = jsonArray.getJSONObject(i)
                val name = w.getString("name")
                val diff = w.getInt("difficulty")
//                Log.d(TAG, "word-vmJson- $name , $diff")
            }
        }
    }

    fun vmGson() {
        viewModelScope.launch(Dispatchers.IO) {
            val json = URL(url).readText()
            val gson = Gson().fromJson(json, Words::class.java)
//            gson.words.forEach {
//                Log.d(TAG, "word-vmGson- ${it.name} , ${it.difficulty} ")
//            }
            wordsData.postValue(gson.words)
        }
    }

    fun vmMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://api.themoviedb.org/3/movie/popular?language=zh-TW&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlYzZmNDVlODJlODdiNTkzZWJjZDAxYWRmNWU2YWVjYiIsIm5iZiI6MTcxNDY0NjU3Ny4xNjMsInN1YiI6IjY2MzM2ZTMxODNlZTY3MDEyZDQwNjQ1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.PUpzJTeo0DfeszsXmp4WGhqyZjqaLVntqlfhqHbzra4"
                )
                .build()
            val response = client.newCall(request).execute()
            val json = response.body?.string()      // - Key point
            val gson = Gson().fromJson(json, MovieResult::class.java)
            movieData.postValue(gson.results)
        }
    }

}










