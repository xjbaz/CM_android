package com.example.cm_v1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cm_v1.R
import com.example.cm_v1.databinding.FragmentInfoBinding
import com.example.cm_v1.ui.info.Itab1Fragment
import com.example.cm_v1.ui.info.Itab2Fragment
import com.example.cm_v1.ui.info.Itab3Fragment
import com.example.cm_v1.ui.info.Itab4Fragment
import com.example.cm_v1.ui.info.Itab5Fragment
import com.google.android.material.tabs.TabLayout

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.tabs2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showFragment(Itab1Fragment())
                    1 -> showFragment(Itab2Fragment())
                    2 -> showFragment(Itab3Fragment())
                    3 -> showFragment(Itab4Fragment())
                    4 -> showFragment(Itab5Fragment())
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
