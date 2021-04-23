package com.alex3645.event_d.ui.eventList

import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.ui.base.BaseContract

class EventListContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadEventsdataSuccess(event: List<Event>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadEventsByConferenceId(id: Int)
    }
}