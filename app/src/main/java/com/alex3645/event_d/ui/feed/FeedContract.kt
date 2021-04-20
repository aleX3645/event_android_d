package com.alex3645.event_d.ui.feed

import com.alex3645.event_d.ui.base.BaseContract
import com.alex3645.event_d.ui.login.LoginContract

class FeedContract {
    interface View: BaseContract.View {
        fun showListFragment()
        fun showLoginFragment()
        fun showSearchFragment()
    }

    interface Presenter: BaseContract.Presenter<FeedContract.View> {
    }
}