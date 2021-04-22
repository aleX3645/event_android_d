package com.alex3645.event_d.model

data class Event(
        val id: Int, val name: String, val description: String, val date_start: String,
        val date_end: String, val speaker_id: Int, val is_leaf: Boolean, val conference_id: Int)


/*
{
        "id": 1,
        "login": "raduan",
        "name": "Raduan",
        "surname": "Al-Shedivat"
    }
 */