package com.example.cm_v1.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cm_v1.R
import com.example.cm_v1.databinding.FragmentInfoBinding
import com.example.cm_v1.ui.dashboard.Dtab2Fragment
import com.example.cm_v1.ui.dashboard.Dtab3Fragment
import com.example.cm_v1.ui.dashboard.Dtab4Fragment
import com.example.cm_v1.ui.info.Itab1Fragment
import com.example.cm_v1.ui.info.Itab2Fragment
import com.example.cm_v1.ui.info.Itab3Fragment
import com.example.cm_v1.ui.info.Itab4Fragment
import com.example.cm_v1.ui.info.Itab5Fragment
import com.example.cm_v1.ui.setting.SettingFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IllegalArgumentException


class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
        viewPager = binding.viewPager3
        viewPager.adapter = PagerAdapter(requireActivity())
        TabLayoutMediator(binding.tabs2,viewPager){tab,position ->
            if (position == 0){tab.text = "個体"}
            else if (position == 1) {
                tab.text = "繁殖"}
            else if (position == 2) {
                tab.text = "餌"}
            else if (position == 3) {
                tab.text = "罹患"}
            else if (position == 4) {
                tab.text = "病歴"}
        }.attach()
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

    private inner class PagerAdapter(fragmentActivity: FragmentActivity):
            FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> Itab1Fragment()
                1 -> Itab2Fragment()
                2 -> Itab3Fragment()
                3 -> Itab4Fragment()
                4 -> Itab5Fragment()
                else -> throw IllegalArgumentException("Invalid position:$position")
            }
        }


    }
    
}
