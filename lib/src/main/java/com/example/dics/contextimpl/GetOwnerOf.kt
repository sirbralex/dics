package com.example.dics.contextimpl

import com.example.dics.component.DiComponent
import kotlin.reflect.KClass

internal fun DiContextImpl.getOwnerOf(kClass: KClass<out DiComponent>): DiContextImpl {
    val candidates = mutableSetOf<DiContextImpl>()
    internalGetOwnerOf(kClass, candidates)
    val owner = when (candidates.size) {
        0 -> throw IllegalStateException("No owner registered for $kClass")
        1 -> candidates.first()
        else -> {
            val candidatesWithDirectDeclaration = candidates.filter { candidate ->
                candidate.associatedComponents.contains(kClass)
            }
            if (candidatesWithDirectDeclaration.size == 1) {
                candidatesWithDirectDeclaration.first()
            } else if (candidatesWithDirectDeclaration.size > 1) {
                throw IllegalStateException("Multiple possible owners founded: ${candidatesWithDirectDeclaration.joinToString()}")
            } else {
                throw IllegalStateException("Multiple possible owners founded: ${candidates.joinToString()}")
            }
        }
    }
    return owner
}

private fun DiContextImpl.internalGetOwnerOf(
    kClass: KClass<out DiComponent>,
    candidates: MutableSet<DiContextImpl>
) {
    associatedComponents.forEach {
        if (it.java.isAssignableFrom(kClass.java)) {
            candidates.add(this)
            return@forEach
        }
    }
    delegates.forEach { delegate ->
        delegate.internalGetOwnerOf(kClass, candidates)
    }
}
