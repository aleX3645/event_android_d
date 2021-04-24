package com.alex3645.event_d.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alex3645.event_d.R
import com.alex3645.event_d.di.component.DaggerFragmentComponent
import com.alex3645.event_d.di.module.FragmentModule
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.model.User
import com.alex3645.event_d.ui.event.EventContract
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.event_layout.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.user_layout.view.*
import javax.inject.Inject

class UserFragment(val userID: Int): Fragment(), UserContract.View {
    @Inject
    lateinit var presenter: UserContract.Presenter

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        val userComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        userComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rootView = inflater!!.inflate(R.layout.user_layout, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()

        initView()
    }

    private fun initView(){
        showProgress(true)
        presenter.loadUserById(userID)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            rootView.userProgressBar.visibility = View.VISIBLE
            rootView.visibility = View.GONE
        } else {
            rootView.userProgressBar.visibility = View.GONE
            rootView.visibility = View.VISIBLE
        }
    }

    override fun showErrorMessage(error: String) {
        val toast = Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG)
        Log.d("error", error)
        toast.show()
    }

    override fun showUserDataSuccess(userData: User) {
        Log.d("test", userData.toString())
        rootView.userName.text = userData.name + " " + userData.surname
        rootView.emailTextView.text = userData.email
        rootView.phoneNumberTextView.text = userData.phone
        rootView.userInfoTextView.text = userData.description
    }

    companion object {
        val TAG: String = "UserFragment"
    }
}