package com.alex3645.event_d.ui.conference

import com.alex3645.event_d.api.ApiServiceInterface
import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.model.LoginRequestBody
import com.alex3645.event_d.ui.login.LoginContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ConferencePresenter : ConferenceContract.Presenter{

    private val api: ApiServiceInterface = ApiServiceInterface.create()

    private val subscriptions = CompositeDisposable()
    private lateinit var view: ConferenceContract.View

    override fun loadConferenceById(id: Int) {
        var subscribe = api.getConferenceById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ conference: Conference? ->
                    view.showProgress(false)
                    if (conference != null) {
                        view.showConferenceDataSuccess(conference)
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

    override fun attach(view: ConferenceContract.View) {
        this.view = view
    }
}