package com.example.cm_v1

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.Calendar

class SettingCowInfo : AppCompatActivity() {
    private lateinit var cowInfo: Button
    private lateinit var editText: EditText
    private lateinit var edit_info_date: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_cow_info)

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ボタンをレイアウトファイルから取得
        edit_info_date = findViewById(R.id.edit_info_date)
        edit_info_date.setOnClickListener {
            showDatePickerDialog()
        }

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

        cowInfo = findViewById(R.id.btnFinish)
        editText = findViewById(R.id.edit_info_name)
        editText = findViewById(R.id.edit_info_number)

        // ボタンにクリックリスナーを設定
        cowInfo.setOnClickListener {
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
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // 日付が選択されたときの処理
                val selectedDate = "$selectedYear/${selectedMonth + 1}/$selectedDay"
                edit_info_date.setText(selectedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}
