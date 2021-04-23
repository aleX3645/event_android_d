package com.alex3645.event_d.ui.conferencesList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex3645.event_d.R
import com.alex3645.event_d.di.component.DaggerFragmentComponent
import com.alex3645.event_d.di.module.FragmentModule
import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.ui.conference.ConferenceFragment
import com.alex3645.event_d.ui.login.LoginFragment
import com.alex3645.event_d.util.AnimationConsider
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

    override fun itemDetail(conId: Int) {
        fragmentManager?.beginTransaction()
                ?.setCustomAnimations(AnimationConsider.considerEnterAnimation(TAG, ConferenceFragment.TAG),AnimationConsider.considerExitAnimation(TAG, LoginFragment.TAG))
                ?.disallowAddToBackStack()
                ?.replace(R.id.frame, ConferenceFragment(conId), ConferenceFragment.TAG)
                ?.commit()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        listComponent.inject(this)
    }

    private fun initView() {
        presenter.loadAllConferenceData()
    }

    companion object {
        val TAG: String = "ConferenceListFragment"
    }
}