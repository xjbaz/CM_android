package com.example.cm_v1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import java.util.*

class SettingSpermCount : AppCompatActivity() {
    private lateinit var btnFin: Button
    private lateinit var humanDay: EditText
    private lateinit var transDay: EditText
    private lateinit var natureDay: EditText
    private lateinit var afterDay: EditText
    private lateinit var beforeDay: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_sperm_count)

        btnFin = findViewById(R.id.btnFinish)
        humanDay = findViewById(R.id.human_day)
        transDay= findViewById(R.id.transplanting_day)
        natureDay= findViewById(R.id.nature_day)
        afterDay= findViewById(R.id.after_day)
        beforeDay= findViewById(R.id.before_day)

        // ボタンにクリックリスナーを設定
        btnFin.setOnClickListener {
            finish()
        }
    }
}
