package com.kroger.sps.grinder.ui.widget

import android.content.Context
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.bold
import androidx.core.text.color
import com.kroger.design.components.ValidationMessageState
import com.kroger.design.extensions.getColor
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.grinder.ui.databinding.LayoutGrinderSummaryWidgetBinding
import com.kroger.sps.grinder.ui.model.SummaryData
import com.kroger.sps.grinder.ui.util.GrinderUtils.newKeyLine
import com.kroger.sps.grinder.ui.util.GrinderUtils.newLine
import com.kroger.sps.grinder.ui.util.GrinderUtils.remove
import com.kroger.sps.grinder.ui.util.GrinderUtils.show

class GrinderSummaryView : ConstraintLayout {

    companion object {
        const val EMPTY_DATA = ""
        const val EMPTY_DATA_VIEW = "--"
    }

    private var mLayoutGrinderSummaryWidgetBinding: LayoutGrinderSummaryWidgetBinding =
        LayoutGrinderSummaryWidgetBinding.inflate(
            LayoutInflater.from(context), this, true
        )

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        context.obtainStyledAttributes(attrs, R.styleable.GrinderSummaryView, 0, 0).apply {
            title = getString(R.styleable.GrinderSummaryView_title)
            actionLabel = getString(R.styleable.GrinderSummaryView_actionLabel)
            recycle()
        }
    }

    var title: String? = null
        set(value) {
            field = value
            value?.let {
                mLayoutGrinderSummaryWidgetBinding.tvSummaryTitle.show()
                mLayoutGrinderSummaryWidgetBinding.tvSummaryTitle.text = it
            } ?: run {
                mLayoutGrinderSummaryWidgetBinding.tvSummaryTitle.remove()
            }
        }

    var summaryItems: Map<String, SummaryData> = mapOf()
        set(value) {
            field = value
            value.onEachIndexed { index, entry ->
                takeIf { entry.key.isNotBlank() }?.let {
                    mLayoutGrinderSummaryWidgetBinding.tvSummaryItems.append(
                        SpannableStringBuilder(entry.key.newLine()).apply keySpanText@{
                            when {
                                entry.value.summaryValue.isBlank() ->
                                    setErrorSummaryValue(EMPTY_DATA_VIEW, index, value.size)

                                entry.value.validationMessageState == ValidationMessageState.ERROR ->
                                    setErrorSummaryValue(entry.value.summaryValue, index, value.size)

                                else -> showSummaryValue(entry.value.summaryValue, index, value.size)
                            }
                        })
                }
            }
        }

    var actionLabel: String? = null
        set(value) {
            field = value
            value?.let {
                mLayoutGrinderSummaryWidgetBinding.tvSummaryAction.show()
                mLayoutGrinderSummaryWidgetBinding.tvSummaryAction.text = it
            } ?: run {
                mLayoutGrinderSummaryWidgetBinding.tvSummaryAction.remove()
            }
        }

    fun setActionClickListener(actionClickListener: (() -> Unit)?) =
        mLayoutGrinderSummaryWidgetBinding.tvSummaryAction.setOnClickListener { actionClickListener?.invoke() }


    private fun SpannableStringBuilder.setErrorSummaryValue(summaryValue: String, index: Int, totalSize: Int) = run {
        color(getColor(R.color.kds_ink_color_negative_800)) { showSummaryValue(summaryValue,index,totalSize) }
    }

    private fun SpannableStringBuilder.showSummaryValue(summaryValue: String, index: Int, totalSize: Int) = run {
        bold { append(summaryValue.plus(if (index < totalSize - 1) EMPTY_DATA.newKeyLine() else EMPTY_DATA)) }
    }
}