package com.kroger.sps.grinder.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.grinder.ui.databinding.LayoutGrinderSummaryWidgetBinding

class GrinderSummaryView : ConstraintLayout {
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
            title = getString(
                R.styleable.GrinderSummaryView_title
            )
            actionLabel = getString(
                R.styleable.GrinderSummaryView_actionLabel
            )
            recycle()
        }
    }

    private var title: String? = null
        set(value) {
            field = value
            value?.let {
                mLayoutGrinderSummaryWidgetBinding.tvSummaryTitle.text = it
            }
        }
    private var actionLabel:String? = null
        set(value) {
            field = value
            value?.let {
                mLayoutGrinderSummaryWidgetBinding.tvSummaryAction.text = it
            }
        }

    fun setActionClickListener(actionClickListener: (()->Unit)?) {
        mLayoutGrinderSummaryWidgetBinding.tvSummaryAction.setOnClickListener {
            actionClickListener?.invoke()
        }
    }
}