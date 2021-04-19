package com.alex3645.event_d.di.module

import android.app.Activity
import com.alex3645.event_d.ui.feed.FeedContract
import com.alex3645.event_d.ui.feed.FeedPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): FeedContract.Presenter {
        return FeedPresenter()
    }

}