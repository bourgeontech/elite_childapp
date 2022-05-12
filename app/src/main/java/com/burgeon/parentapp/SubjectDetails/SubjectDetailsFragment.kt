package com.burgeon.parentapp.SubjectDetails

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.fragment.app.Fragment
import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder
import cafe.adriel.androidaudiorecorder.model.AudioChannel
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate
import cafe.adriel.androidaudiorecorder.model.AudioSource
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import com.burgeon.parentapp.ViewDiscussion.ViewDiscussionFragment
import com.bumptech.glide.Glide


import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.fragment_subject_details.*
import okhttp3.ResponseBody
import java.io.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "contentId"


class SubjectDetailsFragment : Fragment(), SubjectDetailsView {

    private var userId: String? = ""
    private var fileextension: String = ""
    private var param1: String? = ""
    private var param2: String? = ""
    private var param3: String? = ""
    private var param4: String? = ""
    private var param5: String? = ""
    private val presenter = SubjectDetailsPresenter(this, SubjectDetailsInteractor())
    val filePath: String =
        Environment.getExternalStorageDirectory().toString() + "/recorded_audio.wav"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getString(ARG_PARAM4)
            param5 = it.getString(ARG_PARAM5)
        }

        if (param3 != null && param3 != "") {
            fileextension = File(param3).extension
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        var preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")

        if (param1 != null && param1 != "") {
            val imageurl = "https://img.youtube.com/vi/" + param1 + "/default.jpg"
            Glide.with(activity!!).load(imageurl).into(ivVideo)
        } else {
            ivVideo.visibility = View.GONE
        }

        tvDescription.text = param4

        llVideo.setOnClickListener {

            if (param1 != null && param1 != "") {

                try {
                    val standAlonePlayerIntent = YouTubeStandalonePlayer.createVideoIntent(
                        activity,
                        Constants.YOUTUBE_API_KEY,  // which you have created in step 3
                        param1,  // video which is to be played
                        100,  //The time, in milliseconds, where playback should start in the video
                        true,  //autoplay or not
                        false
                    )
                    activity?.startActivity(standAlonePlayerIntent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else {
                Toast.makeText(activity, "Video not available", Toast.LENGTH_LONG).show()
            }
        }

        llAudio.setOnClickListener {

        }

        llFiles.setOnClickListener {
            param3?.let { it1 -> presenter.getSubject(it1) }
        }

        ivBack.setOnClickListener {
            try {
                fragmentManager?.popBackStackImmediate()
            } catch (e: Exception) {
            }
        }

        tvHeadTitle.setOnClickListener {
            try {
                fragmentManager?.popBackStackImmediate()
            } catch (e: Exception) {
            }
        }

        llQuestion.setOnClickListener {
            presenter.sendQuestion(userId!!, param5!!, etQuestion?.text.toString())
        }

        llviewdiscussion.setOnClickListener {
            try {
                val discussionList =
                    ViewDiscussionFragment.newInstance(param5.toString())

                fragmentManager?.beginTransaction()?.replace(
                    R.id.fl_container,
                    discussionList
                )?.addToBackStack("")?.commit()

            } catch (e: Exception) {
            }
        }

        ivAudio.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context!!.checkSelfPermission(
                    Manifest.permission.RECORD_AUDIO
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                activity?.requestPermissions(
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    1001
                )
            } else {

                val color = resources.getColor(R.color.colorPrimaryDark)
                val requestCode = 0
                val recorder = AndroidAudioRecorder.with(this) // Required
                    .setFilePath(filePath)
                    .setColor(color)
                    .setRequestCode(requestCode) // Optional
                    .setSource(AudioSource.MIC)
                    .setChannel(AudioChannel.STEREO)
                    .setSampleRate(AudioSampleRate.HZ_48000)
                    .setAutoStart(true)
                    .setKeepDisplayOn(true) // Start recording
                    .recordFromFragment()
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(
            param1: String,
            param2: String,
            param3: String,
            name: String,
            contentId: String
        ) =
            SubjectDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, name)
                    putString(ARG_PARAM5, contentId)
                }
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

    private fun writeResponseBodyToDisk(
        body: ResponseBody?,
        title: String?,
        extension: String?
    ): Boolean {

        return try { // todo change the file location/name according to your needs
            val futureStudioIconFile =
                File(
                    Environment.getExternalStorageDirectory()
                        .toString() + "/" + File.separator + title + "." + extension
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
                //itemView.progress.visibility = View.GONE
                // Toast.makeText(NewsViewHolder.contextval, "file downloaded", Toast.LENGTH_SHORT).show()

                val map: MimeTypeMap = MimeTypeMap.getSingleton()
                val ext: String = MimeTypeMap.getFileExtensionFromUrl(futureStudioIconFile.name)
                var type: String? = map.getMimeTypeFromExtension(ext)

                if (type == null) type = "*/*"

                val intent = Intent(Intent.ACTION_VIEW)
                val data: Uri = Uri.fromFile(futureStudioIconFile)

                intent.setDataAndType(data, type)
                activity?.startActivity(intent)
                true
            } catch (e: IOException) {
                // Toast.makeText(contextval,"first catch",Toast.LENGTH_SHORT).show()
                e.printStackTrace()
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
            e.printStackTrace()
            //Toast.makeText(contextval,"second  catch",Toast.LENGTH_SHORT).show()
            false
        }
    }

    override fun showProgress() {
        pgProgress?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgProgress?.visibility = View.GONE
    }

    override fun setSubjectData(body: ResponseBody?) {
        writeResponseBodyToDisk(
            body,
            param4,
            fileextension
        )
    }

    override fun error() {
        try {
            Toast.makeText(activity, "There is some problem.Please try again", Toast.LENGTH_LONG)
                .show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun questionSuccess(msg: Any?) {
        try {
            etQuestion?.setText("")

            Toast.makeText(activity, "Your question posted successfully", Toast.LENGTH_LONG)
                .show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun questioError(msg: Any?) {
        try {
            Toast.makeText(activity, msg.toString(), Toast.LENGTH_LONG)
                .show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                sendAudio()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(activity, "Audio was not recorded", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun sendAudio() {
        presenter.sendAudio(userId!!, param5.toString(), filePath)
    }
}
