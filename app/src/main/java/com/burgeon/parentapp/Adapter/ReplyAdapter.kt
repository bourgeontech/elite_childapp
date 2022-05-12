package com.burgeon.parentapp.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.ReplayDetails
import com.burgeon.parentapp.R
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.study.firebasecrash.Retrofit.ApiClientImage
import java.lang.Exception


class ReplyAdapter(
    var activity: FragmentActivity?,
    val fragmentManager: FragmentManager?,
    val service: List<ReplayDetails>?

) : RecyclerView.Adapter<ReplyAdapter.ReplyDialogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyDialogViewHolder {
        val view: ReplyDialogViewHolder =
            ReplyDialogViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.replay_list_row,
                    parent,
                    false
                )
            )

        return view
    }

    override fun getItemCount(): Int {
        return service?.size!!
    }

    override fun onBindViewHolder(holder: ReplyDialogViewHolder, position: Int) {
        holder.txtReply.text = service?.get(position)!!.comment
        holder.txtDate.text = service?.get(position)!!.postDate

        if (service?.get(position)!!.type.equals("audio")) {
            holder.txtReply.visibility = View.GONE
            holder.btnPlayaudio.visibility = View.VISIBLE
        } else {
            holder.txtReply.visibility = View.VISIBLE
            holder.btnPlayaudio.visibility = View.GONE
        }

        val head = ApiClientImage.BASE_URL.toString()
        val comment = service?.get(position)?.comment.toString()
        val url = head + comment

        holder.btnPlayaudio.setOnClickListener {
            println("button pressed................ggg")
            try {
                val mDialogView =
                    LayoutInflater.from(activity).inflate(R.layout.audio_dialog, null)
                val mBuilder = AlertDialog.Builder(activity!!)
                    .setView(mDialogView);

                // val  mAlertDialog = mBuilder.show()
                var audio_view_item: PlayerView =
                    mDialogView.findViewById(R.id.audio_view_item) as PlayerView
                var playerval_item = SimpleExoPlayer.Builder(activity!!).build()
                audio_view_item.player = playerval_item
                val uri = Uri.parse(url)
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

    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(activity, "exoplayer-codelab")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }


    class ReplyDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtReply = itemView.findViewById(R.id.txtReply) as TextView
        var txtDate = itemView.findViewById(R.id.txtDate) as TextView
        var btnPlayaudio = itemView.findViewById(R.id.btnPlayaudio) as TextView

    }
}
