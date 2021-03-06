package com.alex3645.event_d.ui.conferencesList

import com.alex3645.event_d.api.ApiServiceInterface
import com.alex3645.event_d.model.Conference
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers



class ConferenceListPresenter: ConferenceListContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: ConferenceListContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ConferenceListContract.View) {
        this.view = view
    }

    override fun loadAllConferenceData() {
        for(i in 0 until 10){
            var subscribe = api.getConferenceByCategory(i)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ list: List<Conference>? ->
                        view.showProgress(false)
                        view.loadDataSuccess(list!!.take(10))
                    }, { error ->
                        view.showProgress(false)
                        view.showErrorMessage(error.localizedMessage)
                    })

            subscriptions.add(subscribe)
        }
    }
}