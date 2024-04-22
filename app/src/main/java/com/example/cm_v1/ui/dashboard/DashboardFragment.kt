package com.example.cm_v1.ui.dashboard

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.cm_v1.R
import com.example.cm_v1.databinding.FragmentDashboardBinding
import com.example.cm_v1.ui.notifications.NotificationsFragment


class DashboardFragment : Fragment() {

    private lateinit var binding:FragmentDashboardBinding // ViewBindingのインスタンスを保持するプロパティを定義する
    private var isBottomLayoutShown = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ViewBindingを初期化して割り当てる
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?){
//      このFragmentがオプションメニューを含むことを示し、システムにメニューの準備を行うよう指示
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu,inflater: MenuInflater) {
        //オプションメニューを作成するときに呼び出されるコールバックメソッド
        inflater.inflate(R.menu.calendar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // メニュー項目が選択されたときの処理
        return when (item.itemId) {
            R.id.action_calender -> {
                if (isBottomLayoutShown) {
                    slideOutToBottom(NotificationsFragment())
                } else { // 下からレイアウトが表示されていない場合は表示する
                    slideInFromBottom(NotificationsFragment())
                }
                // フラグをトグルする
                isBottomLayoutShown = !isBottomLayoutShown
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

     private fun slideInFromBottom(fragment: Fragment) {

        /* bottomFragmentContainerのBOTTOMをrootLayoutのBOTTOMに合わせる */
        ConstraintSet().apply {
            clone(binding.rootLayout)
            clear(R.id.bottomFragmentContainer, ConstraintSet.TOP)
            connect(R.id.bottomFragmentContainer, ConstraintSet.BOTTOM, R.id.rootLayout, ConstraintSet.BOTTOM)
            applyTo(binding.rootLayout)
        }

        /* フラグメントを表示 */
        childFragmentManager.beginTransaction()
            .add(R.id.bottomFragmentContainer, fragment)
            .commit()

        /* スライドインアニメーション */
        ObjectAnimator.ofFloat(
            binding.bottomFragmentContainer, "translationY",
            binding.bottomFragmentContainer.measuredHeight.toFloat(), 0f
        ).apply {
            duration = 150
            start()
        }
    }

    private fun slideOutToBottom(fragment: Fragment) {

        /* bottomFragmentContainerのTOPをrootLayoutのBOTTOMに合わせる */
        ConstraintSet().apply {
            clone(binding.rootLayout)
            clear(R.id.bottomFragmentContainer, ConstraintSet.BOTTOM)
            connect(R.id.bottomFragmentContainer, ConstraintSet.TOP, R.id.rootLayout, ConstraintSet.BOTTOM)
            applyTo(binding.rootLayout)
        }

        /* アニメーションイベントリスナー。アニメーション終了したらフラグメントを削除 */
        val animatorListener = object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator, isReverse: Boolean) {
                if (animation != null) {
                    super.onAnimationEnd(animation)
                }
                childFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }

        /* スライドアウトアニメーション */
        ObjectAnimator.ofFloat(
            binding.bottomFragmentContainer, "translationY",
            -binding.bottomFragmentContainer.measuredHeight.toFloat(), 0f
        ).apply {
            duration = 150
            addListener(animatorListener)
            start()
        }
    }
}
