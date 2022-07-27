package com.example.expresskotlin.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.ActivitySplashBinding
import com.example.expresskotlin.helpers.MetodosUsados
import com.facebook.shimmer.ShimmerFrameLayout

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private lateinit var mainHandler: Handler
    private lateinit var mainRunnable: Runnable
    private lateinit var txtHandler: Handler
    private lateinit var txtRunnable: Runnable

    private val DEFAULT_HANDLER_TIMEOUT:Long = 1200


    override fun onCreate(savedInstanceState: Bundle?) {
        MetodosUsados.transparentNavigationBar(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_splash)
        initViews()
    }

    private fun initViews() {

        val shimmerSplash: ShimmerFrameLayout = binding.shimmerSplash
        shimmerSplash.startShimmer()
        shimmerSplash.visibility = View.VISIBLE
        val imgLogo: ImageView = binding.imgLogo
        val txtCopyright: TextView = binding.txtCopyright

        //Animations
        val viewAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.splash_view_animation)
        val viewAnimFadeOut:Animation = AnimationUtils.loadAnimation(this,
            R.anim.splash_view_fadeout_animation
        )
        val logoAnim:Animation = AnimationUtils.loadAnimation(this, R.anim.splash_logo_animation)
        val txtAnim:Animation = AnimationUtils.loadAnimation(this,
            R.anim.splash_txtcopyright_animation
        )

        //Set animation to elements
        shimmerSplash.visibility = View.VISIBLE
        imgLogo.animation = logoAnim
        shimmerSplash.animation = viewAnim
        shimmerSplash.animation = viewAnimFadeOut
        shimmerSplash.visibility = View.INVISIBLE



        mainHandler = Handler()
        txtHandler = Handler()

        txtRunnable = Runnable() {

           run {
               txtCopyright.visibility = View.VISIBLE
               txtCopyright.animation = txtAnim

               mainRunnable = Runnable() {

                   run{
                       launchHomeScreen()
                   }
               }
               mainHandler.postDelayed(mainRunnable, DEFAULT_HANDLER_TIMEOUT)
           }

        }
        txtHandler.postDelayed(txtRunnable, 1200);


    }

    private fun launchHomeScreen() {
        val intent = Intent(this, CadastroActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (txtHandler != null && txtRunnable != null) {
            txtHandler.removeCallbacks(txtRunnable)
            if (mainHandler != null && mainRunnable != null) {
                mainHandler.removeCallbacks(mainRunnable)
            }
        }
    }


}