package com.kroger.sps.grinder.ui.activity

import android.content.Context
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.grinder.ui.viewmodel.CustomViewModelFactory
import com.kroger.sps.grinder.ui.viewmodel.GrinderBaseViewModel
import com.kroger.sps.mobile.ui.activity.SPSBaseActivity
import kotlin.properties.Delegates

class GrinderFlowActivity() : SPSBaseActivity() {

    internal val mActivityViewModel by viewModels<GrinderBaseViewModel> { CustomViewModelFactory(kafContext) }

    internal lateinit var mNavController: NavController

    companion object {
        private var mLayoutDetail by Delegates.notNull<Int>()
    }

    constructor (mActivityLayoutID: Int) : this() {
        mLayoutDetail = mActivityLayoutID
    }

    override val layoutId get() = mLayoutDetail
    override fun init() {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .apply { mNavController = this.navController }
        setUpToolBar()
        println("VM GFA"+mActivityViewModel.toString())
    }

    internal fun setToolbarTitle(toolBarTitle: String) {
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
    }
}