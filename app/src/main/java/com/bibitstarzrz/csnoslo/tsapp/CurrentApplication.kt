package com.bibitstarzrz.csnoslo.tsapp

import android.app.Application
import com.onesignal.OneSignal
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class CurrentApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        val signalId = "6c3ed8a8-3d6e-4753-ae06-2eb1c4f7b802"

        OneSignal.initWithContext(this, signalId)

        thread {
            runBlocking {
                OneSignal.Notifications.requestPermission(true)
            }
        }
    }
}