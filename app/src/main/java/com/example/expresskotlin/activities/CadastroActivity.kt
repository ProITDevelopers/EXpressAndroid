package com.example.expresskotlin.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.ActivityCadastroBinding
import com.google.android.material.tabs.TabLayout

import com.example.expresskotlin.adapters.ViewPagerFragmentsAdapter

import com.example.expresskotlin.helpers.MetodosUsados
import com.example.expresskotlin.login_regist_senha.LoginFragment
import com.example.expresskotlin.login_regist_senha.RegistroFragment

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding

    private lateinit var imgCadastro: ImageView
    private lateinit var txtCadastro: TextView
    private lateinit var mViewPager: ViewPager


    companion object{
        private var tabLayout: TabLayout? = null
        fun getTabLayout(): TabLayout? {
            return tabLayout
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MetodosUsados.transparentStatusBar(this);
//        setContentView(R.layout.activity_cadastro)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        imgCadastro = binding.imgCadastro
        txtCadastro = binding.txtCadastro
        tabLayout = binding.tabs
        mViewPager = binding.viewPager
        showFrags()
    }

    private fun showFrags() {

        tabLayout?.setupWithViewPager(mViewPager)
        val adapter = ViewPagerFragmentsAdapter(supportFragmentManager)

        // add your fragments
        adapter.addFrag(RegistroFragment(), getString(R.string.cadastro_tab_registrar));
        adapter.addFrag(LoginFragment(), getString(R.string.cadastro_tab_login))

        // set adapter on viewpager
        mViewPager.adapter = adapter
        mViewPager.currentItem = 1

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

                if (position == 0){
                    imgCadastro.setImageResource(R.drawable.registrar)
                    txtCadastro.setText(R.string.cadastro_text_registro)
                }else{
                    imgCadastro.setImageResource(R.drawable.login);
                    txtCadastro.setText(R.string.cadastro_text_login)
                }


            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

    }



}