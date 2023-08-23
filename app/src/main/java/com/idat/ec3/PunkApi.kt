package com.idat.ec3
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity(tableName = "punkapi")
@Parcelize
data class PunkApi(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val abv: Double,
    val first_brewed: String,
    val image_url: String,
    var isFavorite:Boolean=false
):Parcelable

fun getData():List<PunkApi> =
    listOf(
        PunkApi(1,"ssw","nike coupon","Nike Store",1.1,"vd",""),
        PunkApi(2,"ssw","nike coupon","Nike Store",1.1,"vd",""),
        PunkApi(3,"ssw","nike coupon","Nike Store",1.1,"vd",""),
        PunkApi(4,"ssw","nike coupon","Nike Store",1.1,"vd",""),
        PunkApi(5,"ssw","nike coupon","Nike Store",1.1,"vd",""),
        PunkApi(6,"ssw","nike coupon","Nike Store",1.1,"vd",""),
        PunkApi(7,"ssw","nike coupon","Nike Store",1.1,"vd",""),
        PunkApi(8,"ssw","nike coupon","Nike Store",1.1,"vd","")
    )
