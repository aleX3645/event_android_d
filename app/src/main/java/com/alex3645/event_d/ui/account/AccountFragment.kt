package com.alex3645.event_d.ui.account

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
import com.alex3645.event_d.model.User
import com.alex3645.event_d.ui.eventList.EventListFragment
import com.alex3645.event_d.ui.login.LoginContract
import com.alex3645.event_d.ui.registration.RegistrationFragment
import com.alex3645.event_d.util.AnimationConsider
import kotlinx.android.synthetic.main.account_layout.view.*
import kotlinx.android.synthetic.main.login_layout.view.*
import kotlinx.android.synthetic.main.recycler_fragment.view.*
import kotlinx.android.synthetic.main.user_layout.view.*
import javax.inject.Inject

class AccountFragment(): Fragment(), AccountContract.View {

    @Inject
    lateinit var presenter: AccountContract.Presenter

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        val loginComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        loginComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rootView = inflater!!.inflate(R.layout.account_layout, container, false)
        val session = SessionManager(context!!)
        presenter.loadUserByLogin(session.fetchLogin()!!)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            rootView.accountProgressBar.visibility = View.VISIBLE
        } else {
            rootView.accountProgressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        val toast = Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG)
        Log.d("error", error)
        toast.show()
    }

    override fun updated(user: User) {
        TODO("Not yet implemented")
    }

    override fun showUserDataSuccess(userData: User) {
        rootView.acUserName.text = userData.name + " " + userData.surname
        rootView.acEmailTextView.text = userData.email
        rootView.acPhoneNumberTextView.text = userData.phone
        rootView.acUserInfoTextView.text = userData.description
    }


    companion object {
        val TAG: String = "LoginFragment"
    }
}