package com.example.cardapioonline

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Orders.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "orders"
        const val COLUMN_ID = "id"
        const val COLUMN_FOOD_NAME = "food_name"
        const val COLUMN_QUANTITY = "quantity"
        const val COLUMN_TOTAL_PRICE = "total_price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_FOOD_NAME TEXT," +
                "$COLUMN_QUANTITY INTEGER," +
                "$COLUMN_TOTAL_PRICE REAL)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertOrder(foodName: String, quantity: Int, totalPrice: Double): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_FOOD_NAME, foodName)
        contentValues.put(COLUMN_QUANTITY, quantity)
        contentValues.put(COLUMN_TOTAL_PRICE, totalPrice)

        return db.insert(TABLE_NAME, null, contentValues)
    }
}
