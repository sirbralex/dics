package com.example.dics.base

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.dics.R

abstract class BaseFragment : Fragment(R.layout.fragment_base) {

    val goBtn: Button
        get() = requireView().findViewById(R.id.goBtn)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.title).text = this.javaClass.simpleName
    }
}
