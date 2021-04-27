package com.alex3645.event_d.ui.account

import com.alex3645.event_d.model.User
import com.alex3645.event_d.ui.base.BaseContract

class AccountContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun updated(user: User)
        fun showUserDataSuccess(userData: User)
    }

    interface Presenter: BaseContract.Presenter<AccountContract.View> {
        fun update(user: User)
        fun loadUserByLogin(login: String)
    }
}