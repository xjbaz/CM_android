package com.example.cm_v1.ui.info

import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.cm_v1.R


class Itab2_2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_itab2_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button_ad = view.findViewById<Button>(R.id.button_itab2_ad)
        val button_re = view.findViewById<Button>(R.id.button_itab2_re)

        button_re.setOnClickListener {
            // FragmentManagerの取得
            val pfm = parentFragmentManager

            // トランザクションの生成・コミット
            val ft = pfm.beginTransaction()
            ft.apply {
                replace(R.id.fragment_container, Itab2Fragment())
                commit()
            }
        }
        button_ad.setOnClickListener {
            // FragmentManagerの取得
            val pfm = parentFragmentManager
            val eventID: Long = 208
            val uri: Uri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID)
            val intent = Intent(Intent.ACTION_EDIT)
                .setData(uri)
                .putExtra(CalendarContract.Events.TITLE, "My New Title")
            startActivity(intent)

            // トランザクションの生成・コミット
            val ft = pfm.beginTransaction()
            ft.apply {
                replace(R.id.fragment_container, Itab2_3Fragment())
                commit()
            }
        }

    }
    fun asSyncAdapter(uri: Uri, account: String, accountType: String): Uri {
        return uri.buildUpon()
            .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
            .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, account)
            .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, accountType).build()
    }
}