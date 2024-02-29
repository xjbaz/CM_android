package com.example.cm_v1.ui.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cm_v1.R
import com.example.cm_v1.ui.info.InfoFragment

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        // InfoFragmentのインスタンスを作成
        val infoFragment = InfoFragment()

        // InfoFragmentのレイアウトをインフレートして表示
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, infoFragment)
            .commit()
    }
}