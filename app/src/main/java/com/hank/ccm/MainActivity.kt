package com.hank.ccm

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.hank.ccm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val requestCamera = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            takePoto()
        } else {
            Snackbar.make(binding.root, "Denied", Snackbar.LENGTH_LONG).show()
        }
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_camera -> {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    takePoto()
                } else {
                    requestCamera.launch(Manifest.permission.CAMERA)
                }
                true
            }

            R.id.action_nickname -> true
            R.id.action_name -> true
            R.id.action_words -> true
            R.id.action_room -> true
            R.id.action_news -> true
            R.id.action_service -> true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun takePoto() {
        Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA).also {
            startActivity(it)
        }
    }

}