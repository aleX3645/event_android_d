package com.alex3645.event_d.di.module

import com.alex3645.event_d.App
import com.alex3645.event_d.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): App {
        return app
    }
}