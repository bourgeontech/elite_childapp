package com.burgeon.parentapp.Changepassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_changepassword.*
import java.lang.Exception


class ChangepasswordFragment : Fragment(), ChangepasswdView {

    private val presenter = ChangepasswdPresenter(this, ChangepasswdInteractor())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_changepassword, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var preferenceObj = PreferenceRequestHelper(activity)
        var userId = preferenceObj.getStringValue(Constants.PRES_ID, "")

        btnChangepasswd.setOnClickListener {
            presenter.onChangepasswd(
                userId, etOldpasswd?.text.toString(),
                etNewpasswd?.text.toString(), etConfirmpasswd?.text.toString()
            )
        }
    }

    override fun showProgress() {
        pgProgress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgress?.visibility = View.GONE
    }

    override fun setPasswordError() {
        etOldpasswd?.setError("Old password")
    }

    override fun passwdChangeerror(msg: String?) {
        try{
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        }catch (e:Exception){
        }

    }

    override fun passwdChangesuccess() {
        try{
            Toast.makeText(activity, "Successfully updated", Toast.LENGTH_LONG).show()
            fragmentManager?.popBackStackImmediate()
        }catch (e:Exception){
        }

    }

    override fun passwdMismatcherror() {
        etConfirmpasswd?.setError("Password mismatch")
    }

    override fun passwdNewerror() {
        etNewpasswd?.setError("New password")
    }


    companion object {
    }
}
