package io.ivan.demo.ormlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import io.ivan.demo.ormlite.db.dao.Table

class DatabaseHelper(context: Context) :
    OrmLiteSqliteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    private var tableDao: Dao<Table, Int>? = null

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, Table::class.java)
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int,
    ) {
        TableUtils.dropTable<Table, Any>(connectionSource, Table::class.java, true)
        onCreate(database, connectionSource)
    }

    fun getTableDao(): Dao<Table, Int>? {
        if (tableDao == null) {
            tableDao = super.getDao(Table::class.java)
        }

        return tableDao
    }

    override fun close() {
        super.close()
        tableDao = null
    }

    companion object {
        private const val DB_NAME = "test.db"
        private const val DB_VERSION = 1
    }
}