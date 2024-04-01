package com.example.cm_v1

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SettingHealthDurg : AppCompatActivity() {

    private lateinit var editDrugName: EditText
    private lateinit var btnAdd: Button
    private lateinit var listView: ListView
    private lateinit var btnBack: Button
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_health_drug)

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        editDrugName = findViewById(R.id.edit_drug_name)
        btnAdd = findViewById(R.id.btn_add)
        listView = findViewById(R.id.listView)
        btnBack = findViewById(R.id.btnBack)

        // 初期データを表示するアダプターを作成
        val data = listOf<String>()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data.toMutableList())
        listView.adapter = adapter

        // 追加ボタンのクリックリスナーを設定
        btnAdd.setOnClickListener {
            val newData = editDrugName.text.toString()
            if (newData.isNotEmpty()) {
                // 入力されたテキストを追加してアダプターを更新
                adapter.add(newData)
                adapter.notifyDataSetChanged()

                // EditText をクリア
                editDrugName.text.clear()
            }
        }

        // 戻るボタンのクリックリスナーを設定
        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // android.R.id.home に戻るボタンを押した時のidが取得できる
        if (item.itemId == android.R.id.home) {
            // 今回はActivityを終了させている
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
