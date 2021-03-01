package kz.kolesateam.memoryleaksexample.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kz.kolesateam.memoryleaksexample.R
import kz.kolesateam.memoryleaksexample.common.context.ContextWrapper
import kz.kolesateam.memoryleaksexample.common.context.DefaultContextWrapper
import kz.kolesateam.memoryleaksexample.common.user.User
import kz.kolesateam.memoryleaksexample.details.DetailsActivity
import java.util.Random

class MainActivity: AppCompatActivity(R.layout.activity_main) {

    private lateinit var user: User
    private lateinit var userName: TextView
    private lateinit var goToDetailsButton: Button
    private lateinit var calculateButton: Button
    private lateinit var calculateResultTextView: TextView

    private var randomResult: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contextWrapper: ContextWrapper = DefaultContextWrapper(this)
        User.createInstance(contextWrapper)
        user = User.instance

        userName = findViewById(R.id.activity_main_name)
        userName.text = user.userName

        goToDetailsButton = findViewById(R.id.activity_main_go_to_details)
        goToDetailsButton.setOnClickListener {
            startActivity(
                    Intent(
                            this,
                            DetailsActivity::class.java
                    )
            )
        }

        calculateResultTextView = findViewById(R.id.activity_details_calculate_result)
        calculateButton = findViewById(R.id.activity_main_calculate)
        calculateButton.setOnClickListener {
            //imagine that this is long work
            Thread {
                Thread.sleep(3000)
                randomResult = Random().nextInt(20)

                runOnUiThread {
                    calculateResultTextView.text = getString(
                            R.string.activity_main_calculated_result_fmt,
                            randomResult.toString()
                    )
                }
            }.start()
        }

        //if (savedInstanceState == null) {
        //InitLogger().initialize(this)
        //}
    }


    inner class InitLogger {

        fun initialize(context: Context) {
            Thread {
                //imagine that this is long work
                Thread.sleep(3000)

                runOnUiThread {
                    Toast.makeText(
                            context,
                            "Logger was initialize",
                            Toast.LENGTH_LONG
                    ).show()
                }
            }.start()
        }
    }
}