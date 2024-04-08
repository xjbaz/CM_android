package com.example.cm_v1.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.cm_v1.R
import com.example.cm_v1.databinding.FragmentHomeBinding
import com.example.cm_v1.ui.info.InfoActivity
import com.google.android.material.tabs.TabLayout
import java.util.Locale



class HomeFragment : Fragment() {

    private lateinit var adapter: TabAdapter
    private lateinit var tab: TabLayout
    private lateinit var viewPager: ViewPager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        viewPager = root.findViewById(R.id.viewPager)
        tab = root.findViewById(R.id.tabLayout)

        for (k in 0 until 5) {
            tab.addTab(tab.newTab().setText("$k"))
        }

        adapter = TabAdapter(childFragmentManager, tab.tabCount)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
        // タブが選択されたときにViewPagerのページを変更する処理

        setupTabLayout()
        return root
    }

    private fun setupTabLayout()
    {
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // タブが選択解除された時の処理
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // タブが再度選択された時の処理
            }
        })
    }
    class TabAdapter(fm: androidx.fragment.app.FragmentManager, private val numOfTabs: Int) :
        androidx.fragment.app.FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return DynamicFragment.newInstance(position)
        }

        override fun getCount(): Int {
            return numOfTabs
        }
    }
}
