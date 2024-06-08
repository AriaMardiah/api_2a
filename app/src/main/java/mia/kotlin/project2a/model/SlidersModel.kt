package mia.kotlin.project2a.model

import java.util.ArrayList

data class SlidersModel(val success: Int,
                       val message: String,
                       val data: ArrayList<Result>
){
    data class Result(val image: String, val deskripsi: String)
}