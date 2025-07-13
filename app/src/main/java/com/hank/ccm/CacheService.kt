package com.hank.ccm

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class CacheService : Service() {
    private val TAG: String? = CacheService::class.java.simpleName

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: ccm-service-")
        return null
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.d(TAG, "onStart: ccm-service-")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ccm-service-")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ccm-service-")
    }

}









