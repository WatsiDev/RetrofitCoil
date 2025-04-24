package com.watsidev.retrofitapi.data

import com.watsidev.retrofitapi.data.model.Region

object RegionDataSource {
    val regionList = listOf(
        Region("National", "1", "905"),
        Region("Kanto", "1", "151"),
        Region("Johto", "152", "251"),
        Region("Hoenn", "252", "386"),
        Region("Sinnoh", "387", "493"),
        Region("Unova", "494", "649"),
        Region("Kalos", "650", "721"),
        Region("Alola", "722", "809"),
        Region("Galar", "810", "898"),
        Region("Paldea", "899", "905"),
    )
}