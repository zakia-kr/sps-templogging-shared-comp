package com.kroger.sps.grinder.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.grinder.ui.adapter.SingleViewAdapter
import com.kroger.sps.grinder.ui.constants.GrinderConstants.GrinderFlows.*
import com.kroger.sps.grinder.ui.constants.GrinderConstants.fetchResourceId
import com.kroger.sps.grinder.ui.databinding.ActivityGrinderGlobalBinding
import com.kroger.sps.mobile.ui.activity.SPSBaseBindingActivity

class GrinderHomeActivity : SPSBaseBindingActivity<ActivityGrinderGlobalBinding>() {

    override val layoutId = R.layout.activity_grinder_global

    override fun inflateBinding(inflater: LayoutInflater) = ActivityGrinderGlobalBinding.inflate(inflater)

    override fun init() {
        setUpToolBar()
        setUpAccordion()
        setUpRecyclerView()
    }

    private fun setUpToolBar() {
        val toolbar: Toolbar = findViewById<View>(R.id.main_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpAccordion() {
        val pairs = mutableListOf<Pair<String, View>>()
        val rView = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(this@GrinderHomeActivity)
            adapter = SingleViewAdapter(
                listOf(
                    GRINDING_TRIM.flowName,
                    CASE_READY_PACK.flowName,
                    LOG_PRIMAL.flowName,
                    NO_GRINDS_TODAY.flowName
                )
            ) {
                println(it)
                val intentExpiringItem = Intent(
                    this@GrinderHomeActivity,
                    GrinderFlowActivity(R.layout.activity_grinder_flow)::class.java
                )
                this@GrinderHomeActivity.startActivity(intentExpiringItem)
            }
        }
        pairs.add(Pair(getString(R.string.label_grind), rView))
        binding.accordion.sectionPair = pairs
    }

    private fun setUpRecyclerView() {
        binding.rvView.apply {
            layoutManager = LinearLayoutManager(this@GrinderHomeActivity)
            adapter = SingleViewAdapter(
                listOf(
                    MEAT_SAW_INSPECTION.flowName,
                    CLEAN_GRINDER_MACHINE.flowName,
                    REPORTS.flowName
                )
            ) {
                println(it.fetchResourceId())
            }
            addItemDecoration(DividerItemDecoration(this@GrinderHomeActivity, DividerItemDecoration.VERTICAL))
        }
    }
}