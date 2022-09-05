package com.example.dics.fragment_flow

import android.os.Bundle
import android.view.View
import com.example.dics.base.BaseFragment

class FlowFragment5 : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBtn.setOnClickListener {
            requireActivity().finish()
        }
    }
}
