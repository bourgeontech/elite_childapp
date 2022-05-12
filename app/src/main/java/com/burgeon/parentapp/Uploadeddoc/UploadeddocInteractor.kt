package com.burgeon.parentapp.Uploadeddoc

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import com.app.yashqualitytesting.Datamodels.LoginDatamodel
import com.burgeon.parentapp.Datamodels.*
import com.study.firebasecrash.Retrofit.ApiClient
import com.study.firebasecrash.Retrofit.ApiClientImage
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*

/**
 * Created by Ajay K K on 2020-02-17.
 */
class UploadeddocInteractor {

    interface onStudentprofileListener {
        fun chapterContent(data: List<Uploadeddetails>?)
        fun chapterContentError(msg: String)

        fun deleteSuccess(msg: Any?)
        fun delError(msg: Any?)

        fun fileDownload(intent: Intent)
        fun fileDownloaderror()
    }

    fun gteDoc(
        student_id: String,
        homework_id: String,
        type: String,
        listener: onStudentprofileListener
    ) {
        val call: Call<Uploadedmain> = ApiClient.getClient.StudentViewUploads(
            student_id,
            homework_id, type
        )
        call.enqueue(object : Callback<Uploadedmain> {
            override fun onFailure(call: Call<Uploadedmain>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<Uploadedmain>,
                response: Response<Uploadedmain>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.chapterContent(response.body()?.data)
                    } else {
                        response.body()?.msg?.let { listener.chapterContentError(it) }
                    }
                }
            }

        });
    }

    fun removeDoc(
        student_id: String,
        homework_id: String,
        uploadId: String,
        listener: onStudentprofileListener
    ) {
        val call: Call<LoginDatamodel> = ApiClient.getClient.deleteUpload(
            student_id,
            homework_id, uploadId
        )
        call.enqueue(object : Callback<LoginDatamodel> {
            override fun onFailure(call: Call<LoginDatamodel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<LoginDatamodel>,
                response: Response<LoginDatamodel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.deleteSuccess(response.body()?.msg)
                    } else {
                        listener.delError(response.body()?.msg)
                    }
                }
            }

        });
    }

    fun downloadFile(file: String, listener: onStudentprofileListener) {

        val call: Call<ResponseBody> =
            ApiClientImage.getClient.downloadFileWithDynamicUrlSync(file)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {}

            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {

                if (response?.isSuccessful!!) {

                    var fileextension = File(file).extension


                    writeResponseBodyToDisk(
                        response.body(),
                        "file",
                        fileextension,listener
                    )
                }
            }
        })

    }

    private fun writeResponseBodyToDisk(
        body: ResponseBody?,
        title: String?,
        extension: String?,
        listener: onStudentprofileListener
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

                val map: MimeTypeMap = MimeTypeMap.getSingleton()
                val ext: String = MimeTypeMap.getFileExtensionFromUrl(futureStudioIconFile.name)
                var type: String? = map.getMimeTypeFromExtension(ext)

                if (type == null) type = "*/*"

                val intent = Intent(Intent.ACTION_VIEW)
                val data: Uri = Uri.fromFile(futureStudioIconFile)

                intent.setDataAndType(data, type)

                listener.fileDownload(intent)
               // HomeworkViewHolder.contextval.startActivity(intent)
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
}