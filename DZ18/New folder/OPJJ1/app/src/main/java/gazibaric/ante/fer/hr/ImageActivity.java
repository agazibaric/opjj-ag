package gazibaric.ante.fer.hr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import gazibaric.ante.fer.hr.gazibaric.ante.fer.hr.data.ImageResponse;

public class ImageActivity extends AppCompatActivity {


    @BindView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ButterKnife.bind(this);

        ImageResponse response = (ImageResponse) getIntent().
                getSerializableExtra(LifecycleActivity.EXTRA_RESULT);

        Glide.with(this)
                .load(response.getUrl())
                .into(imageView);

    }
}
