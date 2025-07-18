package com.hank.ccm

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hank.ccm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val requestRS = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val nickName = it.data?.getStringExtra("NICKNAME")
            val nickNum = it.data?.getIntExtra("NICKNUM", 0)
            Log.d(TAG, "ccm-nick-result- $nickName , $nickNum")
            //
            getSharedPreferences("CCM.spf", MODE_PRIVATE).apply {
                val spfNick = getString("SPFNICK", null)
                val spfNum = getInt("SPFNUM", 0)
                Log.d(TAG, "ccm-nick-spf- $spfNick , $spfNum ")
            }
        }
    }
//    private val requestCamera = registerForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) {
//        if (it) {
//            takePoto()
//        } else {
//            Snackbar.make(binding.root, "Denied", Snackbar.LENGTH_LONG).show()
//        }
//    }
    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (
            it[Manifest.permission.CAMERA] == true &&
            it[Manifest.permission.INTERNET] == true
        ) {
            takePoto()
        } else {
            Snackbar.make(binding.root, "Loss Permission", Snackbar.LENGTH_LONG).show()
        }
    }
    private lateinit var binding: ActivityMainBinding
    private val TAG: String? = MainActivity::class.java.simpleName
    private lateinit var viewModel: GuessViewModel

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
        // ViewModel
        viewModel = ViewModelProvider(this).get(GuessViewModel::class.java)
        viewModel.secretData.observe(this) { secretData ->
            Log.d(TAG, "ccm-guess-secret- ${secretData} ")
        }
        viewModel.counter.observe(this) { counter ->
            binding.tvCounter.text = counter.toString()
        }
        viewModel.gameStatus.observe(this) { status ->
            if (status != GameStatus.INIT) {
                val message = when (status) {
                    GameStatus.BIGGER -> "Bigger"
                    GameStatus.SMALLER -> "Smaller"
                    GameStatus.BINGO -> "You got it!"
                    GameStatus.INIT -> ""
                }
                AlertDialog.Builder(this)
                    .setTitle("Guess Status")
                    .setMessage(message)
                    .setPositiveButton("OK") { guess, which ->
                        binding.edNumber.text.clear()
                    }
                    .setNegativeButton("Replay") { reset, which ->
                        binding.edNumber.text.clear()
                        viewModel.vmReset()
                    }
                    .show()
            }
        }
    }

    fun setGuess(view: View) {
        if (!binding.edNumber.text.toString().equals("")) {
            val num = binding.edNumber.text.toString().toInt()
            viewModel.vmGuess(num)
        }
    }

    fun setReset(view: View) {
        viewModel.vmReset()
    }

    fun setFinish(view: View) {
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private val getCache: Intent
        get() {
            val cache = Intent(this, CacheService::class.java)
            return cache
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_camera -> {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.INTERNET)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    takePoto()
                } else {
//                    requestCamera.launch(Manifest.permission.CAMERA)
                    requestPermission.launch(
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.INTERNET,
                        )
                    )
                }
                true
            }

            R.id.action_nickname -> {
                Intent(this, NickActivity::class.java).apply {
                    putExtra("NAME", "Hank")
                    putExtra("LEVEL", 543)
                }.also {
//                    startActivity(it)
                    requestRS.launch(it)
                }
                true
            }

            R.id.action_name -> {
                Intent(this, NameActivity::class.java).also {
                    startActivity(it)
                }
                true
            }

            R.id.action_words -> {
                Intent(this, WordsActivity::class.java).also {
                    startActivity(it)
                }
                true
            }

            R.id.action_room -> {
                Intent(this, RoomActivity::class.java).also {
                    startActivity(it)
                }
                true
            }

            R.id.action_news -> {
                Intent(this, NewsActivity::class.java).also {
                    startActivity(it)
                }
                true
            }

            R.id.action_service -> {
                val cache = getCache
                startService(cache)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: main-service-")
        stopService(getCache)
    }

    private fun takePoto() {
        Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA).also {
            startActivity(it)
        }
    }

}









