package com.watsidev.retrofitapi.data

import com.watsidev.retrofitapi.data.model.Region

private val constantUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/"

object RegionDataSource {
    val regionList = listOf(
        Region("National", "1", "905", "$constantUrl/493.png"),
        Region("Kanto", "1", "151", "$constantUrl/6.png"),
        Region("Johto", "152", "251", "$constantUrl/157.png"),
        Region("Hoenn", "252", "386", "$constantUrl/260.png"),
        Region("Sinnoh", "387", "493", "$constantUrl/392.png"),
        Region("Unova", "494", "649", "$constantUrl/497.png"),
        Region("Kalos", "650", "721", "$constantUrl/658.png"),
        Region("Alola", "722", "809", "$constantUrl/727.png"),
        Region("Galar", "810", "898", "$constantUrl/818.png"),
        Region("Paldea", "899", "905", "$constantUrl/911.png"),
    )
}