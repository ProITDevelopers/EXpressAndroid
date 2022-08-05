package com.example.expresskotlin.helpers

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.expresskotlin.R
import com.example.expresskotlin.models.CartItem
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.random.Random

@Suppress("DEPRECATION")
class MetodosUsados {

    companion object {

        fun transparentStatusBar(activity: Activity){
            val window: Window = activity.window
            val decorView: View = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = Color.TRANSPARENT
            }

        }

        fun transparentNavigationBar(activity: Activity){
            val window: Window = activity.window
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.navigationBarColor = ContextCompat.getColor(activity, R.color.white)
            }
        }

        //=====================================================================//
        //==============MOSTRAR_MENSAGENS=======================================================//
        fun mostrarMensagem(mContexto: Context, mensagem:Int) {
            Toast.makeText(mContexto,mensagem,Toast.LENGTH_SHORT).show()
        }

        fun mostrarMensagem(mContexto: Context, mensagem:String) {
            Toast.makeText(mContexto,mensagem,Toast.LENGTH_SHORT).show()
        }

        //======================GERAR_NUMEROS_ALEATORIOS_INTEIROS===============================================//
        fun getRandomNumbers(min: Int, max: Int): Int {
            if (min>=max){
                throw IllegalArgumentException("max must be greater than min")
            }

            var randomNumber = Random
            return randomNumber.nextInt((max - min) + 1) + min
        }

        fun getCartPrice(cartItemList: ArrayList<CartItem>):Float{
            var price =0f
            for (cartItem in cartItemList){
                price += ((cartItem.produto.preco?.times(cartItem.cartItemQuantity))?.toFloat() ?: Float) as Float
            }
            return price
        }

        fun formatPrice(price:Double) : String{
            if (price > 0){
                val df = DecimalFormat("#,##0.00")
                df.roundingMode = RoundingMode.UP
                val finalPrice = StringBuilder().append(df.format(price)).toString()
                return finalPrice.replace(".",",")
            }
            else
                return "0.00"
        }


    }





}