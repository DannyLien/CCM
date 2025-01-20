package com.hank.ccm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hank.ccm.databinding.ActivityNickBinding

class NickActivity : AppCompatActivity() {
    private val TAG: String? = NickActivity::class.java.simpleName
    private lateinit var binding: ActivityNickBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNickBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("NAME")
        val level = intent.getIntExtra("LEVEL", 0)
        Log.d(TAG, "nick-intent- $name , $level")

    }

    fun setSave(view: View) {
        if (!binding.edName.text.toString().equals("")) {
            val nickName = binding.edName.text.toString()
            intent.putExtra("NICKNAME", nickName)
            intent.putExtra("NICKNUM", 945)
            setResult(RESULT_OK, intent)
            //
            getSharedPreferences("CCM.spf", MODE_PRIVATE)
                .edit()
                .putString("SPFNICK", nickName)
                .putInt("SPFNUM", 666)
                .apply()
            finish()
        }
    }

}