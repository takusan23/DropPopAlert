package io.github.takusan23.droppopalert

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.postDelayed

/**
 * 上からポップアップするアラート
 * */
class DropPopAlert(ctx: Context, attr: AttributeSet) : FrameLayout(ctx, attr) {

    val alertTextView by lazy { findViewById<TextView>(R.id.drop_pop_alert_textview) }

    val alertMotionLayout by lazy { findViewById<MotionLayout>(R.id.drop_pop_alert_motionlayout) }

    /**
     * 表示時間
     * */
    var showTimeMs = 2000L

    init {
        View.inflate(context, R.layout.drop_pop_alert_layout, this)
    }

    /**
     * アラートを表示する。
     * */
    fun alert(message: String) {

        alertTextView.text = message

        alertMotionLayout.transitionToEnd()

        postDelayed(showTimeMs) {
            alertMotionLayout.transitionToStart()
        }

        postDelayed(showTimeMs * 2) {
            alertMotionLayout.transitionToEnd()
        }

        alertMotionLayout.constraintSetIds.forEach { id->
            // alertMotionLayout.getConstraintSet(id).connect()
        }

    }

}