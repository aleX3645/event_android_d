package com.alex3645.event_d.ui.account

import com.alex3645.event_d.api.ApiServiceInterface
import com.alex3645.event_d.model.LoginRequestBody
import com.alex3645.event_d.model.User
import com.alex3645.event_d.ui.login.LoginContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AccountPresenter : AccountContract.Presenter{

    private val api: ApiServiceInterface = ApiServiceInterface.create()

    private val subscriptions = CompositeDisposable()
    private lateinit var view: AccountContract.View

    override fun update(user: User) {
        TODO("Not yet implemented")
    }

    override fun loadUserByLogin(login: String) {
        var subscribe = api.getUserByLogin(login)
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

    override fun attach(view: AccountContract.View) {
        this.view = view
    }
}