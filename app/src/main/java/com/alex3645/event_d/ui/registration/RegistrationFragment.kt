package com.alex3645.event_d.ui.registration

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
import com.alex3645.event_d.ui.login.LoginContract
import com.alex3645.event_d.ui.login.LoginPresenter
import kotlinx.android.synthetic.main.conference_layout.view.*
import kotlinx.android.synthetic.main.login_layout.view.*
import kotlinx.android.synthetic.main.recycler_fragment.view.*
import kotlinx.android.synthetic.main.registration_layout.view.*
import javax.inject.Inject

class RegistrationFragment: Fragment(), RegistrationContract.View {

    @Inject
    lateinit var presenter: RegistrationContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): RegistrationFragment {
        return RegistrationFragment()
    }

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
        rootView = inflater!!.inflate(R.layout.registration_layout, container, false)
        rootView.regButton.setOnClickListener{
            showProgress(true)

        }
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
            rootView.registrationProgressBar.visibility = View.VISIBLE
        } else {
            rootView.registrationProgressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        val toast = Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG)
        Log.d("error", error)
        toast.show()
    }

    override fun RegistrationSuccess(login: String, password: String, token: String) {

    }


    companion object {
        val TAG: String = "RegistrationFragment"
    }
}