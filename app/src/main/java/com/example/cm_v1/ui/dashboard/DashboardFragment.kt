package com.example.cm_v1.ui.dashboard

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.cm_v1.R


class DashboardFragment : Fragment() {

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

    fun slideInFromBottom(fragment: Fragment) {

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

    fun slideOutToBottom(fragment: Fragment) {

        /* bottomFragmentContainerのTOPをrootLayoutのBOTTOMに合わせる */
        ConstraintSet().apply {
            clone(binding.rootLayout)
            clear(R.id.bottomFragmentContainer, ConstraintSet.BOTTOM)
            connect(R.id.bottomFragmentContainer, ConstraintSet.TOP, R.id.rootLayout, ConstraintSet.BOTTOM)
            applyTo(binding.rootLayout)
        }

        /* アニメーションイベントリスナー。アニメーション終了したらフラグメントを削除 */
        val listener = object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                childFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }

        /* スライドアウトアニメーション */
        ObjectAnimator.ofFloat(
            binding.bottomFragmentContainer, "translationY",
            -binding.bottomFragmentContainer.measuredHeight.toFloat(), 0f
        ).apply {
            duration = 150
            addListener(listener)
            start()
        }
    }
}
