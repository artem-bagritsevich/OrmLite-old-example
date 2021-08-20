package io.ivan.demo.ormlite.db

import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import io.ivan.demo.ormlite.App
import io.ivan.demo.ormlite.db.dao.Table

object DatabaseHelper : OrmLiteSqliteOpenHelper(App.instance, "test.db", null, 1) {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, Table::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<Table, Any>(connectionSource, Table::class.java, true)
        onCreate(database, connectionSource)
    }
}