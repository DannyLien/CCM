package com.hank.ccm

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class CacheService : Service() {
    private val TAG: String? = CacheService::class.java.simpleName

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Log.d(TAG, "onStart: service-")
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: service-")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: service-")
        super.onDestroy()
    }
}