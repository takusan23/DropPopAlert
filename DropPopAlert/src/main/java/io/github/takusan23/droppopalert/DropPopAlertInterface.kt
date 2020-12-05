package io.github.takusan23.droppopalert

import io.github.takusan23.droppopalert.DropPopAlert.Companion.ALERT_DROP

/**
 * [DropPopAlert]・[DropPopAlertView]で共通している関数
 * */
interface DropPopAlertInterface {

    /**
     * アニメーションさせながら表示する関数
     * @param position [ALERT_DROP]等参照
     * */
    fun alert(position: Int)

    /**
     * 表示のみ。非表示にはしない。
     * @param position [ALERT_DROP]等参照
     * */
    fun showAlert(position: Int)

    /**
     * 非表示のみ。
     * @param position [ALERT_DROP]等参照
     * */
    fun hideAlert(position: Int)

}