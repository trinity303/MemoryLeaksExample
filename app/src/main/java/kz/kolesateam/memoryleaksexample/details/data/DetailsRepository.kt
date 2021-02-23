package kz.kolesateam.memoryleaksexample.details.data

import androidx.annotation.WorkerThread
import kz.kolesateam.memoryleaksexample.details.model.Details
import java.lang.Exception

class DetailsRepository {

    @WorkerThread
    @Throws(Exception::class)
    fun loadDetails(): Details {
        Thread.sleep(2000)

        return Details(1, "Obi Wan", "Kenobi")
    }
}