package com.alex3645.event_d.ui.registration

import com.alex3645.event_d.api.ApiServiceInterface
import com.alex3645.event_d.model.LoginRequestBody
import com.alex3645.event_d.ui.login.LoginContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RegistrationPresenter : RegistrationContract.Presenter{

    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private val subscriptions = CompositeDisposable()
    private lateinit var view: RegistrationContract.View
    override fun rigister(login: String, password: String, name: String, surname: String, number: String, about: String, organizer: Boolean) {
        if(organizer){
            var subscribe = api.authAsOrganizer(LoginRequestBody(login, password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.showProgress(false)

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

    override fun attach(view: RegistrationContract.View) {
        this.view = view
    }
}