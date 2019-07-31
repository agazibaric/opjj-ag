package gazibaric.ante.fer.hr;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.data.ImageResponse;

/**
 * Class represents activity that accepts parameter sent by {@link LifecycleActivity}
 * under the key that {@link LifecycleActivity}
 * specifies as public constant LifecycleActivity.EXTRA_RESULT
 * and shows it using {@link Toast}.
 * Also that result is entered in {@link EditText} object that this activity contains.
 *
 * @author Ante Gazibaric
 */
public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        ImageResponse response = (ImageResponse) getIntent().getSerializableExtra(LifecycleActivity.EXTRA_RESULT);

        EditText inputResult = findViewById(R.id.input_show);
        inputResult.setText(response.getUrl());
        Toast.makeText(this, response.getUrl(), Toast.LENGTH_LONG).show();

        Button btnSendBack = findViewById(R.id.btn_send_back);
        btnSendBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle extras = new Bundle();
                ImageResponse newResponse = new ImageResponse();
                newResponse.setUrl(inputResult.getText().toString());

                extras.putSerializable(LifecycleActivity.EXTRA_RESULT, newResponse);
                intent.putExtras(extras);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}
