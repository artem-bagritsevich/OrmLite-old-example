package io.ivan.demo.ormlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.j256.ormlite.dao.Dao
import io.ivan.demo.ormlite.databinding.ActivityMainBinding
import io.ivan.demo.ormlite.db.dao.Table
import io.ivan.demo.ormlite.db.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = requireNotNull(_binding)

    private val list = mutableListOf<Table>()
    private val dao: Dao<Table, Int>? = DatabaseHelper(this).getTableDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultTextView = binding.resultTextView

        binding.btnCreate.setOnClickListener {
            for (id in 1..10) {
                dao?.create(Table(null, id.toString(), ('a' + id - 1).toString()))
            }
            resultTextView.text = getString(R.string.create_success)
            dao?.apply {
                queryForAll().forEach { list.add(it) }
            }
        }
        binding.btnRemove.setOnClickListener {
            if (list.isNotEmpty()) {
                dao?.delete(list)
                list.clear()
                resultTextView.text = getString(R.string.remove_success)
            } else {
                resultTextView.text = getString(R.string.db_empty)
            }
        }
        binding.btnQuery.setOnClickListener {
            if (list.isEmpty()) {
                resultTextView.text = getString(R.string.db_empty)
                return@setOnClickListener
            }
            resultTextView.text = list.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        DatabaseHelper(this).close()
    }
}
