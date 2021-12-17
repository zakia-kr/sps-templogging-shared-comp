package com.kroger.sps.grinder.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kroger.mobile.kaf.context.KafContext

@Suppress("UNCHECKED_CAST")
class CustomViewModelFactory(private val mKafContext: KafContext) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            GrinderBaseViewModel::class.java -> GrinderBaseViewModel(mKafContext) as T
            else -> throw  IllegalArgumentException("Unknown ViewModel class")
        }
    }
}