package com.example.cm_v1.ui.setting

import YesNoDialogFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.cm_v1.R
import com.example.cm_v1.SettingCowHome
import com.example.cm_v1.SettingCowInfo
import com.example.cm_v1.SettingCustomTag
import com.example.cm_v1.SettingFoodChange
import com.example.cm_v1.SettingFoodInfo
import com.example.cm_v1.SettingHealthDisease
import com.example.cm_v1.SettingHealthDurg
import com.example.cm_v1.SettingSpermCount
import com.example.cm_v1.SettingSpermInfo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val ARG_PARAM1 = "param1"
const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var cowInfo: Button
    private lateinit var cowHome: Button
    private lateinit var healthDrug: Button
    private lateinit var healthDisease: Button
    private lateinit var foodInfo: Button
    private lateinit var foodChange: Button
    private lateinit var spermInfo: Button
    private lateinit var spermCount: Button
    private lateinit var customTag: Button
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // レイアウトファイルをインフレート
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        // レイアウトファイルからボタンを取得
        cowInfo = view.findViewById(R.id.cow_info)
        cowHome = view.findViewById(R.id.cow_home)
        healthDrug = view.findViewById(R.id.health_drug)
        healthDisease = view.findViewById(R.id.health_disease)
        foodInfo = view.findViewById(R.id.food_info)
        foodChange = view.findViewById(R.id.food_change)
        spermInfo = view.findViewById(R.id.sperm_info)
        spermCount = view.findViewById(R.id.sperm_count)
        customTag = view.findViewById(R.id.custom_tag)

        cowInfo.setOnClickListener {
            val intent = Intent(requireContext(), SettingCowInfo::class.java)
            startActivity(intent)
        }
        cowHome.setOnClickListener {
            val intent = Intent(requireContext(), SettingCowHome::class.java)
            startActivity(intent)
        }
        healthDrug.setOnClickListener {
            val intent = Intent(requireContext(), SettingHealthDurg::class.java)
            startActivity(intent)
        }
        healthDisease.setOnClickListener {
            val intent = Intent(requireContext(), SettingHealthDisease::class.java)
            startActivity(intent)
        }
        foodInfo.setOnClickListener {
            val intent = Intent(requireContext(), SettingFoodInfo::class.java)
            startActivity(intent)
        }
        foodChange.setOnClickListener {
            val intent = Intent(requireContext(), SettingFoodChange::class.java)
            startActivity(intent)
        }
        spermInfo.setOnClickListener {
            val intent = Intent(requireContext(), SettingSpermInfo::class.java)
            startActivity(intent)
        }
        spermCount.setOnClickListener {
            val intent = Intent(requireContext(), SettingSpermCount::class.java)
            startActivity(intent)
        }
        customTag.setOnClickListener {
            val intent = Intent(requireContext(), SettingCustomTag::class.java)
            startActivity(intent)
        }


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.login_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_login -> {
                // ログインメニューのアイテムがクリックされた場合、ダイアログを表示する
                showYesNoDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showYesNoDialog() {
        val dialog = YesNoDialogFragment()
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.NoPaddingDialogTheme)
        dialog.show(parentFragmentManager, "YesNoDialogFragment")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}