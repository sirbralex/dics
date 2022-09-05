package com.example.dics.context

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.dics.component.ActivityDiComponent
import com.example.dics.component.ActivityNonConfigDiComponent
import com.example.dics.component.ApplicationDiComponent
import com.example.dics.component.FragmentDiComponent
import com.example.dics.component.FragmentNonConfigDiComponent
import com.example.dics.component.FragmentViewDiComponent
import com.example.dics.log.log

internal var rootDiContext: ClearableDiContext? = null

val Application.diContext: DiContext
    get() {
        log("access Application.diContext")
        if (rootDiContext == null) {
            rootDiContext = createDiContext("Application.diContext", ApplicationDiComponent::class)
        }
        return rootDiContext!!
    }

val ComponentActivity.diContext: DiContext
    get() {
        log("access Activity.diContext, activity = ${this@diContext}")
        val viewModel = ViewModelProvider(this, DiContextViewModelFactory()).get(DiContextViewModel::class.java)
        if (viewModel.ownerDiContext == null) {
            viewModel.ownerDiContext =
                createDiContext("Activity.diContext[$this]", ActivityDiComponent::class, nonConfigDiContext)
            lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(source: LifecycleOwner) {
                    log("clearing Activity.diContext, activity = $this")
                    viewModel.ownerDiContext!!.onCleared()
                    viewModel.ownerDiContext = null
                }
            })
        }
        return viewModel.ownerDiContext!!
    }

val ComponentActivity.nonConfigDiContext: DiContext
    get() {
        log("access Activity.nonConfigDiContext, activity = $this")
        val viewModel = ViewModelProvider(this, DiContextViewModelFactory()).get(DiContextViewModel::class.java)
        if (viewModel.nonConfigDiContext == null) {
            viewModel.nonConfigDiContext = createDiContext(
                "Activity.nonConfigDiContext",
                ActivityNonConfigDiComponent::class,
                application.diContext
            )
            viewModel.onClearedCallback = {
                log("clearing Activity.nonConfigDiContext, activity = $this")
                viewModel.nonConfigDiContext!!.onCleared()
            }
        }
        return viewModel.nonConfigDiContext!!
    }

val Fragment.viewDiContext: DiContext
    get() {
        log("access Fragment.viewDiContext, fragment = ${this@viewDiContext}")
        val viewModel = ViewModelProvider(this, DiContextViewModelFactory()).get(DiContextViewModel::class.java)
        if (viewModel.viewDiContext == null) {
            viewModel.viewDiContext =
                createDiContext("Fragment.viewDiContext", FragmentViewDiComponent::class, diContext)
            viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(source: LifecycleOwner) {
                    log("clearing Fragment.viewDiContext, fragment = $this")
                    viewModel.viewDiContext!!.onCleared()
                    viewModel.viewDiContext = null
                }
            })
        }
        return viewModel.viewDiContext!!
    }

val Fragment.diContext: DiContext
    get() {
        log("access Fragment.diContext, fragment = ${this@diContext}")
        val viewModel = ViewModelProvider(this, DiContextViewModelFactory()).get(DiContextViewModel::class.java)
        if (viewModel.ownerDiContext == null) {
            val parentDiContext = parentFragment?.diContext ?: requireActivity().diContext
            viewModel.ownerDiContext =
                createDiContext("Fragment.diContext", FragmentDiComponent::class, nonConfigDiContext, parentDiContext)
            lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(source: LifecycleOwner) {
                    log("clearing Fragment.diContext, fragment = $this")
                    viewModel.ownerDiContext!!.onCleared()
                    viewModel.ownerDiContext = null
                }
            })
        }
        return viewModel.ownerDiContext!!
    }

val Fragment.nonConfigDiContext: DiContext
    get() {
        log("access Fragment.nonConfigDiContext, fragment = $this")
        val viewModel = ViewModelProvider(this, DiContextViewModelFactory()).get(DiContextViewModel::class.java)
        if (viewModel.nonConfigDiContext == null) {
            val parentDiContext = parentFragment?.nonConfigDiContext ?: requireActivity().nonConfigDiContext
            viewModel.nonConfigDiContext =
                createDiContext("Fragment.nonConfigDiContext", FragmentNonConfigDiComponent::class, parentDiContext)
            viewModel.onClearedCallback = {
                log("clearing Fragment.nonConfigDiContext, fragment = $this")
                viewModel.nonConfigDiContext!!.onCleared()
            }
        }
        return viewModel.nonConfigDiContext!!
    }

