package com.kroger.sps.grinder.ui.activity

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.mobile.ui.activity.SPSBaseBindingActivity

abstract class BaseActivity<T : ViewBinding> : SPSBaseBindingActivity<T>() {
   /* internal fun setToolbarTitle(toolBarTitle: String) {
        findViewById<Toolbar>(R.id.main_toolbar).title = toolBarTitle
    }

    internal fun setUpBackArrow() {
        setUpToolBar()
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    internal fun setUpCrossIcon() {
        setUpToolBar()
        supportActionBar?.setHomeAsUpIndicator(R.drawable.close_icon_white)
    }

    private fun setUpToolBar() {
        val toolbar: Toolbar = findViewById<View>(R.id.main_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    internal fun removeNavIcon() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }*/
}