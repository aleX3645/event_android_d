package com.alex3645.event_d.ui.login

import android.accounts.AccountManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import butterknife.OnClick
import com.alex3645.event_d.App
import com.alex3645.event_d.R
import com.alex3645.event_d.api.SessionManager
import com.alex3645.event_d.di.component.DaggerFragmentComponent
import com.alex3645.event_d.di.module.FragmentModule
import kotlinx.android.synthetic.main.login_layout.*
import kotlinx.android.synthetic.main.login_layout.view.*
import kotlinx.android.synthetic.main.recycler_fragment.view.*
import kotlinx.android.synthetic.main.recycler_fragment.view.progressBar
import okhttp3.Credentials
import javax.inject.Inject


class LoginFragment: Fragment(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): LoginFragment {
        return LoginFragment()
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
        rootView = inflater!!.inflate(R.layout.login_layout, container, false)
        rootView.loginButton.setOnClickListener{
            showProgress(true)
            presenter.authorize(rootView.loginTextField.editText?.text.toString(), rootView.passwordTextField.editText?.text.toString(), rootView.orgSwitch.isChecked)
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
            rootView.progressBar.visibility = View.VISIBLE
        } else {
            rootView.progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        val toast = Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG)
        Log.d("error", error)
        toast.show()
    }

    override fun loginSuccess(login: String, password: String, token: String) {
        val session = SessionManager(App.instance)
        session.saveAuthToken(token)
    }


    companion object {
        val TAG: String = "LoginFragment"
    }
}