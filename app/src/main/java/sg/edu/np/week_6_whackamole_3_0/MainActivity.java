package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
        1. This is the main page for user to log in
        2. The user can enter - Username and Password
        3. The user login is checked against the database for existence of the user and prompts
           accordingly via Toastbox if user does not exist. This loads the level selection page.
        4. There is an option to create a new user account. This loads the create user page.
     */
    private static final String FILENAME = "MainActivity.java";
    private static final String TAG = "Whack-A-Mole3.0!";
    EditText userNameEditText;
    EditText pwEditText;
    Button loginBtn;
    TextView signUpButton;
    MyDBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        /* Hint:
            This method creates the necessary login inputs and the new user creation ontouch.
            It also does the checks on button selected.
            Log.v(TAG, FILENAME + ": Create new user!");
            Log.v(TAG, FILENAME + ": Logging in with: " + getUsername.getText().toString() + ": " + getPassword.getText().toString());
            Log.v(TAG, FILENAME + ": Valid User! Logging in");
            Log.v(TAG, FILENAME + ": Invalid user!");
        */
        userNameEditText = (EditText) findViewById(R.id.loginUN);
        pwEditText = (EditText) findViewById(R.id.loginPW);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        signUpButton = (TextView) findViewById(R.id.signUpButton);
        handler = new MyDBHandler(this, "WhackAMole.db", null, 1);

    }

    public boolean isValidUser(String userName, String password){

        /* HINT:
            This method is called to access the database and return a true if user is valid and false if not.
            Log.v(TAG, FILENAME + ": Running Checks..." + dbData.getMyUserName() + ": " + dbData.getMyPassword() +" <--> "+ userName + " " + password);
            You may choose to use this or modify to suit your design.
         */
        boolean result;
        UserData dbData = handler.findUser(userName);
        if (dbData != null) {
            Log.v(TAG, FILENAME + ": Running Checks..." + dbData.getMyUserName() + ": " + dbData.getMyPassword() +" <--> "+ userName + " " + password);
            if (dbData.getMyUserName().equals(userName) && dbData.getMyPassword().equals(password)) {

                result = true;
            }
            else {
                result = false;
            }
        }
        else {
            result = false;
        }

        return result;
    }

    public void onClick(View v) {
        Log.v(TAG, FILENAME + ": Create new user!");
        Intent in = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(in);
    }

    //when Login button is clicked
    public void OnClick2(View v) {
        Log.v(TAG, FILENAME + ": Logging in with: " + userNameEditText.getText().toString() + ": " + pwEditText.getText().toString());
        String inputUsername = userNameEditText.getText().toString();
        String inputPw = pwEditText.getText().toString();
        boolean result = isValidUser(inputUsername, inputPw);

        if (result) {
            Log.v(TAG, FILENAME + ": Valid User! Logging in");

            Intent in = new Intent(MainActivity.this, Main3Activity.class);
            in.putExtra("Username", inputUsername);
            startActivity(in);
        }
        else {
            makeToast("Invalid Username or Password");
            Log.v(TAG, FILENAME + ": Invalid user!");
        }
    }

    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}