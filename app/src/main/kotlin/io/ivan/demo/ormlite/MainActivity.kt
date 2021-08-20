package io.ivan.demo.ormlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.ivan.demo.ormlite.databinding.ActivityMainBinding
import io.ivan.demo.ormlite.db.DatabaseHelper
import io.ivan.demo.ormlite.db.dao.Table
import io.ivan.demo.ormlite.db.dao.TableDao

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dao = TableDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener {
            for (id in 1..10) {
                dao.add(Table(null, id.toString(), ('a' + id - 1).toString()))
            }
            Toast.makeText(this, "create success", Toast.LENGTH_SHORT).show()
        }

        binding.btnRemove.setOnClickListener {
            dao.removeAll()
            Toast.makeText(this, "remove success", Toast.LENGTH_SHORT).show()
        }

        binding.btnQuery.setOnClickListener {
            val tableAll = dao.queryForAll()
            if (tableAll.size == 0) {
                Toast.makeText(this, "is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val stringBuilder = StringBuilder()
            for (table in tableAll) {
                stringBuilder.append(table.toString())
                stringBuilder.append("\n")
            }
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DatabaseHelper.close()
    }
}
