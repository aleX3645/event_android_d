package com.alex3645.event_d.model

data class User(
        val id: Int, val login: String = "Не указано", val name: String = "Не указано", val surname: String = "Не указано",
        val email: String = "Не указано", val phone: String = "Не указано", val description: String = "Не указано")
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