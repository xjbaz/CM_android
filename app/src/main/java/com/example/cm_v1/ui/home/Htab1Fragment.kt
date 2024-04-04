package com.example.cm_v1.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cm_v1.R
import com.example.cm_v1.ui.info.InfoActivity
import java.util.Collections.addAll
import java.util.Locale

class Htab1Fragment : Fragment() {

    class Cow(val name: String, val number: String, val love: Boolean, val state: Boolean)

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
        val cows = listOf(
            Cow("さくら", "0X001A", true, true),
            Cow("よしえ", "0X001B", false, false),
            Cow("りかこ", "0X001C", false, false),
            Cow("さくらこ", "0X001D", true, true),
            Cow("はじめ", "0X001E", false, true),
            Cow("よしえ", "0X001F", true, true),
            Cow("みちる", "0X001G", true, false)
        )

        // ArrayAdapter を使用した方法
        val adapter = CowAdapter(requireContext(), cows)


        // ListViewにデータをセットする
        val list: ListView = view.findViewById(R.id.cowHouse_list)
        list.adapter = adapter

        // リストアイテムのクリックイベントをリスナーで処理
        list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // クリックされたアイテムの位置(position)を取得
            // ここで画面遷移などの処理を行う
            // 例えば、InfoActivityに遷移する場合:
            val intent = Intent(requireContext(), InfoActivity::class.java)
            startActivity(intent)
        }

        // テキストフィルターを有効にする
        list.isTextFilterEnabled = true

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView?.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                // 入力テキストに変更があったとき
                override fun onQueryTextChange(p0: String?): Boolean {
                    adapter.filter.filter(p0)
                    adapter.notifyDataSetChanged() // データが変更されたことを通知
                    return false
                }

                // 検索ボタンを押したとき
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }
            }
        )
    }


//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            Htab1Fragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}


class CowAdapter(context: Context, private val cows: List<Htab1Fragment.Cow>) :
    ArrayAdapter<Htab1Fragment.Cow>(context, 0, cows) {

    private var filteredCows: List<Htab1Fragment.Cow> = cows.toList()

    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val result = FilterResults()
            val filteredList = mutableListOf<Htab1Fragment.Cow>()

            constraint?.let { query ->
                if (query.isNotBlank()) {
                    val filterPattern = query.toString().toLowerCase(Locale.getDefault())
                    for (item in cows) {
                        if (item.name.toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                } else {
                    filteredList.addAll(cows)
                }
            }

            result.values = filteredList
            result.count = filteredList.size
            return result
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.let {
                filteredCows = it.values as List<Htab1Fragment.Cow>
                notifyDataSetChanged()
            }
        }
    }

    override fun getCount(): Int {
        return filteredCows.size
    }

    override fun getItem(position: Int): Htab1Fragment.Cow? {
        return filteredCows[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(
                R.layout.list_item1,
                parent,
                false
            )
        }

        val currentCow = getItem(position)

        val cowNameTextView = listItemView?.findViewById<TextView>(R.id.cow_name)
        cowNameTextView?.text = currentCow?.name

        val cowNumberTextView = listItemView?.findViewById<TextView>(R.id.number)
        cowNumberTextView?.text = currentCow?.number

        val loveIcon = listItemView?.findViewById<ImageView>(R.id.loveIcon)
        if (currentCow?.love == true) {
            loveIcon?.visibility = View.VISIBLE
        } else {
            loveIcon?.visibility = View.GONE
        }

        val stateIcon = listItemView?.findViewById<ImageView>(R.id.stateIcon)
        if (currentCow?.state == true) {
            stateIcon?.setImageResource(R.drawable.good_state)
        } else {
            stateIcon?.setImageResource(R.drawable.bat_state)
        }

        return listItemView!!
    }

    override fun getFilter(): Filter {
        return filter
    }
}


//class CowAdapter(context: Context, private val cows: List<Htab1Fragment.Cow>) :
//    ArrayAdapter<Htab1Fragment.Cow>(context, 0, cows) {
//
//    private var filteredCows: List<Htab1Fragment.Cow> = cows
//
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var listItemView = convertView
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(context).inflate(
//                R.layout.list_item1,
//                parent,
//                false
//            )
//        }
//
//        val currentCow = getItem(position)
//
//        val cowNameTextView = listItemView?.findViewById<TextView>(R.id.cow_name)
//        cowNameTextView?.text = currentCow?.name
//
//        val cowNumberTextView = listItemView?.findViewById<TextView>(R.id.number)
//        cowNumberTextView?.text = currentCow?.number
//
//        val loveIcon = listItemView?.findViewById<ImageView>(R.id.loveIcon)
//        if (currentCow?.love == true) {
//            loveIcon?.visibility = View.VISIBLE
//        } else {
//            loveIcon?.visibility = View.GONE
//        }
//
//        val stateIcon = listItemView?.findViewById<ImageView>(R.id.stateIcon)
//        if (currentCow?.state == true) {
//            stateIcon?.setImageResource(R.drawable.good_state)
//        } else {
//            stateIcon?.setImageResource(R.drawable.bat_state)
//        }
//
//        return listItemView!!
//    }
//
//    private val filter = object : Filter() {
//        override fun performFiltering(constraint: CharSequence?): FilterResults {
//            val result = FilterResults()
//            val filteredList: MutableList<Htab1Fragment.Cow> = mutableListOf()
//
//            if (constraint != null) {
//                filteredList.clear()
//                val filterPattern = constraint.toString().lowercase()
//                for (item in cows) {
//                    if (item.name.lowercase().contains(filterPattern)) {
//                        filteredList.add(item)
//                    }
//                }
//                result.values = filteredList
//                result.count = filteredList.size
//            }
//
//            return result
//        }
//
//        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//            clear()
//            if (results != null && results.count > 0) {
//                addAll(results.values as MutableList<Htab1Fragment.Cow>)
//            } else {
//                addAll(cows)
//            }
//            notifyDataSetChanged()
//        }
//    }
//}


//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val results = FilterResults()
//                val filteredList = mutableListOf<Htab1Fragment.Cow>()
//
//                constraint?.let { query ->
//                    if (query.isNotEmpty()) {
//                        val search = query.toString().lowercase(Locale.getDefault())
//                        for (cow in cows) {
//                            if (cow.name.contains(search)) {
//                                filteredList.add(cow)
//                            }
//                        }
//                    } else {
//                        filteredList.addAll(cows)
//                    }
//                }
//
//                results.values = filteredList
//                results.count = filteredList.size
//                return results
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                results?.let {
//                    filteredCows = it.values as List<Htab1Fragment.Cow>
//                    notifyDataSetChanged()
//                }
//            }
//        }
//    }
//}
