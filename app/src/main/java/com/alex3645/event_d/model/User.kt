package com.alex3645.event_d.model

data class User(
        val id: Int, val login: String, val name: String, val surname: String,
        val email: String, val phone: String, val description: String)
/*
{
        "id": 2,
        "name": "Economics EVENT",
        "description": "This is event dedicated for undergrads on Economics faculty",
        "date_start": "2020-05-12T12:15:00.000+0300",
        "date_end": "2020-05-12T19:15:00.000+0300",
        "speaker_id": 1,
        "is_leaf": true,
        "conference_id": 1
    }
 */