package gazibaric.ante.fer.hr;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class represents activity that offers user to compose email.
 * It contains "Send to", "Title" and "Message" {@link EditText} objects
 * that are used for constructing the email.
 * By clicking on button "Send", user is redirected to email app that phone offers.
 * In "cc" field is automatically added two emails that are given in assignment.
 *
 * @author Ante Gazibaric
 */
public class ComposeMailActiviy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_mail_activiy);

        EditText inputSendTo = findViewById(R.id.input_send_to);
        EditText inputTitle = findViewById(R.id.input_title);
        EditText inputMessage = findViewById(R.id.input_message);
        TextView labelEmail = findViewById(R.id.label_email);
        Button btnSend = findViewById(R.id.btn_send_email);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputSendTo.getText().toString().trim();
                if (email.isEmpty()) {
                    labelEmail.setText(R.string.empty_email);
                    return;
                }

                if (!validateEmail(email)) {
                    labelEmail.setText(R.string.error_email);
                    return;
                }

                String title = inputTitle.getText().toString().trim();
                if (title.isEmpty()) {
                    labelEmail.setText(R.string.empty_email);
                    return;
                }

                if (!Character.isUpperCase(title.charAt(0))) {
                    labelEmail.setText(R.string.error_title);
                    return;
                }

                String message = inputMessage.getText().toString();
                if (message.isEmpty()) {
                    labelEmail.setText(R.string.empty_email);
                    return;
                }

                String mailto = "mailto:" + email +
                        "?cc=marcupic@gmail.com" +
                        "&cc=ana@baotic.org";

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(mailto));
                intent.putExtra(Intent.EXTRA_SUBJECT, title);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                startActivity(Intent.createChooser(intent, "Send email"));

            }
        });

    }

    /**
     * Valid email format regular expression.
     */
    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * Method that checks if given email has valid format.
     *
     * @param email email that is checked
     * @return      <code>true</code> if email is valid,
     *              <code>false</code> otherwise
     */
    private static boolean validateEmail(String email) {
        Matcher matcher = EMAIL_REGEX .matcher(email);
        return matcher.find();
    }

}
