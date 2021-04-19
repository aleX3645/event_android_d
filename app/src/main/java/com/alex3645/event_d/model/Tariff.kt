package com.alex3645.event_d.model

data class Tariff(val id: Int, val name: String, val cost: Double, val conference_id: Int, val tickets_left:Int, val tickets_total: Int)

/*
{
                "id": 1,
                "name": "vip",
                "cost": 228.0,
                "conference_id": 3,
                "tickets_left": 0,
                "tickets_total": 100
            }
 */
