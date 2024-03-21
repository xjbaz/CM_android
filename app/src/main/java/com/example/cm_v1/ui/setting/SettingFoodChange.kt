package com.example.cm_v1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.*

class SettingFoodChange : AppCompatActivity() {
    private lateinit var btnFin: Button
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_food_change)

        val radioGroup: RadioGroup = findViewById(R.id.radio_gender)
        val button1: RadioButton = findViewById(R.id.radio_gender_osu)
        val button2: RadioButton = findViewById(R.id.radio_gender_mesu)

// ラジオボタングループの選択状態が変更されたときのリスナーを設定
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // ラジオボタンが選択されたときの処理
            when (checkedId) {
                R.id.radio_gender_osu -> {
                    // Button 1が選択されたときの処理
                    button2.isChecked = false // Button 2の選択を解除
                }
                R.id.radio_gender_mesu -> {
                    // Button 2が選択されたときの処理
                    button1.isChecked = false // Button 1の選択を解除
                }
            }
        }

        listView = findViewById(R.id.listView)
        val data = listOf(
            "牛A", "牛B", "牛C"
        )
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data.toMutableList())
        listView.adapter = adapter

        btnFin = findViewById(R.id.btnFinish)
        // ボタンにクリックリスナーを設定
        btnFin.setOnClickListener {
            finish()
        }
    }

}
