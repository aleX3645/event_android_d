package com.alex3645.event_d.ui.feed

import io.reactivex.rxjava3.disposables.CompositeDisposable

class FeedPresenter : FeedContract.Presenter{

    private val subscriptions = CompositeDisposable()
    private lateinit var view: FeedContract.View


    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: FeedContract.View) {
        this.view = view
        view.showListFragment()
    }
}