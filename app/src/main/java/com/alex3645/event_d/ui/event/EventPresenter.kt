package com.alex3645.event_d.ui.event

import com.alex3645.event_d.api.ApiServiceInterface
import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.ui.conference.ConferenceContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EventPresenter : EventContract.Presenter{

    private val api: ApiServiceInterface = ApiServiceInterface.create()

    private val subscriptions = CompositeDisposable()
    private lateinit var view: EventContract.View

    override fun loadEventById(id: Int) {
        var subscribe = api.getEventById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ event: Event? ->
                    view.showProgress(false)
                    if (event != null) {
                        view.showEventDataSuccess(event)
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

    override fun attach(view: EventContract.View) {
        this.view = view
    }
}