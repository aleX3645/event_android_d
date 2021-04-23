package com.alex3645.event_d.ui.eventList

import com.alex3645.event_d.api.ApiServiceInterface
import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.ui.conferencesList.ConferenceListContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EventListPresenter: EventListContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: EventListContract.View

    override fun loadEventsByConferenceId(id: Int) {
        var subscribe = api.getEventByConferenceId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ events: List<Event>? ->
                    view.showProgress(false)
                    if (events != null) {
                        view.loadEventsdataSuccess(events)
                    }
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: EventListContract.View) {
        this.view = view
    }
}