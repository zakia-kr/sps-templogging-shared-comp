package com.kroger.sps.grinder.ui.fragment.grindingtrim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kroger.design.components.ValidationMessageState
import com.kroger.mobile.kaf.log.LogComponent
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.grinder.ui.base.BaseFragment
import com.kroger.sps.grinder.ui.databinding.LayoutGrindingTrimSummaryBinding
import com.kroger.sps.grinder.ui.model.SummaryData

class GrindingTrimSummaryFragment : BaseFragment() {

    companion object {
        var TAG = GrindingTrimSummaryFragment::class.java.simpleName
    }

    private lateinit var mLayoutGrindingTrimSummaryBinding: LayoutGrindingTrimSummaryBinding

    private val mLog by lazy { kafContext.getComponent(LogComponent::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mLayoutGrindingTrimSummaryBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.layout_grinding_trim_summary,
                container,
                false
            )
        super.onCreate(savedInstanceState)
        return mLayoutGrindingTrimSummaryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbarTitle("Grinding Trim Summary")
        showToolbarNavBack()
        handleSummaryItemsView()
    }

    private fun handleSummaryItemsView() {
        mLog.d(TAG, "adding the summary View")
        mLayoutGrindingTrimSummaryBinding.svGrinderTrimData.run {
            this.title = "Step 1/2"
            this.actionLabel = "Edit"
            this.summaryItems = getGrinderTrimMap()
            this.setActionClickListener { navigate(R.id.action_summary_to_grind_trim) }
        }
        mLayoutGrindingTrimSummaryBinding.svGrinderTrimStaffData.summaryItems = getStaffMap()
    }

    private fun getGrinderTrimMap() = mapOf(
        "Packed Date" to SummaryData("April 24, 2021"),
        "Sell by Date" to SummaryData(
            "April 29, 2021",
            validationMessageState = ValidationMessageState.ERROR
        )
    )

    private fun getStaffMap() = mapOf(
        "Staff" to SummaryData("DLT28107"),
        "Grind Time" to SummaryData("April 24, 2021 -  13:52 EST"),
        "Store Number" to SummaryData("1829118"),
    )
}