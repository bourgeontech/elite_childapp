package com.burgeon.parentapp.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.MyConference
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.PreferenceRequestHelper


/**
 * Created by Ajay K K on 2020-02-14.
 */
class ClassAdapter(
    val myclass: List<MyConference>,
    val context: FragmentActivity?,
    val manager: FragmentManager?
) : RecyclerView.Adapter<ClassAdapter.ClassHolder>() {
    private var userId: String = ""
    private lateinit var preferenceObj2: PreferenceRequestHelper
    private var row_index: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassHolder {

        val view: ClassHolder =
            ClassHolder(
                LayoutInflater.from(context!!).inflate(
                    R.layout.row_class,
                    parent,
                    false
                )
            )
        return view;

    }

    override fun getItemCount(): Int {
        // return items?.size!!
        return myclass?.size!!
    }

    override fun onBindViewHolder(holder: ClassHolder, position: Int) {
        holder.tvName.setText(myclass?.get(position)?.title)
        holder.rlAll.setOnClickListener {
            try {
                val browse = Intent(Intent.ACTION_VIEW, Uri.parse(myclass?.get(position)?.url))
                context?.startActivity(browse)
            }catch (e:Exception)
            {

            }
        }

    }

    class ClassHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById(R.id.tvName) as TextView
       val  rlAll = itemView.findViewById(R.id.rlAll) as RelativeLayout
    }
}
