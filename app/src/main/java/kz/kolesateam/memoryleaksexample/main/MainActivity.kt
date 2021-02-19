package kz.kolesateam.memoryleaksexample.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kz.kolesateam.memoryleaksexample.R
import kz.kolesateam.memoryleaksexample.common.context.ContextWrapper
import kz.kolesateam.memoryleaksexample.common.context.DefaultContextWrapper
import kz.kolesateam.memoryleaksexample.common.user.User
import kz.kolesateam.memoryleaksexample.details.DetailsActivity

class MainActivity: AppCompatActivity(R.layout.activity_main) {

    private lateinit var user: User
    private lateinit var userName: TextView
    private lateinit var goToDetailsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contextWrapper: ContextWrapper = DefaultContextWrapper(this)
        User.createInstance(contextWrapper)
        user = User.instance

        userName = findViewById(R.id.activity_details_name)
        userName.text = user.userName

        goToDetailsButton = findViewById(R.id.activity_main_go_to_details)
        goToDetailsButton.setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java))
        }
    }

}