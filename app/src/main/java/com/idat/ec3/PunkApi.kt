package com.idat.ec3
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val image_url: String
)

fun getData():List<Beer> =
    listOf(
        Beer(1,"ssw","nike coupon","Nike Store","50"),
        Beer(2,"ssw","nike coupon","Nike Store","50"),
        Beer(3,"ssw","nike coupon","Nike Store","50"),
        Beer(4,"ssw","nike coupon","Nike Store","50"),
        Beer(5,"ssw","nike coupon","Nike Store","50"),
        Beer(6,"ssw","nike coupon","Nike Store","50"),
        Beer(7,"ssw","nike coupon","Nike Store","50"),
        Beer(8,"ssw","nike coupon","Nike Store","50")

    )
