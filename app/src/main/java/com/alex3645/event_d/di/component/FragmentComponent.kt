package com.alex3645.event_d.di.component

import com.alex3645.event_d.di.module.FragmentModule
import com.alex3645.event_d.ui.account.AccountFragment
import com.alex3645.event_d.ui.conference.ConferenceFragment
import com.alex3645.event_d.ui.conferencesList.ConferenceListFragment
import com.alex3645.event_d.ui.event.EventFragment
import com.alex3645.event_d.ui.eventList.EventListFragment
import com.alex3645.event_d.ui.login.LoginFragment
import com.alex3645.event_d.ui.registration.RegistrationFragment
import com.alex3645.event_d.ui.search.SearchFragmentList
import com.alex3645.event_d.ui.user.UserFragment
import dagger.Component

@Component(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(listFragment: ConferenceListFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(searchFragment: SearchFragmentList)
    fun inject(conferenceFragment: ConferenceFragment)
    fun inject(eventListFragment: EventListFragment)
    fun inject(eventFragment: EventFragment)
    fun inject(eventFragment: UserFragment)
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(accountFragment: AccountFragment)
}