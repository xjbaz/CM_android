package com.example.cm_v1.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.cm_v1.R
class Itab2_4Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_itab2_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button_re = view.findViewById<Button>(R.id.button_itab4_re)

        button_re.setOnClickListener {
            // FragmentManagerの取得
            val pfm = parentFragmentManager

            // トランザクションの生成・コミット
            val ft = pfm.beginTransaction()
            ft.apply {
                replace(R.id.fragment_container, Itab2_3Fragment())
                commit()
            }
        }
    }
}