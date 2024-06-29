package com.newsreader.data.network

import android.content.Context
import androidx.annotation.StringRes

sealed class UIText {
    data class DynamicString(val str: String) : UIText()
    data class StringResource(@StringRes val resId: Int) : UIText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> str
            is StringResource -> context.getString(resId)
        }
    }
}