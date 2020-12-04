package io.github.takusan23.droppopalert

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.core.view.isVisible
import java.util.*
import kotlin.concurrent.schedule

class DropPopAlertView(ctx: Context, attr: AttributeSet) : FrameLayout(ctx, attr) {

    /** 表示時間 */
    var showTimeMs = 2000L

    private var timer = Timer()

    companion object {
        /** 上から降りてくる */
        const val ALERT_DROP = 1

        /** 下から出てくる */
        const val ALERT_UP = 2
    }

    fun alert(position: Int) {

        timer.cancel()
        timer=Timer()

        when (position) {
            ALERT_UP -> {
                // ALERT_DROP。上からおりてくる
                val startAnimation =
                    AnimationUtils.loadAnimation(context, R.anim.drop_pop_alert_up_start_anim)
                this.startAnimation(startAnimation)
                this.isVisible = true
                // 終了へ
                val endAnimation =
                    AnimationUtils.loadAnimation(context, R.anim.drop_pop_alert_up_end_anim)
                timer.schedule(showTimeMs) {
                    this@DropPopAlertView.post {
                        this@DropPopAlertView.startAnimation(endAnimation)
                        this@DropPopAlertView.isVisible = false
                    }
                }
            }
            else -> {
                // 下から上へ
                val startAnimation =
                    AnimationUtils.loadAnimation(context, R.anim.drop_pop_alert_drop_start_anim)
                this.startAnimation(startAnimation)
                this.isVisible = true
                // 終了へ
                val endAnimation =
                    AnimationUtils.loadAnimation(context, R.anim.drop_pop_alert_drop_end_anim)
                timer.schedule(showTimeMs) {
                    this@DropPopAlertView.post {
                        this@DropPopAlertView.startAnimation(endAnimation)
                        this@DropPopAlertView.isVisible = false
                    }
                }
            }
        }

    }
}