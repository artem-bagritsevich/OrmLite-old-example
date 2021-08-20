# ORMLite-Kotlin
Kotlin example of using ORMLite

## e.g.
```
@DatabaseTable(tableName = "table")
data class Table(

        @DatabaseField(generatedId = true)
        var id: Int? = null,

        @DatabaseField
        var category: String = "",

        @DatabaseField
        var content: String = ""
)
```
