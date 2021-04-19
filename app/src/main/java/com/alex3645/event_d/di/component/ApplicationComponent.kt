package com.alex3645.event_d.di.component

import com.alex3645.event_d.App
import com.alex3645.event_d.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: App)
}