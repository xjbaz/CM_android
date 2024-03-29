package com.example.cm_v1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.cm_v1.R

class SettingCustomTag : AppCompatActivity() {

    private lateinit var editTagName: EditText
    private lateinit var btnAdd: Button
    private lateinit var listView: ListView
    private lateinit var btnBack: Button
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_custom_tag)

        editTagName = findViewById(R.id.edit_tag_name)
        btnAdd = findViewById(R.id.btn_add)
        listView = findViewById(R.id.listView)
        btnBack = findViewById(R.id.btnBack)

        // 初期データを表示するアダプターを作成
        val data = listOf<String>()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data.toMutableList())
        listView.adapter = adapter

        // 追加ボタンのクリックリスナーを設定
        btnAdd.setOnClickListener {
            val newData = editTagName.text.toString()
            if (newData.isNotEmpty()) {
                // 入力されたテキストを追加してアダプターを更新
                adapter.add(newData)
                adapter.notifyDataSetChanged()

                // EditText をクリア
                editTagName.text.clear()
            }
        }

        // 戻るボタンのクリックリスナーを設定
        btnBack.setOnClickListener {
            finish()
        }
    }
}
