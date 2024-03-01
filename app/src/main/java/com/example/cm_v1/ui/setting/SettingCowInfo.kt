package com.example.cm_v1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SettingCowInfo : AppCompatActivity() {
    private lateinit var cowInfo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_cow_info)

        // ボタンをレイアウトファイルから取得
        cowInfo = findViewById(R.id.btnBack)

        // ボタンにクリックリスナーを設定
        cowInfo.setOnClickListener {
            finish()
        }
    }
}
