package com.alex3645.event_d.model

import java.util.*

data class Event(
        val id: Int, val name: String = "Не указано", val description: String = "Не указано", val date_start: Date,
        val date_end: Date, val speaker_id: Int, val is_leaf: Boolean, val conference_id: Int)


/*
{
        "id": 1,
        "login": "raduan",
        "name": "Raduan",
        "surname": "Al-Shedivat"
    }
 */