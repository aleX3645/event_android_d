package com.alex3645.event_d.ui.event

import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.ui.base.BaseContract
import com.alex3645.event_d.ui.conference.ConferenceContract

class EventContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun showEventDataSuccess(eventData: Event)
    }

    interface Presenter: BaseContract.Presenter<EventContract.View> {
        fun loadEventById(id: Int)
    }
}