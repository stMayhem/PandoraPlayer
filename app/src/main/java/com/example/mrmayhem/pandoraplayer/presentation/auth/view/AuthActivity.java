package com.example.mrmayhem.pandoraplayer.presentation.auth.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mrmayhem.pandoraplayer.AndroidApp;
import com.example.mrmayhem.pandoraplayer.R;
import com.example.mrmayhem.pandoraplayer.di.auth.AuthModule;
import com.example.mrmayhem.pandoraplayer.presentation.auth.presenter.IAuthPresenter;
import com.example.mrmayhem.pandoraplayer.presentation.playlist.view.PlaylistActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthActivity extends AppCompatActivity implements IAuthView{
    @BindView(R.id.progress_bar)ProgressBar progressBar;
    @BindView(R.id.input_layout_email)TextInputLayout emailView;
    @BindView(R.id.input_layout_password)TextInputLayout passwordView;
    @BindView(R.id.email_sign_in_button)Button loginBtn;

    @Inject
    IAuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ButterKnife.bind(this);
        AndroidApp.get(AuthActivity.this).applicationComponent().plus(new AuthModule()).inject(this);
        presenter.attachView(this);

        emailView.getEditText().setText("fsdfsd@mail.ru");
        passwordView.getEditText().setText("fs324wrh");

        passwordView.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                loginBtn.performClick();
                return true;
            }
            return false;
        });
    }
    @OnClick(R.id.email_sign_in_button)
    public void onSignInClick(){
        emailView.setError(null);
        passwordView.setError(null);
        closeKeyboard();
        presenter.authUser(
                emailView.getEditText().getText().toString(),
                passwordView.getEditText().getText().toString());
    }
    @Override
    public void showPlaylistActivity() {
        Intent intent = new Intent(this, PlaylistActivity.class);
        startActivity(intent);
    }
    @Override
    public void showLoading() {
        emailView.setEnabled(false);
        passwordView.setEnabled(false);
        loginBtn.setVisibility(View.GONE);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        emailView.setEnabled(true);
        passwordView.setEnabled(true);
        loginBtn.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
