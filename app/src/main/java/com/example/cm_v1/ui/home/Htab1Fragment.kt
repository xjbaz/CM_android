package com.example.cm_v1.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.example.cm_v1.R
import com.example.cm_v1.ui.info.InfoActivity

class Htab1Fragment : Fragment() {

    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_htab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // データを用意
        val name = arrayOf(
            "さくら", "よしえ", "りかこ", "さくらこ", "はじめ", "よしえ",
            "みちる",
        )
        val number = arrayOf(
            "0X001A","0X001B","0X001C",
            "0X001D","0X001E","0X001F",
            "0X001G",
        )

        val listData = ArrayList<Map<String, Any>>()
        for (i in name.indices) {
            val item = HashMap<String, Any>()
            item["name"] = name[i]
            item["number"] = number[i]
            listData.add(item)
        }

        // ListViewにデータをセットする
        val list: ListView = view.findViewById(R.id.cowHouse_list)
        list.adapter = SimpleAdapter(
            requireContext(),
            listData,
            R.layout.list_item1,
            arrayOf("name", "number"),
            intArrayOf(R.id.cow_name, R.id.number)
        )

        // リストアイテムのクリックイベントをリスナーで処理
        list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // クリックされたアイテムの位置(position)を取得
            // ここで画面遷移などの処理を行う
            // 例えば、InfoActivityに遷移する場合:
            val intent = Intent(requireContext(), InfoActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Htab1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
