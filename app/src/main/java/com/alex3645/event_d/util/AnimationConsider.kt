package com.alex3645.event_d.util

import android.util.Log
import com.alex3645.event_d.R
import com.alex3645.event_d.ui.conferencesList.ConferenceListFragment
import com.alex3645.event_d.ui.login.LoginFragment
import com.alex3645.event_d.ui.search.SearchFragmentList

class AnimationConsider {
    companion object{
        public fun considerEnterAnimation(curTag: String, newTag: String): Int{

            if(newTag == SearchFragmentList.TAG){
                return R.anim.enter_from_up
            }
            
            if(newTag == LoginFragment.TAG){
                return R.anim.enter_from_right
            }

            if(newTag == ConferenceListFragment.TAG && curTag == SearchFragmentList.TAG){
                return R.anim.enter_from_bottom
            }

            if(newTag == ConferenceListFragment.TAG && curTag == LoginFragment.TAG){
                return R.anim.enter_from_left
            }

            return R.anim.enter_from_up
        }

        public fun considerExitAnimation(curTag: String, newTag: String): Int{
            if(newTag == SearchFragmentList.TAG){
                return R.anim.exit_to_bottom
            }

            if(newTag == LoginFragment.TAG){
                return R.anim.exit_to_left
            }

            if(newTag == ConferenceListFragment.TAG && curTag == SearchFragmentList.TAG){
                return R.anim.exit_to_up
            }

            if(newTag == ConferenceListFragment.TAG && curTag == LoginFragment.TAG){
                return R.anim.exit_to_right
            }

            return R.anim.exit_to_up
        }
    }
}