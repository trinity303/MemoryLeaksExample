package kz.kolesateam.memoryleaksexample.common.context

import android.content.Context

class DefaultContextWrapper(
        private val context: Context
): ContextWrapper {

    override fun getString(res: Int): String = context.getString(res)
}