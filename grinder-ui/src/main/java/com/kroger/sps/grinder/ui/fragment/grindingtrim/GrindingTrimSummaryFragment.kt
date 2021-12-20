package com.kroger.sps.grinder.ui.fragment.grindingtrim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.grinder.ui.base.BaseFragment
import com.kroger.sps.grinder.ui.databinding.LayoutGrindingTrimSummaryBinding

class GrindingTrimSummaryFragment:BaseFragment() {
    private lateinit var mLayoutGrindingTrimSummaryBinding: LayoutGrindingTrimSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mLayoutGrindingTrimSummaryBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_grinding_trim_summary, container, false)
        super.onCreate(savedInstanceState)
        return mLayoutGrindingTrimSummaryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbarTitle("Grinding Trim Summary")
        showToolbarNavBack()
        mLayoutGrindingTrimSummaryBinding.staffCardWidgetGrindingTrim.setActionClickListener {
            navigateToNext()
        }
    }
    private fun navigateToNext() = navigateToScreen(R.id.navigation_grinding_trim)
}