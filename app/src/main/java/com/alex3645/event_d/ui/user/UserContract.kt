package com.alex3645.event_d.ui.user

import com.alex3645.event_d.model.Event
import com.alex3645.event_d.model.User
import com.alex3645.event_d.ui.base.BaseContract

class UserContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun showUserDataSuccess(userData: User)
    }

    interface Presenter: BaseContract.Presenter<UserContract.View> {
        fun loadUserById(id: Int)
    }
}