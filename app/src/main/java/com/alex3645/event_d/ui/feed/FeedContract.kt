package com.alex3645.event_d.ui.feed

import com.alex3645.event_d.ui.base.BaseContract

class FeedContract {
    interface View: BaseContract.View {
        fun showListFragment()
    }

    interface Presenter: BaseContract.Presenter<FeedContract.View> {
    }
}