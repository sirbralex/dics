package com.example.dics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.dics.activtity_flow.FlowActivity1
import com.example.dics.component_key.ComponentKeyActivity
import com.example.dics.fragment_flow.FragmentFlowActivity
import com.example.dics.fragment_hierarchy.FragmentHierarchyActivity
import com.example.dics.permanent_component.PermanentComponentActivity
import com.example.dics.weak_ref_di_context.WeakRefDiContextActivity

class RootActivity : AppCompatActivity(R.layout.activity_root) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.activity_flow_btn).setOnClickListener {
            startActivity(Intent(this, FlowActivity1::class.java))
        }
        findViewById<Button>(R.id.fragment_flow_btn).setOnClickListener {
            startActivity(Intent(this, FragmentFlowActivity::class.java))
        }
        findViewById<Button>(R.id.fragment_hierarchy_btn).setOnClickListener {
            startActivity(Intent(this, FragmentHierarchyActivity::class.java))
        }
        findViewById<Button>(R.id.permanent_component_btn).setOnClickListener {
            startActivity(Intent(this, PermanentComponentActivity::class.java))
        }
        findViewById<Button>(R.id.weak_ref_di_context_btn).setOnClickListener {
            startActivity(Intent(this, WeakRefDiContextActivity::class.java))
        }
        findViewById<Button>(R.id.component_key_btn).setOnClickListener {
            startActivity(Intent(this, ComponentKeyActivity::class.java))
        }
    }
}
