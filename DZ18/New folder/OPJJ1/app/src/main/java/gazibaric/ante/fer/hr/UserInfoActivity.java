package gazibaric.ante.fer.hr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.data.UserInfo;

public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.surname)
    TextView surname;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.phone)
    TextView phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ButterKnife.bind(this);

        UserInfo info = (UserInfo) getIntent().getSerializableExtra(LifecycleActivity.EXTRA_USER);

        Glide.with(this)
                .load(info.getAvatar())
                .into(imageView);

        age.setText(String.format(getString(R.string.age_placeholder), info.getAge()));
        name.setText(String.format(getString(R.string.name_placeholder), info.getName()));
        surname.setText(String.format(getString(R.string.surname_placeholder), info.getSurname()));
        email.setText(String.format(getString(R.string.email_placeholder), info.getEmail()));
        phone.setText(String.format(getString(R.string.phone_placeholder), info.getPhoneNumber()));

    }
}
