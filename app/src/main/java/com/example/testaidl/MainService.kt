package com.example.testaidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.ArrayList


class MainService : Service() {

    private val mBinder = object : IMainService.Stub() {
        override fun listFiles(path: String): Array<MainObject> {
            val toSend = ArrayList<MainObject>()
            for (i in 0..999)
                toSend.add(MainObject("/example/item" + (i + 1)))
            return toSend.toTypedArray()
        }

        override fun exit() {
            stopSelf()
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }
}