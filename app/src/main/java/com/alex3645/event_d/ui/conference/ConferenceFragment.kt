package com.alex3645.event_d.ui.conference

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alex3645.event_d.App
import com.alex3645.event_d.R
import com.alex3645.event_d.api.SessionManager
import com.alex3645.event_d.di.component.DaggerFragmentComponent
import com.alex3645.event_d.di.module.FragmentModule
import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.ui.conferencesList.ConferenceListFragment
import com.alex3645.event_d.ui.eventList.EventListFragment
import com.alex3645.event_d.ui.login.LoginContract
import com.alex3645.event_d.ui.login.LoginFragment
import com.alex3645.event_d.util.AnimationConsider
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.conference_layout.view.*
import kotlinx.android.synthetic.main.login_layout.view.*
import kotlinx.android.synthetic.main.recycler_fragment.view.*
import javax.inject.Inject

class ConferenceFragment(val conferenceID: Int): Fragment(), ConferenceContract.View {



    @Inject
    lateinit var presenter: ConferenceContract.Presenter

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        val conferenceComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        conferenceComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rootView = inflater!!.inflate(R.layout.conference_layout, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()

        initEvents()
        initView()
    }

    private fun initEvents(){
        rootView.scheduleButton.setOnClickListener {
            fragmentManager?.beginTransaction()
                    ?.setCustomAnimations(AnimationConsider.considerEnterAnimation(EventListFragment.TAG, EventListFragment.TAG), AnimationConsider.considerExitAnimation(EventListFragment.TAG, EventListFragment.TAG))
                    ?.disallowAddToBackStack()
                    ?.replace(R.id.frame, EventListFragment(conferenceID), EventListFragment.TAG)
                    ?.commit()
        }
    }

    private fun initView(){
        showProgress(true)
        presenter.loadConferenceById(conferenceID)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            rootView.conferenceProgressBar.visibility = View.VISIBLE
            rootView.visibility = View.GONE
        } else {
            rootView.conferenceProgressBar.visibility = View.GONE
            rootView.visibility = View.VISIBLE
        }
    }

    override fun showErrorMessage(error: String) {
        val toast = Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG)
        Log.d("error", error)
        toast.show()
    }

    override fun showConferenceDataSuccess(conferenceData: Conference) {
        rootView.conferenceTitle.text = conferenceData.name
        rootView.conferenceCategory.text = conferenceData.category.toString()
        rootView.conferenceStartDate.text = conferenceData.date_start.toString()
        rootView.conferenceEndDate.text = conferenceData.date_end.toString()
        rootView.conferencePlace.text = conferenceData.location
        rootView.conferenceDescription.text = conferenceData.description
    }

    companion object {
        val TAG: String = "ConferenceFragment"
    }
}