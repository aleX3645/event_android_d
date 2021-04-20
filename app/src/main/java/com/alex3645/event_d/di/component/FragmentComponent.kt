package com.alex3645.event_d.di.component

import com.alex3645.event_d.di.module.FragmentModule
import com.alex3645.event_d.ui.eventsList.ConferenceListFragment
import com.alex3645.event_d.ui.login.LoginFragment
import dagger.Component

@Component(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(listFragment: ConferenceListFragment)
    fun inject(loginFragment: LoginFragment)

}