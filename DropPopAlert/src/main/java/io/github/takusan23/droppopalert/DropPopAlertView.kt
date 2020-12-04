package io.github.takusan23.droppopalert

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.core.view.isVisible
import java.util.*
import kotlin.concurrent.schedule

/**
 * 下、上からスライドして出てくるView。
 *
 * xmlに設置したら、予めandroid:visibilityをgoneにしておく必要があります。（tools:visibilityの方はvisibleでおｋ）
 *
 * [DropPopAlertView.alert]でアニメーションできます。
 * */
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

    /**
     * アニメーションさせながら表示する関数
     * @param position [ALERT_DROP]等参照
     * */
    fun alert(position: Int) {
        // タイマー停止
        timer.cancel()
        timer = Timer()
        // 表示
        showAlert(position)
        timer.schedule(showTimeMs) {
            // UIスレッドではないため
            this@DropPopAlertView.post {
                hideAlert(position)
            }
        }
    }

    /**
     * 表示のみ。非表示にはしない。
     * @param position [ALERT_DROP]等参照
     * */
    fun showAlert(position: Int) {
        val animation = when (position) {
            ALERT_UP -> {
                AnimationUtils.loadAnimation(context, R.anim.drop_pop_alert_up_start_anim)
            }
            else -> {
                AnimationUtils.loadAnimation(context, R.anim.drop_pop_alert_drop_start_anim)
            }
        }
        this.startAnimation(animation)
        this.isVisible = true
    }

    /**
     * 非表示のみ。
     * @param position [ALERT_DROP]等参照
     * */
    fun hideAlert(position: Int) {
        val animation = when (position) {
            ALERT_UP -> {
                AnimationUtils.loadAnimation(context, R.anim.drop_pop_alert_up_end_anim)
            }
            else -> {
                AnimationUtils.loadAnimation(context, R.anim.drop_pop_alert_drop_end_anim)
            }
        }
        this.startAnimation(animation)
        this.isVisible = false
    }

}