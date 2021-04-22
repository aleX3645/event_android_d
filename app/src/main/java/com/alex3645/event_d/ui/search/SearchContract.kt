package com.alex3645.event_d.ui.search

import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.model.User
import com.alex3645.event_d.ui.base.BaseContract

class SearchContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadConferenceDataSuccess(list: List<Conference>)
        fun loadEventDataSuccess(list: List<Event>)
        fun loadUserDataSuccess(list: List<User>)
    }

    interface Presenter: BaseContract.Presenter<SearchContract.View> {
        fun loadConferenceDataByText(text: String)
        fun loadEventDataByText(text: String)
        fun loadUserDataByText(text: String)
    }
}