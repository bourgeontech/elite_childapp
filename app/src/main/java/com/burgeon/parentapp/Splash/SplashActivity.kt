package com.burgeon.parentapp.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.burgeon.parentapp.Login.LoginActivity
import com.burgeon.parentapp.MainActivity
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper


class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 3000 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var preferenceObj = PreferenceRequestHelper(this)
        var userId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")

        Handler().postDelayed({

            if (userId != "" && userId != null)
                startActivity(Intent(this, MainActivity::class.java))
            else
                startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }, SPLASH_TIME_OUT)


    }
}
