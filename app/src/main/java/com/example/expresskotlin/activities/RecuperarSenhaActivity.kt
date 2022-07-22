package com.example.expresskotlin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.expresskotlin.R

import com.example.expresskotlin.login_regist_senha.RecuperarFragment

class RecuperarSenhaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_senha)
        goToFragment(RecuperarFragment())
    }

    private fun goToFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout_senha, fragment, null)
        transaction.commit()
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}