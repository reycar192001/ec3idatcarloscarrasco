package com.idat.ec3

data class PunkApi(
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val image_url: String
)

fun getData():List<PunkApi> =
    listOf(
        PunkApi("","nike coupon","Nike Store","50"),
        PunkApi("","nike coupon","Nike Store","50"),
        PunkApi("","nike coupon","Nike Store","50"),
        PunkApi("","nike coupon","Nike Store","50"),
        PunkApi("","nike coupon","Nike Store","50"),
        PunkApi("","nike coupon","Nike Store","50"),
        PunkApi("","nike coupon","Nike Store","50"),
        PunkApi("","nike coupon","Nike Store","50")

    )
