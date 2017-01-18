package net.styleru.biandroid.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import net.styleru.biandroid.R;
import net.styleru.biandroid.presenter.ILoginPresenter;
import net.styleru.biandroid.presenter.LoginPresenter;
import net.styleru.biandroid.view.ILoginView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Credentials;

/**
 * Created by tetawex on 15.11.16.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView
{
    public static ILoginPresenter presenter;

    @InjectView(R.id.logo)
    ImageView logoView;
    @InjectView(R.id.loginEditText)
    EditText loginEditText;
    @InjectView(R.id.passwordEditText)
    EditText passwordEditText;

    @InjectView(R.id.loginButton)
    AppCompatButton loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);

        if (getResources().getConfiguration()
                .orientation == Configuration.ORIENTATION_LANDSCAPE)
            logoView.setVisibility(View.GONE);

        if(presenter==null)
            presenter=new LoginPresenter(this);

        loginButton.setOnClickListener((v)->requestLogin());
    }

    @Override
    public void requestLogin()
    {
        presenter.onLoginRequested(loginEditText.getText().toString(),
                        passwordEditText.getText().toString());
    }

    @Override
    public void errorLog(String errorMsg)
    {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        loginEditText.setText(savedInstanceState.getString("login"));
        passwordEditText.setText(savedInstanceState.getString("password"));
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        // Save the user's current game state
        savedInstanceState.putString("login", loginEditText.getText().toString());
        savedInstanceState.putString("password", passwordEditText.getText().toString());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void switchActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
