package com.example.testtask

import java.util.*
import kotlin.collections.ArrayList

data class RequestModel(
    var id: Int = getAutoId(),

    var binnum: String = "",

    var number_len: String = "",
    var number_lun: String = "",

    var scheme: String = "",

    var type: String = "",

    var brand: String = "",

    var prepaid: String = "",

    var country_emoji: String = "",
    var country_name: String = "",
    var country_coord: String = "",

    var bank_name: String = "",
    var bank_city: String = "",
    var bank_site: String = "",
    var bank_phone: String = "",



){
    companion object{
        fun getAutoId(): Int{
            val random = Random()
            return random.nextInt(10000)
        }
    }


}