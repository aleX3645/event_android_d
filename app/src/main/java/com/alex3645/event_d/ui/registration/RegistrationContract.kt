package com.alex3645.event_d.ui.registration

import com.alex3645.event_d.ui.base.BaseContract

class RegistrationContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun RegistrationSuccess(login: String, password: String, token: String)
    }

    interface Presenter: BaseContract.Presenter<RegistrationContract.View> {
        fun rigister(login: String, password: String, name: String, surname: String, number: String, about: String, organizer: Boolean)
    }
}