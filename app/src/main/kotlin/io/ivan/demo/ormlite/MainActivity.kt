package io.ivan.demo.ormlite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.ivan.demo.ormlite.databinding.ActivityMainBinding
import io.ivan.demo.ormlite.db.dao.Table
import io.ivan.demo.ormlite.db.dao.TableDao
import io.ivan.demo.ormlite.db.DatabaseHelper

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val dao = TableDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnCreate.setOnClickListener(this@MainActivity)
            btnRemove.setOnClickListener(this@MainActivity)
            btnQuery.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnCreate -> {
                for (id in 1..10) {
                    dao.add(Table(null, id.toString(), ('a' + id - 1).toString()))
                }
                Toast.makeText(this, "create success", Toast.LENGTH_SHORT).show()

            }
            binding.btnRemove -> {
                dao.removeAll()
                Toast.makeText(this, "remove success", Toast.LENGTH_SHORT).show()
            }
            binding.btnQuery -> {
                val tableAll = dao.queryForAll()
                if (tableAll.size == 0) {
                    Toast.makeText(this, "is empty", Toast.LENGTH_SHORT).show()
                    return
                }
                val stringBuilder = StringBuilder()
                for (table in tableAll) {
                    stringBuilder.append(table.toString())
                    stringBuilder.append("\n")
                }
                Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DatabaseHelper.close()
    }
}
