package com.example.cm_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher


class LoginActivity : AppCompatActivity() {

    private lateinit var editTextId: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // EditTextをレイアウトから取得
        editTextId = findViewById(R.id.plain_text_input)
        editTextPassword = findViewById(R.id.plain_pass_input)
        btnLogin = findViewById(R.id.btnLogin)

        // TextWatcherをEditTextに追加
        editTextId.addTextChangedListener(loginTextWatcher)
        editTextPassword.addTextChangedListener(loginTextWatcher)

        // ログインボタンのクリックリスナーを設定
        btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // 最初はログインボタンを非活性にしておく
        btnLogin.isEnabled = false
    }

    // TextWatcherを実装
    private val loginTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            // テキストが変更された後に呼ばれる
            checkFieldsForEmptyValues()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // テキストが変更される前に呼ばれる
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // テキストが変更された時に呼ばれる
        }
    }

    // 入力フィールドが空かどうかを確認し、ログインボタンを制御
    private fun checkFieldsForEmptyValues() {
        val id = editTextId.text.toString()
        val password = editTextPassword.text.toString()

        // IDとパスワードが空でない場合にログインボタンを活性化
        btnLogin.isEnabled = id.isNotEmpty() && password.isNotEmpty()
    }
}