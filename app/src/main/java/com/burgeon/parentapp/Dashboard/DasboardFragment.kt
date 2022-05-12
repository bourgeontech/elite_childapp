package com.burgeon.parentapp.Dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.burgeon.parentapp.Adapter.HomeAdapter
import com.burgeon.parentapp.Login.LoginActivity

import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.ItemDecorationAlbumColumns
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.dashboard_fragment.*
import kotlinx.android.synthetic.main.dashboard_fragment.pgProgress
import kotlinx.android.synthetic.main.dashboard_fragment.rvHome
import kotlinx.android.synthetic.main.dashboard_fragment.signinout
import kotlinx.android.synthetic.main.dashboard_fragment.tvParentname
import kotlinx.android.synthetic.main.dashboard_fragment.txtId
import kotlinx.android.synthetic.main.fragment_home_new.*
import java.lang.Exception

class DasboardFragment : Fragment(), DashboardView {


    private var childSection: String = ""
    private var userName: String? = ""
    private var childIdClass: String? = ""
    private var childIdName: String? = ""
    private var childId: String? = ""
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = DashboardPresenter(this, DashboardInteractor())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home_new, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivPower.setOnClickListener {
            val builder = AlertDialog.Builder(context!!)
            builder.setTitle("Logout")
            builder.setMessage("Are you want to logout the application?")
            builder.setPositiveButton("YES") { dialog, which ->
                //                val sharedpreference =
//                    activity?.getSharedPreferences(Constants.PRES_EVENT_NAME, Context.MODE_PRIVATE)
//                sharedpreference?.edit()?.clear()?.commit()
                preferenceObj.clear()
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            builder.show()
        }

        preferenceObj = PreferenceRequestHelper(activity)
        childId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")
        childIdName = preferenceObj.getStringValue(Constants.PRES_STUDENT_NAME, "")
        childIdClass = preferenceObj.getStringValue(Constants.PRES_STUDENT_CLASS, "")
        childSection = preferenceObj.getStringValue(Constants.PRES_STUDENT_SECTION, "")

        tvParentname.text = "Hi, " + childIdName
        txtId.text = childIdClass + " " + childSection

        val homeList = ArrayList<String>()
        homeList.add("Profile")
        homeList.add("Homework")
        homeList.add("Exam")
        homeList.add("Circular")
        homeList.add("Exam Date")
        homeList.add("Exam Result")
        homeList.add("Fees")
        homeList.add("Timetable")
        homeList.add("Remark")
        homeList.add("Attendance")
      //homeList.add("Gallery")
        // homeList.add("Enquiry")
        homeList.add("Tickets")
        //homeList.add("Live Class")
        homeList.add("Change Password")

        rvHome.layoutManager = GridLayoutManager(activity, 3)
        rvHome.addItemDecoration(
            ItemDecorationAlbumColumns(
                3,
                3
            )
        )

        val homeadapter = HomeAdapter(homeList, activity, fragmentManager)
        rvHome.adapter = homeadapter


        /*  ivChange.setOnClickListener {

              val profilefragment =
                  ProfileFragment.newInstance(1)

              fragmentManager?.beginTransaction()?.replace(
                  R.id.fl_container,
                  profilefragment
              )?.addToBackStack("")?.commit()

          }
  */
        /*  ivLogout.setOnClickListener {
              preferenceObj.clear()

              activity?.finish()
              startActivity(Intent(activity, LoginActivity::class.java))
          }
  */
        /*signinout.setOnCheckedChangeListener { compoundButton: CompoundButton?, b: Boolean ->
            if (b) {
                presenter.addSignin(childId)
            }
        }*/

        signinout?.setOnClickListener {
            if (signinout.isChecked)
                presenter.addSignin(childId)
        }

        getHomeDatadetails()
    }

    private fun getHomeDatadetails() {
        if (childId != "" && childId != null) {

            /*     tvName.text = childIdName
                 tvClass.text = childIdClass*/

            presenter.getSignindata(childId)
        }
    }

    override fun apiError() {
        try {
            signinout?.isChecked = false
            Toast.makeText(activity, "Please try later", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }
    }

    override fun showProgress() {
        pgProgress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgress?.visibility = View.GONE
    }

    override fun setHomeData() {
        try {
            signinout?.isChecked = true
            Toast.makeText(activity, "Successfully marked", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
        }
    }

    override fun signedData() {
        try {
            signinout.isChecked = true
        } catch (e: Exception) {
        }
    }

    override fun signedDataError() {
        try {
            signinout?.isChecked = false
        } catch (e: Exception) {
        }
    }
}
