package com.alex3645.event_d.ui.login

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.JsonToken
import com.alex3645.event_d.api.ApiServiceInterface
import com.alex3645.event_d.api.SessionManager
import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.model.LoginRequestBody
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginPresenter : LoginContract.Presenter{

    private val api: ApiServiceInterface = ApiServiceInterface.create()

    private val subscriptions = CompositeDisposable()
    private lateinit var view: LoginContract.View

    override fun authorize(login: String, password: String, organizer: Boolean) {
        if(organizer){
            var subscribe = api.authAsOrganizer(LoginRequestBody(login, password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.showProgress(false)
                        view.loginSuccess(login, password, it.message)
                    }, { error ->
                        view.showProgress(false)
                        view.showErrorMessage(error.localizedMessage)
                    })
        }else{
            var subscribe = api.authAsUser(LoginRequestBody(login, password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.showProgress(false)

                        if(it.success){
                            view.loginSuccess(login, password, it.message)
                        }else{
                            view.showErrorMessage(it.message)
                        }
                    }, { error ->
                        view.showProgress(false)
                        view.showErrorMessage(error.localizedMessage)
                    })
        }
    }



    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: LoginContract.View) {
        this.view = view
    }
}