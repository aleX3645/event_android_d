package com.alex3645.event_d.ui.eventsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alex3645.event_d.R
import com.alex3645.event_d.di.component.DaggerFragmentComponent
import com.alex3645.event_d.di.module.FragmentModule
import com.alex3645.event_d.model.Conference
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.recycler_fragment.*
import kotlinx.android.synthetic.main.recycler_fragment.view.*
import javax.inject.Inject


class ConferenceListFragment: Fragment(), ConferenceListContract.View, ConferenceListAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: ConferenceListContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): ConferenceListFragment {
        return ConferenceListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater!!.inflate(R.layout.recycler_fragment, container, false)
        val r = rootView.recyclerView

        r.adapter = ConferenceListAdapter(activity!!, ArrayList<Conference>(), this)
        r.layoutManager = LinearLayoutManager(activity)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            rootView.progressBar.visibility = View.VISIBLE
        } else {
            rootView.progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    override fun loadDataSuccess(list: List<Conference>) {
        Log.d("!!!", list.toString())
        (rootView.recyclerView.adapter as ConferenceListAdapter).list.addAll(list)
        (rootView.recyclerView.adapter as ConferenceListAdapter).notifyDataSetChanged()
    }

    override fun itemDetail(postId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        listComponent.inject(this)
    }

    private fun initView() {
        presenter.loadData()
    }

    companion object {
        val TAG: String = "ConferenceListFragment"
    }
}