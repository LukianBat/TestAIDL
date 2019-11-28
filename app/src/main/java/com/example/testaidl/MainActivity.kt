package com.example.testaidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mService: IMainService

    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            log.append("Service binded!\n")
            mService = IMainService.Stub.asInterface(service)

            performListing()
        }

        override fun onServiceDisconnected(className: ComponentName) {
            log.append("Service disconnected.\n")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceIntent = Intent()
            .setComponent(
                ComponentName(
                    "com.example.testaidl",
                    "com.example.testaidl.MainService"
                )
            )
        log.text = "Starting service…\n"
        startService(serviceIntent)
        log.append("Binding service…\n")
        bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE)
    }

    private fun performListing() {
        log.append("Requesting file listing…\n")
        val start = System.currentTimeMillis()
        var end: Long = 0
        val results = mService.listFiles("/sdcard/testing")
        end = System.currentTimeMillis()
        var index = 0
        log.append("Received " + results.size + " results:\n")
        for (o in results) {
            if (index > 20) {
                log.append("\t -> Response truncated!\n")
                break
            }
            log.append("\t -> " + o.path + "\n")
            index++
        }
        log.append("File listing took " + (end.toDouble() - start.toDouble()) / 1000.0 + " seconds, or " + (end - start) + " milliseconds.\n")
        mService.exit()


    }
}