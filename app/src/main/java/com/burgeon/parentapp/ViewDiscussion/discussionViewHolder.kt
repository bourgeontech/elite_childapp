package com.kotlinpagination

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.Questions
import com.burgeon.parentapp.R
import com.burgeon.parentapp.Replaylist.ReplaylistFragment
import com.burgeon.parentapp.Uploadeddoc.UploadeddocInteractor
import com.burgeon.parentapp.Utils.Constants
import com.burgeon.parentapp.Utils.PreferenceRequestHelper
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.study.firebasecrash.Retrofit.ApiClientImage
import kotlinx.android.synthetic.main.discussion_row.view.*
import okhttp3.ResponseBody
import java.io.*


class discussionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var mediaSource: MediaSource
    private lateinit var playerval: SimpleExoPlayer
    private var greenProgressbar: Drawable? = null
    private var TAG: String = ""

    fun bind(news: Questions?, contentId: Any?) {
        if (news != null) {

            itemView.tvQuestion.text = news.comment
            itemView.tvStudent.text = news.firstname

            if (news.studentId.equals(userid)) {
                itemView.tvStudent.setTextColor(Color.parseColor("#DF0756F7"))
            } else {
                itemView.tvStudent.setTextColor(Color.parseColor("#000000"))
            }

            if (news.type.equals("audio")) {
                itemView.btnPlayaudio.visibility = View.VISIBLE
                itemView.tvQuestion.visibility = View.GONE
            } else {
                itemView.btnPlayaudio.visibility = View.GONE
                itemView.tvQuestion.visibility = View.VISIBLE
            }

            /*  val iconFont =
                  Typeface.createFromAsset(
                      contextval?.getAssets(),
                      "audio-player-view-font-custom.ttf"
                  )
              itemView.playCustomFonts.setTypeface(iconFont)*/

            val head = ApiClientImage.BASE_URL.toString()
            val comment = news.comment.toString()
            val url = head + comment

            /* itemView.playCustomFonts.withUrl(url);

             itemView.playCustomFonts.setOnAudioPlayerViewListener(object :
                 AudioPlayerView.OnAudioPlayerViewListener {
                 override fun onAudioFinished() {
                 }

                 override fun onAudioPreparing() {
                     if ((contextval as MainActivity).getValue() != null)
                         (contextval as MainActivity).getValue()?.destroy()

                     (contextval as MainActivity).setValue(itemView.playCustomFonts)
                 }

                 override fun onAudioReady() {
                 }
             })*/

            /* itemView.tvPlay.setOnClickListener {

                 itemView.pgbar?.visibility = View.VISIBLE
                 try {
                     val mediaPlayer: MediaPlayer? = MediaPlayer.create(contextval, Uri.parse(url))
                     // mediaPlayer?.start()
                     mediaPlayer?.setOnPreparedListener(OnPreparedListener {
                         mediaPlayer?.start()
                     })

                     mediaPlayer?.setOnCompletionListener {
                         itemView.pgbar?.visibility = View.GONE
                     }
                 } catch (e: Exception) {
                     var toast = Toast.makeText(contextval, "Error", Toast.LENGTH_SHORT)
                     toast.show()
                 }
             }*/

            /*  itemView.play_audio_lay.setOnClickListener {
                  *//* itemView.progress_bar.visibility = View.VISIBLE
                 try {
                     val mediaPlayer: MediaPlayer? = MediaPlayer.create(contextval, Uri.parse(url))
                     // mediaPlayer?.start()
                     mediaPlayer?.setOnPreparedListener(OnPreparedListener {
                         mediaPlayer?.start()
                     })

                     mediaPlayer?.setOnCompletionListener {
                         itemView.pgbar?.visibility = View.GONE
                     }
                 } catch (e: Exception) {
                     var toast = Toast.makeText(contextval, "Error", Toast.LENGTH_SHORT)
                     toast.show()
                 }*//*



            }*/


            itemView.tvReply.setOnClickListener {
                try {
                    val replaylist =
                        ReplaylistFragment.newInstance(news?.id!!)
                    manager?.beginTransaction()?.replace(
                        R.id.fl_container,
                        replaylist
                    )?.addToBackStack("")?.commit()
                } catch (e: Exception) {
                }
            }

            itemView.btnPlayaudio.setOnClickListener {

                try {
                    val mDialogView =
                        LayoutInflater.from(contextval).inflate(R.layout.audio_dialog, null)

                    val mBuilder = AlertDialog.Builder(contextval)
                        .setView(mDialogView)
                    var audio_view_item =
                        mDialogView.findViewById(R.id.audio_view_item) as PlayerView

                    var playerval_item = SimpleExoPlayer.Builder(contextval).build()
                    audio_view_item.player = playerval_item
                    val uri = Uri.parse(url)
                    println("button pressed................ggg "+uri)
                    var mediaSourceval = buildMediaSource(uri)
                    playerval_item.prepare(mediaSourceval, false, false)

                    mBuilder.setPositiveButton("Close") { dialogInterface, which ->
                        if (audio_view_item != null) {
                            audio_view_item?.player?.stop()
                            audio_view_item?.player?.release()
                            audio_view_item?.player = null;
                        }
                    }
                    val alertDialog: AlertDialog = mBuilder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                } catch (e: Exception) {
                }

            }

            /* playerval = SimpleExoPlayer.Builder(contextval).build()
             itemView.audio_view.player = playerval
             val uri = Uri.parse(url)
             mediaSource = buildMediaSource(uri)
             playerval.prepare(mediaSource, false, false)*/

            /* fun downloadFile(
                 file: String,
                 listener: UploadeddocInteractor.onStudentprofileListener
             ) {

                 val call: Call<ResponseBody> =
                     ApiClientImage.getClient.downloadFileWithDynamicUrlSync(file)
                 call.enqueue(object : Callback<ResponseBody> {
                     override fun onFailure(call: Call<ResponseBody>, t: Throwable) {}

                     override fun onResponse(
                         call: Call<ResponseBody>,
                         response: Response<ResponseBody>
                     ) {
                         if (response?.isSuccessful!!) {

                         }
                     }
                 })
             }
 */

        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(contextval, "exoplayer-codelab")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    private fun writeResponseBodyToDisk(
        body: ResponseBody?,
        title: String?,
        extension: String?,
        listener: UploadeddocInteractor.onStudentprofileListener
    ): Boolean {

        return try { // todo change the file location/name according to your needs
            val futureStudioIconFile =
                File(
                    Environment.getExternalStorageDirectory()
                        .toString() + "/" + File.separator + title + "." + extension
                )

            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(20000)
                val fileSize = body!!.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = body.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)

                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream?.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                }
                outputStream?.flush()

                val map: MimeTypeMap = MimeTypeMap.getSingleton()
                val ext: String = MimeTypeMap.getFileExtensionFromUrl(futureStudioIconFile.name)
                var type: String? = map.getMimeTypeFromExtension(ext)

                true
            } catch (e: IOException) {
                // Toast.makeText(contextval,"first catch",Toast.LENGTH_SHORT).show()
                listener.fileDownloaderror()
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
            listener.fileDownloaderror()
            false
        }
    }


    companion object {
        //private lateinit var progressbar: ProgressBar
        private var userid: String? = ""
        private lateinit var contextval: AppCompatActivity
        private lateinit var manager: FragmentManager

        fun create(parent: ViewGroup): discussionViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.discussion_row, parent, false)

            manager = (parent.context as AppCompatActivity).supportFragmentManager
            contextval = parent.context as AppCompatActivity

            var preferenceObj = PreferenceRequestHelper(contextval)
            userid = preferenceObj.getStringValue(Constants.PRES_STUDENT_ID, "")

            //progressbar = view.findViewById(R.id.progress_bar) as ProgressBar
            return discussionViewHolder(view)
        }
    }
}

/*class DownloadZipFileTask internal constructor() :
    AsyncTask<ResponseBody, Pair<Int, Long>, String?>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }


    override fun doInBackground(vararg urls: ResponseBody?): String? {
        saveToDisk(urls[0]!!, "test.wav");
        return null;
    }

    override fun onProgressUpdate(vararg progress: Pair<Int, Long>?) {
        Log.d("API123", " " + progress[0]?.second + " ");
        if (progress[0]?.first == 100)
            Toast.makeText(discussionViewHolder.contextval, "File downloaded successfully", Toast.LENGTH_SHORT)
                .show();


        if (progress[0]?.second != null) {
            if (progress[0]?.second!! > 0) {
                val currentProgress =
                    (progress[0]!!.first as Double / progress[0]!!.second.toDouble() * 100).toInt()
                discussionViewHolder.progressbar?.setProgress(currentProgress)
            }
        }

        if (progress[0]?.first == -1) {
            Toast.makeText(discussionViewHolder.contextval, "Download failed", Toast.LENGTH_SHORT).show();
        }
    }

    fun doProgress(progressDetails: Pair<Int, Long>?) {
        publishProgress(progressDetails)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }
}

fun saveToDisk(body: ResponseBody, filename: String) {
    try {
        val destinationFile = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            filename
        )
        var inputStream: InputStream? = null
        var outputStream: OutputStream? = null
        try {
            inputStream = body.byteStream()
            outputStream = FileOutputStream(destinationFile)
            val data = ByteArray(4096)
            var count: Int
            var progress = 0
            val fileSize = body.contentLength()
            Log.d(Constraints.TAG, "File Size=$fileSize")
            while (inputStream.read(data).also { count = it } != -1) {
                outputStream.write(data, 0, count)
                progress += count
                val pairs = Pair(progress, fileSize)
                doProgress(pairs)
                Log.d(
                    Constraints.TAG,
                    "Progress: " + progress + "/" + fileSize + " >>>> " + progress.toFloat() / fileSize
                )
            }
            outputStream.flush()
            Log.d(Constraints.TAG, destinationFile.parent)
            val pairs = Pair(100, 100L)
            downloadZipFileTask.doProgress(pairs)
            return
        } catch (e: IOException) {
            e.printStackTrace()
            val pairs = Pair(-1, java.lang.Long.valueOf(-1))
            downloadZipFileTask.doProgress(pairs)
            Log.d(Constraints.TAG, "Failed to save the file!")
            return
        } finally {
            inputStream?.close()
            outputStream?.close()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Log.d(Constraints.TAG, "Failed to save the file!")
        return
    }
}*/
