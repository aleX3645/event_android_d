package com.alex3645.event_d.di.component

import com.alex3645.event_d.di.module.ActivityModule
import com.alex3645.event_d.ui.feed.FeedActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: FeedActivity)

}