package kz.kolesateam.memoryleaksexample.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kz.kolesateam.memoryleaksexample.R
import kz.kolesateam.memoryleaksexample.common.broadcast.MyReceiver
import kz.kolesateam.memoryleaksexample.common.context.ContextWrapper
import kz.kolesateam.memoryleaksexample.common.context.DefaultContextWrapper
import kz.kolesateam.memoryleaksexample.common.data.Response
import kz.kolesateam.memoryleaksexample.common.user.User
import kz.kolesateam.memoryleaksexample.details.data.DetailsRepository
import kz.kolesateam.memoryleaksexample.details.model.Details
import java.lang.Exception

private const val FILTER = "android.net.conn.CONNECTIVITY_CHANGE"
private const val ID_FORMAT = "Id: %s"

class DetailsActivity : AppCompatActivity(R.layout.activity_details) {

    private lateinit var broadcastReceiver: BroadcastReceiver
    private lateinit var progressBar: ProgressBar
    private lateinit var nameTextView: TextView
    private lateinit var surnameTextView: TextView
    private lateinit var idTextView: TextView

    private lateinit var user: User

    private val detailsRepository: DetailsRepository = DetailsRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        broadcastReceiver = MyReceiver()

        val contextWrapper: ContextWrapper = DefaultContextWrapper(this)
        User.createInstance(contextWrapper)
        user = User.instance

        initViews()
        setName()

        DetailsAsyncTask().execute()

        if (savedInstanceState == null) {
            InitNotification().initialize(applicationContext)
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(
                broadcastReceiver,
                IntentFilter(packageName + FILTER)
        )
    }

    private fun initViews() {
        progressBar = findViewById(R.id.activity_details_progress_bar)
        nameTextView = findViewById(R.id.activity_details_name)
        surnameTextView = findViewById(R.id.activity_details_surname)
        idTextView = findViewById(R.id.activity_details_id)
    }

    private fun setName() {
        nameTextView.text = user.userName
    }

    private fun showData(details: Details) {
        surnameTextView.text = details.surname
        idTextView.text = String.format(ID_FORMAT, details.id)
    }

    private fun showError(exception: Exception) {
        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
    }

    inner class DetailsAsyncTask : AsyncTask<Void, Void, Response<Details, Exception>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(
                vararg p0: Void?
        ): Response<Details, Exception> = try {
            val details = detailsRepository.loadDetails()

            Response.Success(details)
        } catch (e: Exception) {
            Response.Error(e)
        }

        override fun onPostExecute(result: Response<Details, Exception>) {
            super.onPostExecute(result)
            progressBar.visibility = View.GONE

            when (result) {
                is Response.Success -> showData(result.result)
                is Response.Error -> showError(result.error)
            }

        }
    }

    inner class InitNotification {

        fun initialize(context: Context) {
            Thread {
                //imagine that this is long work
                Thread.sleep(3000)

                runOnUiThread {
                    Toast.makeText(context, "Notification was initialize", Toast.LENGTH_LONG).show()
                }
            }.start()
        }
    }
}