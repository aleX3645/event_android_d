package com.alex3645.event_d.model

import java.util.*

data class Conference(
    val id: Int, val name: String, val description: String, val location: String,
    val category: Int, val tariffs: List<Tariff>, val date_start: Date,
    val date_end: Date, val is_cancelled: Boolean)

/*
{
    "id": 3,
    "name": "aaaa",
    "description": "a",
    "location": "aa",
    "category": 0,
    "tariffs": [],
    "date_start": "2021-04-22T11:57:34.000+0300",
    "date_end": "2021-04-22T11:57:24.000+0300",
    "is_cancelled": false
}
 */
