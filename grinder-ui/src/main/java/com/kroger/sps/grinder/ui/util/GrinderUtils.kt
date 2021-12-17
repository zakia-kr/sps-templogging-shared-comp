package com.kroger.sps.grinder.ui.util

import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.kroger.design.components.KdsToast
import com.kroger.design.components.ToastState
import com.kroger.design.extensions.getOpacity
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.grinder.ui.constants.GrinderConstants.DEFAULT_TIME_STAMP
import com.kroger.sps.grinder.ui.constants.GrinderConstants.EMPTY_STRING
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object GrinderUtils {
    internal infix fun Button.toggleState(mState: Boolean) = mState.run {
        alpha =
            if (this) context.getOpacity(R.dimen.kds_opacity_enabled) else context.getOpacity(R.dimen.kds_opacity_disabled)
        isClickable = this
        isEnabled = this
    }

    internal fun Fragment.showToastMessage(
        parentView: View,
        title: String = EMPTY_STRING,
        message: String,
        state: ToastState
    ) {
        KdsToast(this, parentView, Snackbar.LENGTH_SHORT)
            .show(title, message, state)
    }

    fun View.show() = run { this.visibility = View.VISIBLE }

    fun View.hide() = run { this.visibility = View.INVISIBLE }

    fun View.remove() = run { this.visibility = View.GONE }

    internal fun String.toDate() =
        SimpleDateFormat(DEFAULT_TIME_STAMP, Locale.getDefault()).parse(this)

    fun String.toDisplayDate(inputFormat: String, outputFormat: String): String? {
        return try {
            val originalFormat = SimpleDateFormat(inputFormat, Locale.ENGLISH)
            val targetFormat: DateFormat = SimpleDateFormat(outputFormat, Locale.ENGLISH)
            val date = originalFormat.parse(this)
            date?.let { targetFormat.format(it) }
        } catch (exception: ParseException) {
            ""
        }
    }

    internal infix fun Calendar.toDisplayDate(inputFormat: String) =
        SimpleDateFormat(inputFormat, Locale.ENGLISH).format(this.time)
}