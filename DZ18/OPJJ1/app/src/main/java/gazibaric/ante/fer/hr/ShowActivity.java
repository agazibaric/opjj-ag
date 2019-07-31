package gazibaric.ante.fer.hr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Class represents activity that accepts parameter sent by {@link LifecycleActivity}
 * under the key "rezultat" and shows it using {@link Toast}.
 * Also that result is entered in {@link EditText} object that this activity contains.
 *
 * @author Ante Gazibaric
 */
public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        String result = (String) getIntent().getExtras().get("rezultat");

        EditText editText = findViewById(R.id.input_show);
        editText.setText(result);
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();

    }
}
