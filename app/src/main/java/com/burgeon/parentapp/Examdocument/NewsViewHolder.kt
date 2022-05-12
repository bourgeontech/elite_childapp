package com.burgeon.parentapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.burgeon.parentapp.Datamodels.HomeworkDetailsmodel
import com.burgeon.parentapp.SubjectList.uploadDocFragment
import com.burgeon.parentapp.Uploadeddoc.UploadeddocFragment
import com.bumptech.glide.Glide

import com.study.firebasecrash.Retrofit.ApiClientImage
import kotlinx.android.synthetic.main.row_homework_list.view.*
import kotlinx.android.synthetic.main.row_homework_list.view.ivAttachment
import kotlinx.android.synthetic.main.row_homework_list.view.llDownload
import kotlinx.android.synthetic.main.row_homework_list.view.progress
import kotlinx.android.synthetic.main.row_homework_list.view.tvDesc
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.io.File.separator
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var greenProgressbar: Drawable? = null

    fun bind(news: HomeworkDetailsmodel?) {
        if (news != null) {


            // itemView.tvDate =
            itemView.tvDesc.text = news.description
            itemView.tvSubject.text = news.subject
            itemView.tvCompdate.text = "Submission date is " + parseDateToddMMyyyy(news.submitDate)

            val strs = news.homeworkDate?.split("-")?.toTypedArray()
            itemView.tvDate.text = strs?.get(2)


            var mydate = parseDateToddMMyyyy(news.homeworkDate)
            val time: Array<String> = news.homeworkDate?.split("-")!!.toTypedArray()
            val year = time[0].trim { it <= ' ' }.toInt()
            val month = time[1].trim { it <= ' ' }.toInt()
            val day = time[2].trim { it <= ' ' }.toInt()
            val calendar: Calendar =
                GregorianCalendar(
                    year,
                    month,
                    day
                ) // Note that Month value is 0-based. e.g., 0 for January.

            val reslut = calendar[Calendar.DAY_OF_WEEK]
            val result1 = reslut - 1
            when (result1) {
                Calendar.MONDAY -> itemView.tvDate.text = "MON"
                Calendar.TUESDAY -> itemView.tvDate.text = "TUE"
                Calendar.WEDNESDAY -> itemView.tvDate.text = "WED"
                Calendar.THURSDAY -> itemView.tvDate.text = "THU"
                Calendar.FRIDAY -> itemView.tvDate.text = "FRI"
                Calendar.SATURDAY -> itemView.tvDate.text = "SAT"
                Calendar.SUNDAY -> itemView.tvDate.text = "SUN"

            }
            itemView.tvStartdate.text = mydate


            if (news.fileType != null && news.fileType != "")
                itemView.llDownload.visibility = View.VISIBLE
            else
                itemView.llDownload.visibility = View.GONE


            if (news.fileType.equals("pdf")) {
                greenProgressbar =
                    ContextCompat.getDrawable(contextval!!, R.drawable.ic_pdf)
            } else if (news.fileType.equals("docx")) {
                greenProgressbar =
                    ContextCompat.getDrawable(contextval!!, R.drawable.ic_word)
            } else if ((news.fileType.equals("png")) || (news.fileType.equals("jpeg")) || (news.fileType.equals(
                    "jpg"
                ))
            ) {
                greenProgressbar =
                    ContextCompat.getDrawable(contextval!!, R.drawable.ic_photo)
            } else
                greenProgressbar =
                    ContextCompat.getDrawable(contextval!!, R.drawable.ic_excel)

            Glide.with(contextval!!).load(greenProgressbar).into(itemView.ivAttachment)


            itemView.llUploaddoc.setOnClickListener {
                try {
                    val discussionList =
                        uploadDocFragment.newInstance(news?.id!!, "exam")

                    manager?.beginTransaction()?.replace(
                        R.id.fl_container,
                        discussionList
                    )?.addToBackStack("")?.commit()
                } catch (e: Exception) {
                }
            }

            itemView.llViewdoc.setOnClickListener {
                try {
                    val uloadeddoc =
                        UploadeddocFragment.newInstance(news?.id!!, "1")

                    manager?.beginTransaction()?.replace(
                        R.id.fl_container,
                        uloadeddoc
                    )?.addToBackStack("")?.commit()
                } catch (e: Exception) {
                }
            }


            itemView.llDownload.setOnClickListener {

                itemView.progress.visibility = View.VISIBLE
                val call: Call<ResponseBody> =
                    ApiClientImage.getClient.downloadFileWithDynamicUrlSync(news?.file!!)
                call.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {

                        if (response?.isSuccessful!!) {
                            writeResponseBodyToDisk(
                                response.body(),
                                news.subject,
                                news.fileType
                            )

                            /*        if (news.fileType.equals("pdf")) {
                                        val writtenToDisk: Boolean =
                                            writeResponseBodyToDisk(
                                                response.body(),
                                                news.subject,
                                                news.fileType
                                            )
                                    } else if (news.fileType.equals("docx")) {
                                        val writtenToDisk2: Boolean =
                                            writeResponseBodyToDisk(
                                                response.body(),
                                                news.subject,
                                                news.fileType
                                            )
                                    } else if (news.fileType.equals("png")) {
                                        val writtenToDisk2: Boolean =
                                            writeResponseBodyToDisk(
                                                response.body(),
                                                news.subject,
                                                news.fileType
                                            )
                                    } else if (news.fileType.equals("jpeg")) {
                                        val writtenToDisk2: Boolean =
                                            writeResponseBodyToDisk(
                                                response.body(),
                                                news.subject,
                                                news.fileType
                                            )
                                    } else if (news.fileType.equals("jpg")) {
                                        val writtenToDisk2: Boolean =
                                            writeResponseBodyToDisk(
                                                response.body(),
                                                news.subject,
                                                news.fileType
                                            )
                                    }*/
                        }
                    }
                })
            }
        }
    }

    private fun writeResponseBodyToDisk(
        body: ResponseBody?,
        title: String?,
        extension: String?
    ): Boolean {

        return try { // todo change the file location/name according to your needs
            val futureStudioIconFile =
                File(
                    Environment.getExternalStorageDirectory()
                        .toString() + "/" + separator + title + "." + extension
                )

            println(body.toString() + "................body")
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(20000)
                val fileSize = body!!.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = body.byteStream()
                println(inputStream.toString() + "................body1")
                println(inputStream.toString() + "................body1")
                outputStream = FileOutputStream(futureStudioIconFile)
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream?.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                    Log.d("TAG", "file download: $fileSizeDownloaded of $fileSize")
                }
                outputStream?.flush()
                itemView.progress.visibility = View.GONE
                Toast.makeText(contextval, "file downloaded", Toast.LENGTH_SHORT).show()

                val map: MimeTypeMap = MimeTypeMap.getSingleton()
                val ext: String = MimeTypeMap.getFileExtensionFromUrl(futureStudioIconFile.name)
                var type: String? = map.getMimeTypeFromExtension(ext)

                if (type == null) type = "*/*"

                val intent = Intent(Intent.ACTION_VIEW)
                val data: Uri = Uri.fromFile(futureStudioIconFile)

                intent.setDataAndType(data, type)
                contextval.startActivity(intent)
                true
            } catch (e: IOException) {
                // Toast.makeText(contextval,"first catch",Toast.LENGTH_SHORT).show()

                false
            } finally {
                if (inputStream != null) {
                    inputStream.close()
                }
                if (outputStream != null) {
                    outputStream.close()
                }
            }
        } catch (e: IOException) {
            //Toast.makeText(contextval,"second  catch",Toast.LENGTH_SHORT).show()
            false
        }
    }


    companion object {
        private lateinit var contextval: AppCompatActivity
        private lateinit var manager: FragmentManager

        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_homework_list, parent, false)

            manager = (parent.context as AppCompatActivity).supportFragmentManager
            contextval = parent.context as AppCompatActivity

            return NewsViewHolder(view)
        }
    }

    fun parseDateToddMMyyyy(time: String?): String? {
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
}