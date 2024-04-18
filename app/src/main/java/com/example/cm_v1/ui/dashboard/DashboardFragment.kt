package com.example.cm_v1.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cm_v1.R
import com.example.cm_v1.databinding.FragmentDashboardBinding
import com.example.cm_v1.ui.home.HomeDynamicFragment
import com.example.cm_v1.ui.home.HomeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = binding.viewPager2
        viewPager.adapter = PagerAdapter(requireActivity())
        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->

            if (position == 0){tab.text = "全て"}
            else if (position == 1) {
                tab.text = "発情中"}
            else if (position == 2) {
                tab.text = "鑑定待ち"}
            else if (position == 3) {
                tab.text = "妊娠"}
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class PagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> Dtab1Fragment()
                1 -> Dtab2Fragment()
                2 -> Dtab3Fragment()
                3 -> Dtab4Fragment()
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }
    }
}
