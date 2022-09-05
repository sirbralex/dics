package com.example.dics.permanent_component

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.dics.R
import com.example.dics.base.BaseFragment

class Level_1_Fragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBtn.isVisible = false
        childFragmentManager.beginTransaction()
            .add(R.id.container, Level_2_1_Fragment())
            .commit()
    }
}
