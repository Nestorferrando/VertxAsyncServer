package com.nospoon.vertxserver.core.model


import com.nospoon.jpromises.Promise
import com.nospoon.jpromises.Promises

import java.util.function.Supplier

/**
 * Created by Nestor on 7/26/2016.
 */
class MessageQueue {


    private var currentPromise: Promise<Void>  = Promises.resolve<Void>(null)
    private var enqueuedMessages: Int = 0


    fun enqueueHandler(supplier: () -> Promise<Void>) {
        enqueuedMessages++
        currentPromise = currentPromise!!.pipe {
            enqueuedMessages--
            supplier.invoke()
        }
    }

}
