package com.example.cardapioonline

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Orders.db"
        private const val DATABASE_VERSION = 2  // Incremented version to trigger onUpgrade

        const val TABLE_NAME = "orders"
        const val COLUMN_ID = "id"
        const val COLUMN_FOOD_NAMES = "food_names"
        const val COLUMN_QUANTITIES = "quantities"
        const val COLUMN_TOTAL_VALUE = "total_value"  // Changed column name to singular
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_FOOD_NAMES TEXT," +
                "$COLUMN_QUANTITIES TEXT," +
                "$COLUMN_TOTAL_VALUE REAL)"  // Changed type to REAL for single value
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertOrder(foodNames: String, quantities: String, totalValue: Double): Long {  // Changed parameter to Double
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_FOOD_NAMES, foodNames)
        contentValues.put(COLUMN_QUANTITIES, quantities)
        contentValues.put(COLUMN_TOTAL_VALUE, totalValue)  // Changed to single value

        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllOrders(): List<String> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val orders = mutableListOf<String>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val foodNames = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOOD_NAMES))
                val quantities = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUANTITIES))
                val totalValue = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_VALUE))
                orders.add("ID: $id, Food Names: $foodNames, Quantities: $quantities, Total Value: $totalValue")
            } while (cursor.moveToNext())
        }
        cursor.close()
        return orders
    }
}

