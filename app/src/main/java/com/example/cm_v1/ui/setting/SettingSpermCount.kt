package com.example.cm_v1

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

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

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // android.R.id.home に戻るボタンを押した時のidが取得できる
        if (item.itemId == android.R.id.home) {
            // 今回はActivityを終了させている
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
