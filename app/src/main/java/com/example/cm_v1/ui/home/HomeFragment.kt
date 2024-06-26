package com.example.cm_v1.ui.home


import YesNoDialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cm_v1.R
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment(),AdapterView.OnItemSelectedListener{

    private lateinit var adapter: ViewPagerAdapter
    private lateinit var tab: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var stateSpinner: Spinner
    private lateinit var states: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        stateSpinner = root.findViewById(R.id.filter_spinner)

        states = resources.getStringArray(R.array.spinner_item3)
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            states
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        stateSpinner.adapter = spinnerAdapter
        stateSpinner.onItemSelectedListener = this


        viewPager = root.findViewById(R.id.viewPager)
        tab = root.findViewById(R.id.tabLayout_h)


        for (k in 0 until 4) {
            if (k == 0) {
                tab.addTab(tab.newTab().setText("すべて"))
            } else {
                tab.addTab(tab.newTab().setText("牛舎$k"))
            }
        }

        adapter = ViewPagerAdapter(requireActivity(),tab.tabCount)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
        // タブが選択されたときにViewPagerのページを変更する処理


        //ViewPager2でページを変更する際、TabLayoutの選択状態を連動する処理
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tab.selectTab(tab.getTabAt(position))
            }
        })
        return root


    }

    override fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, arg2: Int, arg3: Long) {
        val i = arg0?.selectedItemPosition ?: 0
        Toast.makeText(
            requireContext(),
            states[i],
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onNothingSelected(arg0: AdapterView<*>?) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
    }


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.login_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.action_login -> {
//                // ログインメニューのアイテムがクリックされた場合、ダイアログを表示する
//                showYesNoDialog()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun showYesNoDialog() {
//        val dialog = YesNoDialogFragment()
//        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.NoPaddingDialogTheme)
//        dialog.show(parentFragmentManager, "YesNoDialogFragment")
//    }

    private fun setupTabLayout() {
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

    inner class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val numOfTabs: Int) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            //アダプターが管理するフラグメントの総数を返す
            return numOfTabs
        }
        override fun createFragment(position: Int): Fragment {
            return HomeDynamicFragment.newInstance(position)
        }
    }
}