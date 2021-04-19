package com.alex3645.event_d.di.component

import com.alex3645.event_d.di.module.FragmentModule
import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.ui.eventsList.ConferenceListFragment
import dagger.Component

@Component(modules = [FragmentModule::class])
interface FragmentComponent {


    fun inject(listFragment: ConferenceListFragment)

}