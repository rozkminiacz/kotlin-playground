package com.michalik.parkings.parking

/**
 * Created by michalik on 21.07.17
 */
class Parking { //todo create area for parking (at least 3 latlng)

    var lat: Double = 0.0
    var lon: Double = 0.0
    var smallImage: String = ""
    var bigImage: String = ""
    var private: Boolean = false
    var covered: Boolean = false

    constructor(lat: Double, lon: Double, smallImage: String, bigImage: String, private: Boolean, covered: Boolean) {
        this.lat = lat
        this.lon = lon
        this.smallImage = smallImage
        this.bigImage = bigImage
        this.private = private
        this.covered = covered
    }
    constructor()

    lateinit var id: String
    var incidents: Int = 0
}
