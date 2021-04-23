package com.alex3645.event_d.ui.conferencesList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.alex3645.event_d.R
import com.alex3645.event_d.model.Conference

class ConferenceListAdapter(private val context: FragmentActivity, var list: MutableList<Conference>,
                  fragment: Fragment): RecyclerView.Adapter<ConferenceListAdapter.ListViewHolder>() {

    private val listener: ConferenceListAdapter.onItemClickListener

    init {

        this.listener = fragment as ConferenceListAdapter.onItemClickListener
    }


    override fun getItemCount(): Int {
        if (list != null)
            return list.size
        else{
            return 0;
        }
    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var card = itemView.findViewById<CardView>(R.id.item_layout)
        var name = itemView.findViewById<TextView>(R.id.nameTextBox)
        var info = itemView.findViewById<TextView>(R.id.shortInfoTextBox)
    }

    interface onItemClickListener {
        fun itemDetail(conferenceId : Int)
    }

    public override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        Log.d("2222", "kek")

        var conference = list[position]

        holder.name.text = conference.name
        holder.info.text = conference.description

        holder.card.setOnClickListener {
            listener.itemDetail(conference.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        Log.d("22221", "kek")
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ListViewHolder(itemView)
    }
}