package com.hank.ccm

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hank.ccm.databinding.ActivityWordsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.truncate

class WordsActivity : AppCompatActivity() {
    private lateinit var myViewModel: MyViewModel
    private lateinit var binding: ActivityWordsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWordsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.vmJson()
//        myViewModel.vmGson()
        myViewModel.vmMovie()
        //
        val recy = binding.rvWord
        recy.setHasFixedSize(true)
        recy.layoutManager = LinearLayoutManager(this)
        //Gson
        myViewModel.wordsData.observe(this) {
            recy.adapter = WordsAdapter(it)
        }
        //Movie
        myViewModel.movieData.observe(this){
            recy.adapter = MovieAdapter(it)
        }
    }

    fun setWordFinish(view: View) {
        finish()
    }

}