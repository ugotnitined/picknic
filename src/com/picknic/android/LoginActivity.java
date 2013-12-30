package com.picknic.android;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

	private Button loginButton;
    private Dialog progressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onLoginButtonClick();	
			}
		});
    }
    private void onLoginButtonClick() {
        LoginActivity.this.progressDialog = ProgressDialog.show(
                        LoginActivity.this, "", "Logging in...", true);
        List<String> permissions = Arrays.asList("basic_info", "user_about_me",
                        "user_relationships", "user_birthday", "user_location");
        ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException err) {
                        LoginActivity.this.progressDialog.dismiss();
                        if (user == null) {
                                Log.d(PicknicApplication.TAG,
                                                "Uh oh. The user cancelled the Facebook login.");
                        } else if (user.isNew()) {
                                Log.d(PicknicApplication.TAG,
                                                "User signed up and logged in through Facebook!");
                                //go to next activity;
                        } else {
                                Log.d(PicknicApplication.TAG,
                                                "User logged in through Facebook!");
                                //go to next activity;                              
                        }
                }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
}
