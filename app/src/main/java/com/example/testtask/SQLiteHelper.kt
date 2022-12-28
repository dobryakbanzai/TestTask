package com.example.testtask

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.*

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "history.db"

        private const val TBL_HISTORY = "tbl_history"

        private const val ID = "id"

        private const val BINNUM = "binnum"

        private const val NUMBER_LEN = "number_len"
        private const val NUMBER_LUN = "number_lun"

        private const val SCHEME = "scheme"

        private const val TYPE = "type"

        private const val BRAND = "brand"

        private const val PREPAID = "prepaid"

        private const val COUNTRY_EMOJI = "country_emoji"
        private const val COUNTRY_NAME = "country_name"
        private const val COUNTRY_COORD = "country_coord"

        private const val BANK_NAME = "bank_name"
        private const val BANK_CITY = "bank_city"
        private const val BANK_SITE = "bank_site"
        private const val BANK_PHONE = "bank_phone"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblHistory = ("CREATE TABLE " + TBL_HISTORY + "("
                                + ID + " INTEGER PRIMARY KEY, "
                                + BINNUM + " TEXT, "
                                + NUMBER_LEN + " TEXT, "
                                + NUMBER_LUN + " TEXT, "
                                + SCHEME + " TEXT, "
                                + TYPE + " TEXT, "
                                + BRAND + " TEXT, "
                                + PREPAID + " TEXT, "
                                + COUNTRY_EMOJI + " TEXT, "
                                + COUNTRY_NAME + " TEXT, "
                                + COUNTRY_COORD + " TEXT, "
                                + BANK_NAME + " TEXT, "
                                + BANK_CITY + " TEXT, "
                                + BANK_SITE + " TEXT, "
                                + BANK_PHONE + " TEXT " + ")")

        println(createTblHistory)

        db?.execSQL(createTblHistory)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_HISTORY")
        onCreate(db)
    }

    fun insertRequest(rm: RequestModel): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, rm.id)
        contentValues.put(BINNUM, rm.binnum)
        contentValues.put(NUMBER_LEN, rm.number_len)
        contentValues.put(NUMBER_LUN, rm.number_lun)
        contentValues.put(SCHEME, rm.scheme)
        contentValues.put(TYPE, rm.type)
        contentValues.put(BRAND, rm.brand)
        contentValues.put(PREPAID, rm.prepaid)
        contentValues.put(COUNTRY_EMOJI, rm.country_emoji)
        contentValues.put(COUNTRY_NAME, rm.country_name)
        contentValues.put(COUNTRY_COORD, rm.country_coord)
        contentValues.put(BANK_NAME, rm.bank_name)
        contentValues.put(BANK_CITY, rm.bank_city)
        contentValues.put(BANK_SITE, rm.bank_site)
        contentValues.put(BANK_PHONE, rm.bank_phone)

        val success = db.insert(TBL_HISTORY, null, contentValues)
        db.close()

        return success

    }

    @SuppressLint("Range")
    fun getAllRequests(): ArrayList<RequestModel>{
        val reqList: ArrayList<RequestModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_HISTORY"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)

            return ArrayList()
        }

        var id: Int
        var binnum: String
        var number_len: String
        var number_lun: String
        var scheme: String
        var type: String
        var brand: String
        var prepaid: String
        var country_emoji: String
        var country_name: String
        var country_coord: String
        var bank_name: String
        var bank_city: String
        var bank_site: String
        var bank_phone: String

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))

                binnum = cursor.getString(cursor.getColumnIndex("binnum"))

                number_len = cursor.getString(cursor.getColumnIndex("number_len"))
                number_lun = cursor.getString(cursor.getColumnIndex("number_lun"))

                scheme = cursor.getString(cursor.getColumnIndex("scheme"))

                type = cursor.getString(cursor.getColumnIndex("type"))

                brand = cursor.getString(cursor.getColumnIndex("brand"))

                prepaid = cursor.getString(cursor.getColumnIndex("prepaid"))

                country_emoji = cursor.getString(cursor.getColumnIndex("country_emoji"))
                country_name = cursor.getString(cursor.getColumnIndex("country_name"))
                country_coord = cursor.getString(cursor.getColumnIndex("country_coord"))

                bank_name = cursor.getString(cursor.getColumnIndex("bank_name"))
                bank_city = cursor.getString(cursor.getColumnIndex("bank_city"))
                bank_site = cursor.getString(cursor.getColumnIndex("bank_site"))
                bank_phone = cursor.getString(cursor.getColumnIndex("bank_phone"))

                val rm = RequestModel(
                    id = id,
                    binnum = binnum,

                    number_len = number_len,
                    number_lun = number_lun,

                    scheme = scheme,

                    type = type,

                    brand = brand,

                    prepaid = prepaid,

                    country_emoji = country_emoji,
                    country_name = country_name,
                    country_coord = country_coord,

                    bank_name = bank_name,
                    bank_city = bank_city,
                    bank_site = bank_site,
                    bank_phone = bank_phone
                    )
                reqList.add(rm)
            }while (cursor.moveToNext())
        }

        return reqList
    }
}