// YesNoDialogFragment.kt
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.cm_v1.LoginActivity
import com.example.cm_v1.R

class YesNoDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_layout, null)


        builder.setView(view)

        // ダイアログ内のYesボタンがクリックされたときの処理
        view.findViewById<Button>(R.id.buttonYes).setOnClickListener {
            // ここにYesボタンがクリックされたときの処理を記述
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        // ダイアログ内のNoボタンがクリックされたときの処理
        view.findViewById<Button>(R.id.buttonNo).setOnClickListener {
            // ここにNoボタンがクリックされたときの処理を記述
            dismiss() // ダイアログを閉じる
        }

        return builder.create()
    }
}
