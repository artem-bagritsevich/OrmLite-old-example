package io.ivan.demo.ormlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.ivan.demo.ormlite.db.dao.Table
import io.ivan.demo.ormlite.db.dao.TableDao
import io.ivan.demo.ormlite.db.DatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val dao = TableDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_create.setOnClickListener(this)
        btn_remove.setOnClickListener(this)
        btn_query.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view) {
            btn_create -> {
                for (id in 1..10) {
                    dao.add(Table(null, id.toString(), ('a' + id - 1).toString()))
                }
                Toast.makeText(this, "create success", Toast.LENGTH_SHORT).show()

            }
            btn_remove -> {
                dao.removeAll();
                Toast.makeText(this, "remove success", Toast.LENGTH_SHORT).show()
            }
            btn_query -> {
                val tableAll = dao.queryForAll()
                if (tableAll.size == 0) {
                    Toast.makeText(this, "is empty", Toast.LENGTH_SHORT).show()
                    return;
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
