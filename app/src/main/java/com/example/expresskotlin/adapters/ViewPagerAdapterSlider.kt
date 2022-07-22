package com.example.expresskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.expresskotlin.R


class ViewPagerAdapterSlider(context: Context, imageSlides: IntArray): PagerAdapter() {

    private var context: Context
    private var imageSlides: IntArray
    private lateinit var layoutInflater: LayoutInflater

    init {
        this.context = context
        this.imageSlides = imageSlides
    }


    override fun getCount(): Int {
        return imageSlides.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View? = layoutInflater.inflate(R.layout.custom_slide_layout, null)

        val imgSlide : ImageView? =  view?.findViewById(R.id.imgSlide)
        imgSlide?.setImageResource(imageSlides[position])

        val vp = container as ViewPager
        vp.addView(view, 0)

        return view!!
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}