package com.example.dics.permanent_component

import android.os.Bundle
import android.view.View
import com.example.dics.base.BaseFragment

class Level_2_2_Fragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBtn.setOnClickListener {
            requireActivity().finish()
        }
    }
}
