package com.kroger.sps.grinder.ui.model

import com.kroger.design.components.ValidationMessageState

data class SummaryData(
    val summaryValue: String,
    val validationMessageState: ValidationMessageState = ValidationMessageState.SUCCESS
)