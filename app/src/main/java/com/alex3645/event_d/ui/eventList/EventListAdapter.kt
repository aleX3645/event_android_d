package com.alex3645.event_d.ui.eventList

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
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.ui.search.SearchEventListAdapter

class EventListAdapter(private val context: FragmentActivity, var list: MutableList<Event>,
                       fragment: Fragment): RecyclerView.Adapter<EventListAdapter.ListViewHolder>() {

    private val listener: EventListAdapter.onItemClickListener

    init {

        this.listener = fragment as EventListAdapter.onItemClickListener
    }


    override fun getItemCount(): Int {
        if (list != null)
            return list.size
        else{
            return 0;
        }
    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.simple_nameTextBox)
        var info = itemView.findViewById<TextView>(R.id.simple_shortInfoTextBox)
    }

    interface onItemClickListener {
        fun itemDetail(eventId : Int)
    }

    public override fun onBindViewHolder(holder: EventListAdapter.ListViewHolder, position: Int) {

        var event = list[position]

        holder.name.text = event.name
        holder.info.text = event.description

        holder.itemView.setOnClickListener {
            listener.itemDetail(event.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListAdapter.ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.search_simple_item, parent, false)
        return EventListAdapter.ListViewHolder(itemView)
    }
}