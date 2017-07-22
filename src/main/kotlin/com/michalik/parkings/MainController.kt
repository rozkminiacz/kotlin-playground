package com.michalik.parkings

import com.michalik.parkings.incident.Incident
import com.michalik.parkings.incident.IncidentKind
import com.michalik.parkings.incident.IncidentRepo
import com.michalik.parkings.parking.Parking
import com.michalik.parkings.parking.ParkingRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "/api")
class MainController {

    @Autowired
    lateinit var parkingRepo: ParkingRepo

    @Autowired
    lateinit var incidentRepo: IncidentRepo

    @RequestMapping(path = arrayOf("/parking"), method = arrayOf(RequestMethod.GET))
    fun getParkings(): MutableList<Parking>? {
        val list = parkingRepo.findAll()
        list.forEach { parking -> parking.incidents = incidentRepo.findIncidentsByParkingId(parking.id).count() }
        return list
    }

    @RequestMapping(path = arrayOf("/incidents"), method = arrayOf(RequestMethod.DELETE))
    fun deleteAll() = incidentRepo.deleteAll()

    @RequestMapping(path = arrayOf("/incidents"), method = arrayOf(RequestMethod.GET))
    fun getIncidentsByKind(@RequestParam(value = "kind", defaultValue = "OTHER") kind: IncidentKind): MutableList<Incident> = incidentRepo.findIncidentsByKind(kind)

    @RequestMapping(path = arrayOf("/incident"), method = arrayOf(RequestMethod.GET))
    fun getAllIncidents(): MutableList<Incident> = incidentRepo.findAll()

    @RequestMapping(path = arrayOf("/parking"), method = arrayOf(RequestMethod.POST))
    fun createParking(@RequestBody parking: Parking): Parking = parkingRepo.save(parking)

    @RequestMapping(path = arrayOf("/incident"), method = arrayOf(RequestMethod.POST))
    fun createIncident(@RequestBody incident: Incident): Incident = incidentRepo.save(incident)

    @RequestMapping(path = arrayOf("/incident/kind"), method = arrayOf(RequestMethod.GET))
    fun getIncidentKinds() = IncidentKind.values()

    @RequestMapping(path = arrayOf("/incidents/parking"), method = arrayOf(RequestMethod.GET))
    fun getLastIncidentsInParking(@RequestParam(value = "id") id: String) = incidentRepo.findIncidentsByParkingId(id)

}