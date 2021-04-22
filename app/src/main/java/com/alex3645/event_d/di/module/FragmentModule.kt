package com.alex3645.event_d.di.module

import com.alex3645.event_d.api.ApiServiceInterface
import com.alex3645.event_d.ui.eventsList.ConferenceListContract
import com.alex3645.event_d.ui.eventsList.ConferenceListPresenter
import com.alex3645.event_d.ui.login.LoginContract
import com.alex3645.event_d.ui.login.LoginPresenter
import com.alex3645.event_d.ui.search.SearchContract
import com.alex3645.event_d.ui.search.SearchListPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {


    @Provides
    fun provideListPresenter(): ConferenceListContract.Presenter {
        return ConferenceListPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }

    @Provides
    fun provideLoginPresenter(): LoginContract.Presenter {
        return LoginPresenter()
    }

    @Provides
    fun provideSearchPresenter(): SearchContract.Presenter {
        return SearchListPresenter()
    }
}