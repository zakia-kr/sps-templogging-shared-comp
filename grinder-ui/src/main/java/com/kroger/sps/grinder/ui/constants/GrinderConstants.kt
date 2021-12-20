package com.kroger.sps.grinder.ui.constants

import com.kroger.sps.grinder.ui.R

object GrinderConstants {
    const val DEFAULT_TIME_STAMP = "MM/dd/yyyy"
    const val EMPTY_STRING = ""

    enum class GrinderFlows(val flowName: String, val resourceId: Int) {
        GRINDING_TRIM("Grinding Trim", R.layout.activity_grinding_trim_flow),
        CASE_READY_PACK("Case Ready Pack", R.layout.layout_grinding_trim),
        LOG_PRIMAL("Log Primal", R.layout.layout_grinding_trim),
        NO_GRINDS_TODAY("No Grinds Today", R.layout.layout_grinding_trim),
        MEAT_SAW_INSPECTION("Meat Saw Inspection", R.layout.layout_grinding_trim),
        CLEAN_GRINDER_MACHINE("Clean Grinder Machine", R.layout.layout_grinding_trim),
        REPORTS("Reports", R.layout.layout_grinding_trim)
    }

    fun String.fetchResourceId() = GrinderFlows.values().firstOrNull { it.flowName == this }?.resourceId
}