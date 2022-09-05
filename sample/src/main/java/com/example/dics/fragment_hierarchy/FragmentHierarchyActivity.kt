package com.example.dics.fragment_hierarchy

import android.os.Bundle
import androidx.core.view.isVisible
import com.example.dics.R
import com.example.dics.base.BaseActivity
import com.example.dics.fragment_hierarchy.Level_0_Fragment

class FragmentHierarchyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goBtn.isVisible = false
        supportFragmentManager.beginTransaction()
            .add(R.id.container, Level_0_Fragment())
            .commit()
    }
}
