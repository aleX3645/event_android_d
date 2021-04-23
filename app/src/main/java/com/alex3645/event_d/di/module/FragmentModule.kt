package com.alex3645.event_d.di.module

import com.alex3645.event_d.api.ApiServiceInterface
import com.alex3645.event_d.ui.conference.ConferenceContract
import com.alex3645.event_d.ui.conference.ConferencePresenter
import com.alex3645.event_d.ui.conferencesList.ConferenceListContract
import com.alex3645.event_d.ui.conferencesList.ConferenceListPresenter
import com.alex3645.event_d.ui.event.EventContract
import com.alex3645.event_d.ui.event.EventPresenter
import com.alex3645.event_d.ui.eventList.EventListContract
import com.alex3645.event_d.ui.eventList.EventListPresenter
import com.alex3645.event_d.ui.login.LoginContract
import com.alex3645.event_d.ui.login.LoginPresenter
import com.alex3645.event_d.ui.search.SearchContract
import com.alex3645.event_d.ui.search.SearchListPresenter
import com.alex3645.event_d.ui.user.UserContract
import com.alex3645.event_d.ui.user.UserPresenter
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

    @Provides
    fun provideConferencePresenter(): ConferenceContract.Presenter {
        return ConferencePresenter()
    }

    @Provides
    fun provideEventListPresenter(): EventListContract.Presenter {
        return EventListPresenter()
    }

    @Provides
    fun provideEventPresenter(): EventContract.Presenter {
        return EventPresenter()
    }

    @Provides
    fun provideUserPresenter(): UserContract.Presenter {
        return UserPresenter()
    }
}