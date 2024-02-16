package com.example.cm_v1.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cm_v1.R
import com.example.cm_v1.databinding.FragmentDashboardBinding
import com.google.android.material.tabs.TabLayout

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showFragment(Dtab1Fragment())
                    1 -> showFragment(Dtab2Fragment())
                    2 -> showFragment(Dtab3Fragment())
                    3 -> showFragment(Dtab4Fragment())
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
        showFragment(Dtab1Fragment())
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