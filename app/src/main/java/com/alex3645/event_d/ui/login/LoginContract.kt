package com.alex3645.event_d.ui.login

import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.ui.base.BaseContract

class LoginContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loginSuccess()
    }

    interface Presenter: BaseContract.Presenter<LoginContract.View> {
        fun authorize(login: String, password: String, organizer: Boolean)
    }
}