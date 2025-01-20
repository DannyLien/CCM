package com.hank.ccm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class MyViewModel : ViewModel() {
    private val TAG: String? = MyViewModel::class.java.simpleName
    val url = "https://api.jsonserve.com/pcLzBT"
    var wordsData = MutableLiveData<List<Word>>()

    fun vmJson() {
        viewModelScope.launch(Dispatchers.IO) {
            val json = URL(url).readText()
            val jsonArray = JSONObject(json).getJSONArray("words")
            for (i in 0 until jsonArray.length() - 1) {
                val w = jsonArray.getJSONObject(i)
                val name = w.getString("name")
                val diff = w.getInt("difficulty")
                Log.d(TAG, "word-vmJson- $name , $diff")
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
}