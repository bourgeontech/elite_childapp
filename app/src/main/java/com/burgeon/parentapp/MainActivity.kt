package com.burgeon.parentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.burgeon.parentapp.Dashboard.DasboardFragment
import com.burgeon.parentapp.Profile.ProfileFragment
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var preferenceObj = PreferenceRequestHelper(this)
        var userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        var studentId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")

        if (studentId != null && studentId != "") {

            supportFragmentManager?.beginTransaction()?.replace(
                R.id.fl_container,
                DasboardFragment()
            )?.commit()

        } else {

            val profilefragment =
                ProfileFragment.newInstance(0)

            supportFragmentManager?.beginTransaction()?.replace(
                R.id.fl_container,
                profilefragment
            )?.commit()
        }
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        val f = supportFragmentManager.findFragmentById(R.id.fl_container)
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            supportFragmentManager.popBackStack()

        } else if ((f is ProfileFragment) && !doubleBackToExitPressedOnce) {

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit.", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } else if ((f is DasboardFragment) && !doubleBackToExitPressedOnce) {

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit.", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } else if (!(f is DasboardFragment) && !(f is ProfileFragment)) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, DasboardFragment()).commit()
        } else {
            super.onBackPressed()
        }

    }


}
