package com.shzlabs.themenu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.shzlabs.themenu.R;
import com.shzlabs.themenu.ui.base.BaseActivity;
import com.shzlabs.themenu.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {

    private static final int RC_SIGN_IN = 9001;

    @Inject
    LoginPresenter presenter;

    @Inject
    FirebaseAuth firebaseAuth;

    @BindView(R.id.welcome_text_view)
    TextView welcomeTextView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.google_sign_in_button)
    SignInButton signInButton;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inject
        ButterKnife.bind(this);
        getTheApplication().getAppComponent().inject(this);

        // Presenter init
        presenter.attachView(this);

        presenter.loadWelcomeMsg();

        signInButton.setColorScheme(SignInButton.COLOR_DARK);
        signInButton.setSize(SignInButton.SIZE_WIDE);

        googleSignInSetup();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            onUserLogin();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from Google Sign-in Intent
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @OnClick(R.id.google_sign_in_button)
    public void onSignInButtonClick(){

        // Show loading screen
        presenter.loadSigningInScreen();

        Intent signInIntent =  Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void displayWelcomeMsg(String message) {
        welcomeTextView.setText(message);
    }

    @Override
    public void displaySigningInProgress(boolean status) {
        if (status) {
            signInButton.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }else{
            signInButton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    private void googleSignInSetup() {

        // Google Sign-in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this, "Connection Failed!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        // Sign-out off any Google account if logged in
                        Auth.GoogleSignInApi.signOut(googleApiClient);
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .build();
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            presenter.loadWelcomeMsg("LoggedIn as " + account.getDisplayName());
            firebaseAuthWithGoogle(account);
        }else{

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onUserLogin();
                        }else{
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onUserLogin() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
