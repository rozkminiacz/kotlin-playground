package com.michalik.parkings.parking

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by michalik on 21.07.17
 */
interface ParkingRepo : MongoRepository<Parking, String>