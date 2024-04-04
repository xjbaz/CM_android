package com.example.cm_v1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cm_v1.R
import com.example.cm_v1.databinding.FragmentHomeBinding
import com.example.cm_v1.ui.dashboard.Dtab1Fragment
import com.example.cm_v1.ui.dashboard.Dtab2Fragment
import com.example.cm_v1.ui.dashboard.Dtab3Fragment
import com.example.cm_v1.ui.dashboard.Dtab4Fragment
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
//    private lateinit var viewPager: ViewPager
//    private lateinit var tabLayout: TabLayout


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewPager = view.findViewById(R.id.viewPager)
//        tabLayout = view.findViewById(R.id.tabs2)
        setupTabLayout()
    }




    private fun setupTabLayout() {
        binding.tabs2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showFragment(HtabAllFragment())
                    1 -> showFragment(Htab1Fragment())
                    2 -> showFragment(Htab2Fragment())
                    3 -> showFragment(Htab3Fragment())
                    4 -> showFragment(Htab4Fragment())
                    5 -> showFragment(Htab5Fragment())
                }
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // ここにコードは不要です。
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // ここにコードは不要です。
            }
        })

        // 初期フラグメントを設定
        showFragment(HtabAllFragment())
    }
    private fun showFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}