package com.example.cm_v1.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
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
import com.example.cm_v1.R
import com.example.cm_v1.ui.info.InfoActivity
import java.util.Locale

class Dtab1Fragment : Fragment() {

    class Cow(val name: String, val number: String, val love: Boolean, val state: Boolean)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dtab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // データを用意
        val cows = listOf(
            Cow("さくら", "0X001A", true, true),
            Cow("よしえ", "0X001B", false, false),
            Cow("りかこ", "0X001C", false, false),
        )

        // ArrayAdapter を使用した方法
        val adapter = CowAdapter(requireContext(), cows)


        // ListViewにデータをセットする
        val list: ListView = view.findViewById(R.id.cowBreed_list)
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




class CowAdapter(context: Context, private val cows: List<Dtab1Fragment.Cow>) :
    ArrayAdapter<Dtab1Fragment.Cow>(context, 0, cows) {

    private var filteredCows: List<Dtab1Fragment.Cow> = cows.toList()

    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val result = FilterResults()
            val filteredList = mutableListOf<Dtab1Fragment.Cow>()

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
                filteredCows = it.values as List<Dtab1Fragment.Cow>
                notifyDataSetChanged()
            }
        }
    }

    override fun getCount(): Int {
        return filteredCows.size
    }

    override fun getItem(position: Int): Dtab1Fragment.Cow? {
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

}