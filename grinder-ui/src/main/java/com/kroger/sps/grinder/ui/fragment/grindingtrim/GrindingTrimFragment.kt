package com.kroger.sps.grinder.ui.fragment.grindingtrim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kroger.design.components.KdsDatePickerDialogFragment
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.grinder.ui.base.BaseFragment
import com.kroger.sps.grinder.ui.databinding.LayoutGrindingTrimBinding
import java.util.*

class GrindingTrimFragment : BaseFragment() {

    private lateinit var mLayoutGrindingTrimBinding: LayoutGrindingTrimBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mLayoutGrindingTrimBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_grinding_trim, container, false)
        super.onCreate(savedInstanceState)
        return mLayoutGrindingTrimBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbarTitle("Grinding Trim")
        showToolbarNavBack()
        mLayoutGrindingTrimBinding.btnNext.setOnClickListener {
            navigate(R.id.action_grind_trim_to_summary)
        }
        this@GrindingTrimFragment.onBackPressed {
            activity?.finish()
        }
        assignDate()
    }

    private fun assignDate() = mLayoutGrindingTrimBinding.dpGrindingTrimSellByDate.apply {
        setOnClickListener {
            val datePicker = KdsDatePickerDialogFragment.newInstance(
                this, this.getDate(),
                minDate = Calendar.getInstance(),

                )
            datePicker.show(parentFragmentManager, KdsDatePickerDialogFragment.TAG)
            datePicker.setOnClickListener(object :
                KdsDatePickerDialogFragment.DatePickerClickListener {
                override fun onDateModified(date: Calendar) {
                    mLayoutGrindingTrimBinding.dpGrindingTrimSellByDate.setCurrentDate(date)
                }
            })
        }
    }

}
