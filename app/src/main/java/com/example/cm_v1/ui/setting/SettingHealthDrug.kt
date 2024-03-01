package com.example.cm_v1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SettingHealthDrug : AppCompatActivity() {
    private lateinit var cowInfo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_health_drug)

        // ボタンをレイアウトファイルから取得
        cowInfo = findViewById(R.id.btnBack)

        // ボタンにクリックリスナーを設定
        cowInfo.setOnClickListener {
            finish()
        }
    }
}
