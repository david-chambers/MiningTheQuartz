package david.com.quartztraining;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //Widget member variables from activity_login.xml
    EditText mUsernameInput; //Username EditText
    EditText mPasswordInput; //Password EditText
    Button mLogInButton;     //Log-in button
    Button mResetButton;

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
        mResetButton = (Button)findViewById(R.id.resetbutton);

        //Add on click listener to log in button
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUsernameInput.getText().toString().equalsIgnoreCase("") & mPasswordInput.getText().toString().equalsIgnoreCase("")){
                mUsernameInput.setBackgroundColor(Color.RED);
                mPasswordInput.setBackgroundColor(Color.RED);
                    //output message to user on ui thread and break control
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Please provide username and password.", Toast.LENGTH_LONG).show();
                        }
                    });
                    return;
                }

                Log.v("username: ", mUsernameInput.getText().toString());
                Log.v("password: ", mPasswordInput.getText().toString());


                //Start main activity
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("usernameKey", mUsernameInput.getText().toString().trim());
                i.putExtra("passwordKey", mPasswordInput.getText().toString().trim());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "Attempting to login to portal", Toast.LENGTH_LONG).show();
                    }
                });
                startActivity(i);





                //TODO - Pass mUsername and mPassword from this activity to MainActivity.java
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsernameInput.setText("");
                mPasswordInput.setText("");
            }
        });
    }


}
