package com.example.firedatabase_assis
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DB_class(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "app_db"

        private val TABLE_CONTACTS = "user"
        private val TABLE_POST = "annonce"

        private val KEY_EMAIL = "email"
        private val KEY_PSWD = "pswd"
        private val KEY_CITY = "city"
        private val KEY_ACCOUNT_TYPE = "account_type"

        private const val KEY_TITLE = "titre"
        private const val KEY_SECTEUR = "secteur"
        private const val KEY_TYPE_CONTRAT = "type_contrat"
        private const val KEY_DESCRIPTION = "desc"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTableQuery = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_EMAIL + " TEXT PRIMARY KEY,"
                + KEY_PSWD + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_ACCOUNT_TYPE + " TEXT" + ")")

        val createPostTableQuery = ("CREATE TABLE " + TABLE_POST + "("
                + "id INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_SECTEUR + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_TYPE_CONTRAT + " TEXT,"
                + KEY_DESCRIPTION + " TEXT" + ")")
        db?.execSQL(createUserTableQuery)
        db?.execSQL(createPostTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_POST")
        onCreate(db)
    }

}