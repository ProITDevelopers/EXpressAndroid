package com.example.expresskotlin.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerFragmentsAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {


    val mFragmentList: MutableList<Fragment> = ArrayList<Fragment>()
    val mFragmentTitleList: MutableList<String> = ArrayList<String>()



    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFrag(fragment: Fragment, title: String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}