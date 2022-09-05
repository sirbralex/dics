package com.example.dics.fragment_hierarchy

import android.os.Bundle
import android.view.View
import com.example.dics.R
import com.example.dics.base.BaseFragment
import com.example.dics.context.diContext
import com.example.dics.contextimpl.obtainComponent
import com.example.dics.permanent_component.Level_2_2_Fragment

class Level_2_Fragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diContext.obtainComponent(Level_0_FragmentComponent::class)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBtn.setOnClickListener {
            requireActivity().finish()
        }
    }
}
