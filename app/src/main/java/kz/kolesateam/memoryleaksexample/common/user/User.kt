package kz.kolesateam.memoryleaksexample.common.user

import kz.kolesateam.memoryleaksexample.R
import kz.kolesateam.memoryleaksexample.common.context.ContextWrapper

class User private constructor(
        private val sContextWrapper: ContextWrapper
) {

    val userName: String
        get() = sContextWrapper.getString(R.string.user_name)

    companion object {
        private var sInstance: User? = null

        fun createInstance(contextWrapper: ContextWrapper) {
            sInstance = User(contextWrapper)
        }

        val instance: User
            get() = if (sInstance == null) {
                throw IllegalArgumentException("User cannot be null")
            } else {
                sInstance!!
            }
    }
}