package gazibaric.ante.fer.hr;

import android.content.Intent;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.data.ImageResponse;
import gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.data.UserInfo;
import gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.networking.FetcherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    /**
     * Key for data exchange between activities.
     */
    public static final String EXTRA_RESULT = "rezultat";
    public static final String EXTRA_USER = "user_info";

    /**
     * Request code.
     */
    public static final int REQUEST_SHOW = 140;

    @BindView(R.id.label_result)
    TextView labelResult;
    @BindView(R.id.input_first)
    EditText inputFirst;
    @BindView(R.id.input_second)
    EditText inputSecond;

    Retrofit retrofit;
    FetcherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        ButterKnife.bind(this);

        Button btnComposeEmail = findViewById(R.id.btn_compose_email);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://ana.baotic.net/")
                .build();

        service = retrofit.create(FetcherService.class);

        // button listener that redirects you to email composing activity
        btnComposeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LifecycleActivity.this, ComposeMailActiviy.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SHOW && resultCode == RESULT_OK && data != null) {
            ImageResponse response = (ImageResponse) data.getSerializableExtra(EXTRA_RESULT);
            Toast.makeText(this, response.getUrl(), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.btn_send)
    void onSend() {

        service.getImage().enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                Intent intent = new Intent(LifecycleActivity.this, ImageActivity.class);
                Bundle extras = new Bundle();

                extras.putSerializable(EXTRA_RESULT, response.body());
                intent.putExtras(extras);

                startActivityForResult(intent, REQUEST_SHOW);
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.btn_calculate)
    void onCalculate() {
        String first = inputFirst.getText().toString();
        String second = inputSecond.getText().toString();

        int firstNum = 0;
        int secondNum = 0;

        try {
            firstNum = Integer.parseInt(first);
            secondNum = Integer.parseInt(second);
        } catch (NumberFormatException ignorable) {
        }

        if (secondNum != 0) {
            labelResult.setText(String.valueOf(firstNum / secondNum));
        } else {
            labelResult.setText(R.string.error_operation);
        }
    }

    @OnClick(R.id.btn_show_user)
    void onShowUser() {
        service.getUserInfo().enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                Intent intent = new Intent(LifecycleActivity.this, UserInfoActivity.class);
                Bundle extras = new Bundle();

                extras.putSerializable(EXTRA_USER, response.body());
                intent.putExtras(extras);

                startActivity(intent);
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

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
