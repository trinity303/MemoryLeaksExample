package kz.kolesateam.memoryleaksexample.common.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver: BroadcastReceiver() {

    override fun onReceive(
            context: Context,
            intent: Intent
    ) {
        Toast.makeText(context, "onReceive", Toast.LENGTH_LONG).show()
    }

}