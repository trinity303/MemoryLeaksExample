package kz.kolesateam.memoryleaksexample.main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import kz.kolesateam.memoryleaksexample.R;
import kz.kolesateam.memoryleaksexample.common.context.ContextWrapper;
import kz.kolesateam.memoryleaksexample.common.context.DefaultContextWrapper;
import kz.kolesateam.memoryleaksexample.common.data.Response;
import kz.kolesateam.memoryleaksexample.common.user.User;
import kz.kolesateam.memoryleaksexample.details.DetailsActivity;
import kz.kolesateam.memoryleaksexample.details.model.Details;

class MainActivity2 extends AppCompatActivity {

    private User user;
    private TextView calculateResultTextView;

    private int randomResult = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContextWrapper contextWrapper = new DefaultContextWrapper(this);
        User.Companion.createInstance(contextWrapper);
        user = User.Companion.getInstance();

        TextView userName = findViewById(R.id.activity_main_name);
        userName.setText(user.getUserName());

        Button goToDetailsButton = findViewById(R.id.activity_main_go_to_details);
        goToDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                startActivity(intent);
            }
        });

        calculateResultTextView = findViewById(R.id.activity_details_calculate_result);
        Button calculateButton = findViewById(R.id.activity_main_calculate);
        calculateButton.setOnClickListener(view -> new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            randomResult = new Random().nextInt(20);

            runOnUiThread(() -> {
                String str = getString(
                        R.string.activity_main_calculated_result_fmt,
                        String.valueOf(randomResult)
                );
                calculateResultTextView.setText(str);
            });
        }).start());

        InitLogger initLogger = new InitLogger();
        initLogger.initialize(this);
    }


    class InitLogger {

        void initialize(Context context) {
            new Thread(() -> {
                //imagine that this is long work
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(() -> {
                    Toast.makeText(context, "Notification was initialize", Toast.LENGTH_LONG).show();
                });
            }).start();
        }
    }
}