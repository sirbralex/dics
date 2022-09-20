package com.example.dics.context

import com.example.dics.component.WeakRefDiComponent
import com.example.dics.log.log
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import java.util.*

/**
 * Unsafe for usage.
 * DiContext will not be cleared if it has components which refer to `this`
 */
val Any.weakRefDiContext: DiContext
    get() = weakRefDiContextStorageInstance.getContextFor(this)

/**
 * There is no way clear weak contexts automatically.
 * This method must be called sometimes.
 */
fun cleanupWeakDiContexts() {
    weakRefDiContextStorageInstance.clear()
}

private val weakRefDiContextStorageInstance = WeakRefDiContextStorage()

private class WeakRefDiContextStorage {

    private val map = WeakHashMap<Any, DiContextHolderRef>()
    private val referenceQueue = ReferenceQueue<Any>()

    fun getContextFor(obj: Any): DiContext {
        log("access Any.weakRefDiContext")
        clear()
        val existingContext = map[obj]?.diContext
        if (existingContext != null) {
            return existingContext
        }
        val newContext: ClearableDiContext = createDiContext(
            "weakRefDiContext",
            WeakRefDiComponent::class,
            rootDiContext!!
        )
        map[obj] = DiContextHolderRef(obj, referenceQueue, newContext)
        return newContext
    }

    fun clear() {
        var ref: Any?
        while (referenceQueue.poll().also { ref = it } != null) {
            log("clearing Any.weakRefDiContext")
            (ref as DiContextHolderRef).diContext.onCleared()
        }
    }

    private class DiContextHolderRef(
        referent: Any,
        q: ReferenceQueue<in Any>,
        val diContext: ClearableDiContext
    ) : WeakReference<Any>(referent, q)

}
