package com.viking.login;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {



    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:" +
            ";<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

    private TextInputLayout textInputLayout_u, textInputLayout_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInputLayout_u = (TextInputLayout) findViewById(R.id.usernameWrapper);
        textInputLayout_p = (TextInputLayout) findViewById(R.id.passwordWrapper);

        textInputLayout_u.setHint("User Name");
        textInputLayout_p.setHint("Pass Code");
    }

    public void onLoginClicked(View view) {
        hideKeyboard();
        String username = textInputLayout_u.getEditText().getText().toString();
        String password = textInputLayout_p.getEditText().getText().toString();
        if (!validateEmail(username)) {
            textInputLayout_u.setError("Not a valid email address!");
        } else if (!validatePassword(password)) {
            textInputLayout_p.setError("Not a valid password!");
        } else {
            textInputLayout_u.setErrorEnabled(false);
            textInputLayout_p.setErrorEnabled(false);
            doLogin();
        }
    }

    public void doLogin() {
        Toast.makeText(getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
        // TODO: login procedure; not within the scope of this tutorial.
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
