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
import com.alex3645.event_d.model.User

class SearchUserListAdapter(private val context: FragmentActivity, var list: MutableList<User>): RecyclerView.Adapter<SearchUserListAdapter.ListViewHolder>() {

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

    override fun onBindViewHolder(holder: SearchUserListAdapter.ListViewHolder, position: Int) {
        var user = list[position]

        holder.name.text = user.name + " " + user.surname
        holder.info.text = user.login
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserListAdapter.ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.search_simple_item_layout, parent, false)
        return SearchUserListAdapter.ListViewHolder(itemView)
    }

}