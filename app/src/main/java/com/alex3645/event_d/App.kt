package com.alex3645.event_d

import android.app.Application
import com.alex3645.event_d.di.component.ApplicationComponent
import com.alex3645.event_d.di.component.DaggerApplicationComponent
import com.alex3645.event_d.di.module.ApplicationModule

class App: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: App private set
    }
}