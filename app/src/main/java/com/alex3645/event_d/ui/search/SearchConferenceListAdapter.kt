package com.alex3645.event_d.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.alex3645.event_d.R
import com.alex3645.event_d.model.Conference

class SearchConferenceListAdapter(private val context: FragmentActivity, var list: MutableList<Conference>): RecyclerView.Adapter<SearchConferenceListAdapter.ListViewHolder>() {

    override fun getItemCount(): Int {
        if (list != null)
            return list.size
        else{
            return 0;
        }
    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var card = itemView.findViewById<ConstraintLayout>(R.id.searchSimpleItem)
        var name = itemView.findViewById<TextView>(R.id.simple_nameTextBox)
        var info = itemView.findViewById<TextView>(R.id.simple_shortInfoTextBox)

    }

    interface onItemClickListener {
        fun itemDetail(conferenceId : String)
    }

    override fun onBindViewHolder(holder: SearchConferenceListAdapter.ListViewHolder, position: Int) {
        var conference = list[position]

        holder.name.text = conference.name
        holder.info.text = conference.description

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchConferenceListAdapter.ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.search_simple_item_layout, parent, false)
        return SearchConferenceListAdapter.ListViewHolder(itemView)
    }

}