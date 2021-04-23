package com.alex3645.event_d.ui.conference

import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.ui.base.BaseContract

class ConferenceContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun showConferenceDataSuccess(conferenceData: Conference)
    }

    interface Presenter: BaseContract.Presenter<ConferenceContract.View> {
        fun loadConferenceById(id: Int)
    }
}