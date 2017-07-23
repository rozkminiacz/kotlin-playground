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


    /**
     * Parking CRUD
     */

    @RequestMapping(path = arrayOf("/parking"), method = arrayOf(RequestMethod.POST))
    fun createParking(@RequestBody parking: Parking): Parking = parkingRepo.save(parking)

    @RequestMapping(path = arrayOf("/parking"), method = arrayOf(RequestMethod.GET))
    fun getParkings(): MutableList<Parking>? {
        val list = parkingRepo.findAll()
        list.forEach { parking -> parking.incidents = incidentRepo.findIncidentsByParkingId(parking.id).count() }
        return list
    }

    @GetMapping("parking/{id}")
    fun getParkingById(@PathVariable id : String) : Parking?{
        val count = incidentRepo.findIncidentsByParkingId(id).count()
        val parking = parkingRepo.findOne(id)
        parking.incidents=count
        return parking
    }

    @PutMapping("parking/{id}")
    fun editParking(@PathVariable id: String, @RequestBody editedParking: Parking) : Parking{
        var parking = parkingRepo.findOne(id)
        parking=editedParking
        return parkingRepo.save(parking)
    }

    @DeleteMapping("parking/{id}")
    fun deleteParking(@PathVariable id: String) = parkingRepo.delete(id)

    @DeleteMapping("parking")
    fun deleteAllParking() = parkingRepo.deleteAll()

    /**
     * Incidents CRUD
     */

    @RequestMapping(path = arrayOf("/incident"), method = arrayOf(RequestMethod.POST))
    fun createIncident(@RequestBody incident: Incident): Incident = incidentRepo.save(incident)

    @RequestMapping(path = arrayOf("/incident/kind"), method = arrayOf(RequestMethod.GET))
    fun getIncidentKinds() = IncidentKind.values()

    @GetMapping("incident/{id}")
    fun getIncidentById(@PathVariable id : String) : Incident? = incidentRepo.findOne(id)

    @GetMapping("/incidents")
    fun getIncidentsByKind(@RequestParam(value = "kind", defaultValue = "OTHER") kind: IncidentKind): MutableList<Incident> = incidentRepo.findIncidentsByKind(kind)

    @RequestMapping(path = arrayOf("/incident"), method = arrayOf(RequestMethod.GET))
    fun getAllIncidents(): MutableList<Incident> = incidentRepo.findAll()

    @DeleteMapping("/incident")
    fun deleteAllIncidents() = incidentRepo.deleteAll()

    @DeleteMapping("/incident/{id}")
    fun deleteIncident(@PathVariable id: String) = incidentRepo.delete(id)

    @RequestMapping(path = arrayOf("/incidents/{id}"), method = arrayOf(RequestMethod.GET))
    fun getLastIncidentsInParking(@PathVariable("id") id: String) = incidentRepo.findIncidentsByParkingId(id)

}