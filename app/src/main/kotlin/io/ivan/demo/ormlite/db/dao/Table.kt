package io.ivan.demo.ormlite.db.dao

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "table")
data class Table(

    @DatabaseField(generatedId = true)
    var id: Int? = null,

    @DatabaseField
    var category: String = "",

    @DatabaseField
    var content: String = ""
) {
    constructor(): this(null, "", "")
}
