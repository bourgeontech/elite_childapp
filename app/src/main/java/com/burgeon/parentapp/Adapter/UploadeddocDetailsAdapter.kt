package com.burgeon.parentapp.Adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.Uploadeddetails
import com.burgeon.parentapp.R
import com.bumptech.glide.Glide
import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ajay K K on 2020-02-13.
 */
class UploadeddocDetailsAdapter(
    var id: List<Uploadeddetails>?,
    var activity: FragmentActivity?,
    var fragmentManager: FragmentManager
) : RecyclerView.Adapter<UploadeddocViewHolder>() {

    private lateinit var downloaddocObj: onDownloadDoc
    private lateinit var removedocObj: onRemoveDoc
    private var greenProgressbar: Drawable? = null

    public interface onRemoveDoc {
        fun onRemove(id: String, position: Int)
    }

    fun setRemovedoc(removedoc: onRemoveDoc) {
        removedocObj = removedoc
    }

    public interface onDownloadDoc {
        fun onDownload(id: String)
    }

    fun setDownloaddoc(downloaddoc: onDownloadDoc) {
        downloaddocObj = downloaddoc
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadeddocViewHolder {
        val view: UploadeddocViewHolder =
            UploadeddocViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.row_uploaded_details,
                    parent,
                    false
                )
            )
        return view;
    }

    override fun getItemCount(): Int {
        return id?.size!!
    }

    override fun onBindViewHolder(holder: UploadeddocViewHolder, position: Int) {

        var fileextension = File(id?.get(position)?.document!!).extension


        if (fileextension.equals("pdf")) {
            greenProgressbar =
                ContextCompat.getDrawable(activity!!, R.drawable.ic_pdf)
        } else if (fileextension.equals("docx")) {
            greenProgressbar =
                ContextCompat.getDrawable(activity!!, R.drawable.ic_word)
        } else if ((fileextension.equals("png")) || (fileextension.equals("jpeg")) || (fileextension.equals(
                "jpg"
            ))
        ) {
            greenProgressbar =
                ContextCompat.getDrawable(activity!!, R.drawable.ic_photo)
        } else
            greenProgressbar =
                ContextCompat.getDrawable(activity!!, R.drawable.ic_excel)

        Glide.with(activity!!).load(greenProgressbar).into(holder.ivAttachment)

        holder.txtSubject.text = id?.get(position)?.title
        holder.tvRemark.text = id?.get(position)?.remark

        holder.txtDate.text = parseDate(id?.get(position)?.date)

        holder.ivDelete.setOnClickListener {
            removedocObj.onRemove(id?.get(position)?.id!!, position)
        }

        holder.llDownload.setOnClickListener {
            downloaddocObj.onDownload(id?.get(position)?.document!!)
        }

    }
}

class UploadeddocViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtSubject = itemView.findViewById(R.id.tvTitle) as TextView
    val txtDate = itemView.findViewById(R.id.tvDate) as TextView
    val tvRemark = itemView.findViewById(R.id.tvRemark) as TextView
    val ivDelete = itemView.findViewById(R.id.ivDelete) as ImageView
    val ivAttachment = itemView.findViewById(R.id.ivAttachment) as ImageView
    val llDownload = itemView.findViewById(R.id.llDownload) as LinearLayout
}

fun parseDate(time: String?): String? {
    val inputPattern = "yyyy-MM-dd"
    val outputPattern = "dd/MM/yyyy"
    val inputFormat = SimpleDateFormat(inputPattern)
    val outputFormat = SimpleDateFormat(outputPattern)
    var date: Date? = null
    var str: String? = null
    try {
        date = inputFormat.parse(time)
        str = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return str
}


