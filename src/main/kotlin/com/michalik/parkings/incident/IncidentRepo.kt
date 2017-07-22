package com.michalik.parkings.incident

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by michalik on 21.07.17
 */
interface IncidentRepo : MongoRepository<Incident, String>{
    fun findIncidentsByParkingId(parkingId: String) : MutableList<Incident>
    fun findIncidentsByKind(kind: IncidentKind) : MutableList<Incident>
}