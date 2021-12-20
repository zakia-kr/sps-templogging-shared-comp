package com.kroger.sps.grinder.ui.base

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.kroger.mobile.kaf.context.KafContext
import com.kroger.mobile.kaf.context.KafContextProvider
import com.kroger.sps.grinder.ui.activity.GrinderFlowActivity

open class BaseFragment : Fragment(), KafContextProvider {
    companion object {
        private val LOG_TAG = BaseFragment::class.simpleName
    }

    protected val mGrinderActivity
        get() = requireActivity() as GrinderFlowActivity

    override fun getKafContext() = activity?.applicationContext as KafContext

    protected fun showToolbarNavBack() {
        mGrinderActivity.setUpBackArrow()
    }

    protected fun showToolbarNavClose() {
        mGrinderActivity.setUpCrossIcon()
    }

    protected fun updateToolbarTitle(title: String) {
        mGrinderActivity.setToolbarTitle(title)
    }

    protected fun removeNavIcon() {
        mGrinderActivity.removeNavIcon()
    }

    protected fun navigateToScreen(resId: Int, args: Bundle? = null) {
        mGrinderActivity.mNavController.navigate(resId, args)
    }

    protected fun grinderBaseVM() = mGrinderActivity.mActivityViewModel

    internal inline fun Fragment.onBackPressed(crossinline mExecuteAction: () -> Unit) {
        this.requireActivity().onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = mExecuteAction()
            })
    }
}