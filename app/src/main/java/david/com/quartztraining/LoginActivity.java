package david.com.quartztraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    //Widget member variables from activity_login.xml
    EditText mUsernameInput; //Username EditText
    EditText mPasswordInput; //Password EditText
    Button mLogInButton;     //Log-in button

    //Variables to store user input
    private String mUsername;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inflate username and password EditText widgets + button.
        mUsernameInput = (EditText)findViewById(R.id.username_input);
        mPasswordInput = (EditText)findViewById(R.id.password_input);
        mLogInButton = (Button)findViewById(R.id.login_button);

        //Add on click listener to log in button
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("username: ", mUsernameInput.getText().toString());
                Log.v("password: ", mPasswordInput.getText().toString());

                //Start main activity
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);

                //TODO - Pass mUsername and mPassword from this activity to MainActivity.java
            }
        });


    }
}
