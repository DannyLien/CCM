package com.hank.ccm

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hank.ccm.data.GuessRoomDatabase
import com.hank.ccm.data.Recorded
import com.hank.ccm.databinding.ActivityRoomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {
    private val TAG: String? = RoomActivity::class.java.simpleName
    private lateinit var binding: ActivityRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //
        val rec1 = Recorded("Hank  ", 23)
        val rec2 = Recorded("Tom   ", 34)
        val rec3 = Recorded("Jack  ", 45)
        val rec4 = Recorded("Linda ", 56)
        val rec5 = Recorded("Amy   ", 67)

        CoroutineScope(Dispatchers.IO).launch {
//            GuessRoomDatabase.getInstance(this@RoomActivity)?.recordedDao()?.insert(rec1)
//            GuessRoomDatabase.getInstance(this@RoomActivity)?.recordedDao()?.insert(rec2)
//            GuessRoomDatabase.getInstance(this@RoomActivity)?.recordedDao()?.insert(rec3)
//            GuessRoomDatabase.getInstance(this@RoomActivity)?.recordedDao()?.insert(rec4)
//            GuessRoomDatabase.getInstance(this@RoomActivity)?.recordedDao()?.insert(rec5)

            GuessRoomDatabase.getInstance(this@RoomActivity)?.recordedDao()?.getAll()
                ?.forEach {
                    Log.d(TAG, "room-getall- ${it.id} , ${it.nickname} , ${it.counter} ")
                }
        }

    }

    fun setRoomFinish(view: View) {
        finish()
    }

}