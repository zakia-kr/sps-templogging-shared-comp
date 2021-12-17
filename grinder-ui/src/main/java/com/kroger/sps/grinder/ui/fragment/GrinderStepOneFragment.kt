package com.kroger.sps.grinder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.grinder.ui.activity.BaseFragment
import com.kroger.sps.grinder.ui.databinding.LayoutFirstFragmentBinding

class GrinderStepOneFragment : BaseFragment() {
    private lateinit var binding: LayoutFirstFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.layout_first_fragment,
            container,
            false
        )
        super.onCreate(savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbarTitle("Title One")
        showToolbarNavBack()
        binding.tempBtn.setOnClickListener {
            navigateToScreen(R.id.fragment_two_fragment)
        }
        println("VM GSO"+grinderBaseVM().toString())
    }
}