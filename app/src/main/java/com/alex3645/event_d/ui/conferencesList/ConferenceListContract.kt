package com.alex3645.event_d.ui.conferencesList

import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.ui.base.BaseContract

class ConferenceListContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Conference>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadAllConferenceData()
    }
}