package kz.kolesateam.memoryleaksexample.common.context

import androidx.annotation.StringRes

interface ContextWrapper {

    fun getString(@StringRes res: Int): String
}