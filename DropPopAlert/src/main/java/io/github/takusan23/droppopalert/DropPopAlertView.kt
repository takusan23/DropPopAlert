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
 * */
class DropPopAlertView(ctx: Context, attr: AttributeSet) : FrameLayout(ctx, attr), DropPopAlertInterface {

    val dropPopAlert = this.toDropPopAlert()

    override fun alert(position: Int) = dropPopAlert.alert(position)

    override fun showAlert(position: Int) = dropPopAlert.showAlert(position)

    override fun hideAlert(position: Int) = dropPopAlert.hideAlert(position)

}