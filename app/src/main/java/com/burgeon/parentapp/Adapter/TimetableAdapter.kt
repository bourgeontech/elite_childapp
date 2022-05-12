package com.burgeon.parentapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.burgeon.parentapp.Datamodels.TimetableDatamodel
import android.widget.LinearLayout


/**
 * Created by Ajay K K on 2020-02-13.
 */
class TimetableAdapter(
    val items: TimetableDatamodel,
    val context: FragmentActivity?,
    val manager: FragmentManager,
    val positionVal: Int
) : RecyclerView.Adapter<TimetableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableViewHolder {
        val view: TimetableViewHolder =
            TimetableViewHolder(
                LayoutInflater.from(context).inflate(
                    com.burgeon.parentapp.R.layout.row_time_table,
                    parent,
                    false
                )
            )
        return view;
    }

    override fun getItemCount(): Int {
        if (positionVal == 0)
            return items.monday?.size!!
        else if (positionVal == 1)
            return items.tuesday?.size!!
        else if (positionVal == 2)
            return items.wednesday?.size!!
        else if (positionVal == 3)
            return items.thursday?.size!!
        else if (positionVal == 4)
            return items.friday?.size!!
        else if (positionVal == 5)
            return items.saturday?.size!!
        else if (positionVal == 6)
            return items.sunday?.size!!
        else
            return 0

    }

    override fun onBindViewHolder(holder: TimetableViewHolder, position: Int) {
        if (positionVal == 0)
            holder.tvName.text = items.monday?.get(position)?.name!!
        else if (positionVal == 1)
            holder.tvName.text = items.tuesday?.get(position)?.name!!
        else if (positionVal == 2)
            holder.tvName.text = items.wednesday?.get(position)?.name!!
        else if (positionVal == 3)
            holder.tvName.text = items.thursday?.get(position)?.name!!
        else if (positionVal == 4)
            holder.tvName.text = items.friday?.get(position)?.name!!
        else if (positionVal == 5)
            holder.tvName.text = items.saturday?.get(position)?.name!!
        else if (positionVal == 6)
            holder.tvName.text = items.sunday?.get(position)?.name!!
        else
            holder.tvName.text = ""

      /*  val rnd = Random()
        val currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.llContainer.setBackgroundColor(currentColor)*/
    }
}

class TimetableViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val tvName = itemView.findViewById(com.burgeon.parentapp.R.id.tvName) as TextView
    val llContainer = itemView.findViewById(com.burgeon.parentapp.R.id.llContainer) as LinearLayout
}
