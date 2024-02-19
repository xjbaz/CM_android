package com.example.cm_v1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cm_v1.R
import android.widget.ArrayAdapter
import android.widget.ListView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Htab1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Htab1Fragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_htab1,container,false)

        val platformVersion = arrayOf(
            "Android 10.0",
            "Android 9",
            "Android 9",
            "Android 9",
            "Android 9",
            "Android 9",
            "Android 9",
            "Android 9",
            "Android 10.0",
            "Android 9",
            "Android 9",
            "Android 9",
            "Android 9",
            "Android 9",
            "Android 9",
            "Android 9",
        )
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            platformVersion
        )

        val listView: ListView = view.findViewById(R.id.platform_version_list)
        listView.adapter = arrayAdapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HtabFragment.
         */
        // TODO: Rename and change types and number of parameters
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