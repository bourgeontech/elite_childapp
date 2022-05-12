package com.burgeon.parentapp.Application

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.multidex.MultiDex

/**
 * Created by Ajay K K on 2020-02-14.
 */


class Childapp : Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        Thread.setDefaultUncaughtExceptionHandler { thread, ex ->
            handleUncaughtException(
                thread,
                ex
            )
        }
    }


    fun handleUncaughtException(thread: Thread?, e: Throwable) {
        val stackTrace = Log.getStackTraceString(e)
        val message = e.message
        val intent =
            Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(
            Intent.EXTRA_EMAIL,
            arrayOf("nidhin.raj@gitmail.in")
        )
        intent.putExtra(Intent.EXTRA_SUBJECT, "MyApp Crash log file")
        intent.putExtra(Intent.EXTRA_TEXT, stackTrace)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // required when starting from Application
        startActivity(intent)
    }
}