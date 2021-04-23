package com.alex3645.event_d.ui.user

import com.alex3645.event_d.api.ApiServiceInterface
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.model.User
import com.alex3645.event_d.ui.event.EventContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class UserPresenter :UserContract.Presenter{

    private val api: ApiServiceInterface = ApiServiceInterface.create()

    private val subscriptions = CompositeDisposable()
    private lateinit var view: UserContract.View

    override fun loadUserById(id: Int) {
        var subscribe = api.getUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user: User? ->
                    view.showProgress(false)
                    if (user != null) {
                        view.showUserDataSuccess(user)
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

    override fun attach(view: UserContract.View) {
        this.view = view
    }
}