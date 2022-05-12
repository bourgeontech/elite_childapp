package com.kotlinpagination

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager

import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.CircularDetails
import com.burgeon.parentapp.R
import com.bumptech.glide.Glide
import com.study.firebasecrash.Retrofit.ApiClientImage
import kotlinx.android.synthetic.main.row_circular_list_1.view.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.io.File.separator


class CircularViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var greenProgressbar: Drawable? = null
    private var TAG: String = ""

    fun bind(news: CircularDetails?) {
        if (news != null) {

            itemView.tvdate.text = news.date
            itemView.tvTitle.text = news.title
            itemView.tvDesc.text = news.note


            if (news.type.equals("pdf")) {
                greenProgressbar =
                    ContextCompat.getDrawable(contextval!!, R.drawable.ic_pdf)
            } else if (news.type.equals("docx")) {
                greenProgressbar =
                    ContextCompat.getDrawable(contextval!!, R.drawable.ic_word)
            } else if ((news.type.equals("png")) || (news.type.equals("jpeg")) || (news.type.equals(
                    "jpg"
                ))
            ) {
                greenProgressbar =
                    ContextCompat.getDrawable(contextval!!, R.drawable.ic_photo)
            } else
                greenProgressbar =
                    ContextCompat.getDrawable(contextval!!, R.drawable.ic_excel)

            Glide.with(contextval!!).load(greenProgressbar).into(itemView.ivAttachment)
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

                            writeResponseBodyToDisk(response.body(), news.title, news.type)

                            /* if(news.type.equals("pdf")) {
                                 val writtenToDisk: Boolean =
                                     writeResponseBodyToDiskPDF(response.body(), news.title)
                             }else if(news.type.equals("docx")) {
                                 val writtenToDisk2: Boolean =
                                     writeResponseBodyToDiskDOC(response.body(), news.title)
                             }else if(news.type.equals("png")) {
                                 val writtenToDisk2: Boolean =
                                     writeResponseBodyToDiskPNG(response.body(), news.title)
                             }else if(news.type.equals("jpeg")) {
                                 val writtenToDisk2: Boolean =
                                     writeResponseBodyToDiskJPEG(response.body(), news.title)
                             }else if(news.type.equals("jpg")) {
                                 val writtenToDisk2: Boolean =
                                     writeResponseBodyToDiskJPEG(response.body(), news.title)
                             }else if(news.type.equals("jpg")) {
                                 val writtenToDisk2: Boolean =
                                     writeResponseBodyToDiskJPEG(response.body(), news.title)
                             }*/
                        }
                    }
                })
            }


         /*   itemView.llDownload.setOnClickListener {

                PRDownloader.download("", "", "")
                    .build()
                    .setOnStartOrResumeListener {

                    }

                    .setOnPauseListener {

                    }
                    .setOnCancelListener {

                    }

                    .setOnPauseListener {

                    }

                    .setOnProgressListener {

                    }

                    .start(object : OnDownloadListener {
                        override fun onDownloadComplete() {

                        }

                        override fun onError(error: Error?) {
                        }

                    });
*/

                /*}*/


                /* itemView.llDirection.setOnClickListener {


                     if (news.gpsLocation != null &&
                         news.gpsLocation.length > 0
                     ) {

                         val intent = Intent(
                             android.content.Intent.ACTION_VIEW,
                             Uri.parse("geo:0,0?q=" + news.gpsLocation)
                         )
                         this.itemView.context.startActivity(intent)
                     }
                 }*/
            }
        }


        private fun writeResponseBodyToDisk(
            body: ResponseBody?,
            title: String?,
            type: String?
        ): Boolean {

            return try { // todo change the file location/name according to your needs
                val futureStudioIconFile =
                    File(Environment.getExternalStorageDirectory().toString() + "/" + separator + title + "." + type)

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
                        Log.d(TAG, "file download: $fileSizeDownloaded of $fileSize")
                    }
                    outputStream?.flush()
                    itemView.progress.visibility = View.GONE
                    Toast.makeText(contextval, "file downloaded", Toast.LENGTH_SHORT).show()
                    val builder = VmPolicy.Builder()
                    StrictMode.setVmPolicy(builder.build())
                    // val file = File(Environment.getExternalStorageDirectory().toString()+"/"  + separator + title +".pdf")
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

        private fun writeResponseBodyToDiskPDF(body: ResponseBody?, title: String?): Boolean {

            return try { // todo change the file location/name according to your needs
                val futureStudioIconFile =
                    File(Environment.getExternalStorageDirectory().toString() + "/" + separator + title + ".pdf")

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
                        Log.d(TAG, "file download: $fileSizeDownloaded of $fileSize")
                    }
                    outputStream?.flush()
                    itemView.progress.visibility = View.GONE
                    Toast.makeText(contextval, "file downloaded", Toast.LENGTH_SHORT).show()
                    val builder = VmPolicy.Builder()
                    StrictMode.setVmPolicy(builder.build())
                    // val file = File(Environment.getExternalStorageDirectory().toString()+"/"  + separator + title +".pdf")
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

        private fun writeResponseBodyToDiskDOC(body: ResponseBody?, title: String?): Boolean {

            return try { // todo change the file location/name according to your needs
                val futureStudioIconFile =
                    File(Environment.getExternalStorageDirectory().toString() + "/" + separator + title + ".docx")

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
                        Log.d(TAG, "file download: $fileSizeDownloaded of $fileSize")
                    }
                    outputStream?.flush()
                    itemView.progress.visibility = View.GONE
                    Toast.makeText(contextval, "file downloaded", Toast.LENGTH_SHORT).show()
                    val builder = VmPolicy.Builder()
                    StrictMode.setVmPolicy(builder.build())
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

        private fun writeResponseBodyToDiskPNG(body: ResponseBody?, title: String?): Boolean {

            return try { // todo change the file location/name according to your needs
                val futureStudioIconFile =
                    File(Environment.getExternalStorageDirectory().toString() + "/" + separator + title + ".png")

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
                        Log.d(TAG, "file download: $fileSizeDownloaded of $fileSize")
                    }
                    outputStream?.flush()
                    itemView.progress.visibility = View.GONE
                    Toast.makeText(contextval, "file downloaded", Toast.LENGTH_SHORT).show()
                    val builder = VmPolicy.Builder()
                    StrictMode.setVmPolicy(builder.build())
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

        private fun writeResponseBodyToDiskJPEG(body: ResponseBody?, title: String?): Boolean {

            return try { // todo change the file location/name according to your needs
                val futureStudioIconFile =
                    File(Environment.getExternalStorageDirectory().toString() + "/" + separator + title + ".jpeg")

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
                        Log.d(TAG, "file download: $fileSizeDownloaded of $fileSize")
                    }
                    outputStream?.flush()
                    itemView.progress.visibility = View.GONE
                    Toast.makeText(contextval, "file downloaded", Toast.LENGTH_SHORT).show()
                    val builder = VmPolicy.Builder()
                    StrictMode.setVmPolicy(builder.build())
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

//    private fun downloadZipFile() {
//
//        val call: Call<Circular> = ApiClient.getClient.getCircular(
//            "1", "10"
//        )
//
//        call.enqueue(object : Callback<Circular> {
//            override fun onFailure(call: Call<Circular>, t: Throwable) {
//
//            }
//
//            override fun onResponse(
//                call: Call<Circular>,
//                response: Response<Circular>
//            ) {
//
//                if (response.isSuccessful) {
//
//                }
//
//            }
//
//        })
//    }

        /*   private fun writeResponseBodyToDisk(body: ResponseBody): Boolean {
               try {
                   // todo change the file location/name according to your needs
                   val futureStudioIconFile =
                       File(getExternalFilesDirs(contextval) + separator + "Future Studio Icon.png")

                   var inputStream: InputStream? = null
                   var outputStream: OutputStream? = null

                   try {
                       val fileReader = ByteArray(4096)

                       val fileSize = body.contentLength()
                       var fileSizeDownloaded: Long = 0

                       inputStream = body.byteStream()
                       outputStream = FileOutputStream(futureStudioIconFile)

                       while (true) {
                           val read = inputStream!!.read(fileReader)

                           if (read == -1) {
                               break
                           }

                           outputStream!!.write(fileReader, 0, read)

                           fileSizeDownloaded += read.toLong()

                           Log.d("File Download: ", "$fileSizeDownloaded of $fileSize")
                       }

                       outputStream!!.flush()

                       return true
                   } catch (e: IOException) {
                       return false
                   } finally {
                       if (inputStream != null) {
                           inputStream!!.close()
                       }

                       if (outputStream != null) {
                           outputStream!!.close()
                       }
                   }
               } catch (e: IOException) {
                   return false
               }

           }*/


        companion object {
            private lateinit var contextval: AppCompatActivity
            private lateinit var manager: FragmentManager

            fun create(parent: ViewGroup): CircularViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_circular_list_1, parent, false)

                manager = (parent.context as AppCompatActivity).supportFragmentManager
                contextval = parent.context as AppCompatActivity

                return CircularViewHolder(view)
            }
        }
    }