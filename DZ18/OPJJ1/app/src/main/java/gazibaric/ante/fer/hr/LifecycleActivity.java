package gazibaric.ante.fer.hr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Class represents launcher activity of app.
 * It offers user to divide two numbers,
 * send that result to {@link ShowActivity}
 * or to compose email by clicking on specified button
 * which redirects you to {@link ComposeMailActiviy}.
 *
 * @author Ante Gazibaric
 */
public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        EditText inputFirst = findViewById(R.id.input_first);
        EditText inputSecond = findViewById(R.id.input_second);
        TextView labelResult = findViewById(R.id.label_result);

        Button btnCalculate = findViewById(R.id.btn_calculate);
        Button btnSend = findViewById(R.id.btn_send);
        Button btnComposeEmail = findViewById(R.id.btn_compose_email);

        // add button listener that divides two numbers
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String first = inputFirst.getText().toString();
                    String second = inputSecond.getText().toString();

                    int firstNum = 0;
                    int secondNum = 0;

                try {
                    firstNum = Integer.parseInt(first);
                    secondNum = Integer.parseInt(second);

                } catch (NumberFormatException ex) {
                }

                if (secondNum != 0) {
                    labelResult.setText(String.valueOf(firstNum / secondNum));
                } else {
                    labelResult.setText(R.string.error_operation);
                }
            }
        });

        // add button listener that sends result of dividing to ShowActivity
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LifecycleActivity.this, ShowActivity.class);
                Bundle extras = new Bundle();
                extras.putString("rezultat", labelResult.getText().toString());
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        // add button listener that redirects you to email composing activity
        btnComposeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LifecycleActivity.this, ComposeMailActiviy.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "Pozvao onpause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "Pozvao onresume");
    }

}
