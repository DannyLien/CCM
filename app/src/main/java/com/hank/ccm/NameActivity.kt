package com.hank.ccm

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.hank.ccm.databinding.ActivityNameBinding


class NameActivity : AppCompatActivity() {
    private val TAG: String? = NameActivity::class.java.simpleName
    private lateinit var binding: ActivityNameBinding

    // 資料
    val names = listOf<String>(
        "Aaren", "Abbe", "Adele", "Carlyn",
        "Carol", "Cassy", "Claudia", "Dale",
        "Debra", "Ellen", "Gilberta", "Hallie",
        "Harlene", "Isabelle", "Jacklyn", "Jaimie",
        "Jenifer", "Kaitlin", "Kaja"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // spinner
        val sp = binding.spinner
        sp.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, names
        ).apply {
            setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
        }
        sp.prompt = "Select Name"
        sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapter: AdapterView<*>?, view: View, i: Int, l: Long) {
                Log.d(
                    TAG, "onItemSelected: ccm-name-spinner- " +
                            "${i} , ${names.get(i)}"
                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        // Recycler View
        val recy = binding.rvName
        recy.setHasFixedSize(true)
        recy.layoutManager = LinearLayoutManager(this)
        recy.adapter = NameAdapter(names)

    }

    //
    fun setNameFinish(view: View) {
        finish()
    }

}