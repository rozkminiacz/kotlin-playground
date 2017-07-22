package com.michalik.parkings.incident

import java.util.*

/**
 * Created by michalik on 21.07.17
 */
class Incident {

    var date: Date = Calendar.getInstance().time
    var userId: String = ""
    var image: String = ""
    var description: String = ""
    var parkingId: String = ""
    var kind: IncidentKind = IncidentKind.OTHER

    constructor(date: Date, userId: String, image: String, description: String, parkingId: String, kind: IncidentKind) {
        this.date = date
        this.userId = userId
        this.image = image
        this.description = description
        this.parkingId = parkingId
        this.kind = kind
    }

    constructor() {}

    lateinit var id: String
}