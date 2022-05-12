package com.burgeon.parentapp.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.burgeon.parentapp.Adapter.ProfileAdapter
import com.burgeon.parentapp.Datamodels.childdetailsDatamodel

import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.ItemDecorationAlbumColumns
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), ProfileView {

    private var userId: String? = ""
    private var childId: String? = ""
    private var position: Int? = 0
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = ProfilePresenter(this, ProfileInteractor())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        position = arguments?.getInt(ARG_NAME)
        preferenceObj = PreferenceRequestHelper(activity)

        preferenceObj = PreferenceRequestHelper(activity)
        childId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")


        presenter.getProfile(userId)
    }

    override fun showProgress() {
        pgprogress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgprogress?.visibility = View.GONE
    }

    override fun setProfileData(child: List<childdetailsDatamodel>?) {
        rvStudents.layoutManager = GridLayoutManager(activity, 2)
        rvStudents.addItemDecoration(
            ItemDecorationAlbumColumns(
                2,
                2
            )
        )
//        rvStudents?.layoutManager =
//            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val homeadapter = ProfileAdapter(child, activity, fragmentManager, preferenceObj,position,childId)
        rvStudents?.adapter = homeadapter
    }

    override fun serFailure() {
        Toast.makeText(activity, "Please try later", Toast.LENGTH_LONG).show()
    }

    companion object {

        const val ARG_NAME = "position"

        fun newInstance(pos: Int): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putInt("position", pos)
            fragment.setArguments(args)
            return fragment
        }
    }
}
