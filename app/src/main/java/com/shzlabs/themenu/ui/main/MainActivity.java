package com.shzlabs.themenu.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shzlabs.themenu.R;
import com.shzlabs.themenu.ui.base.BaseActivity;
import com.shzlabs.themenu.ui.categories.CategoriesFragment;
import com.shzlabs.themenu.ui.fooditems.ItemsFragment;
import com.shzlabs.themenu.ui.itemdetail.ItemDetailFragment;
import com.shzlabs.themenu.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, CategoriesFragment.OnCategoryItemSelectedListener,
ItemsFragment.OnFoodItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainPresenter presenter;

    @Inject
    FirebaseAuth firebaseAuth;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inject
        ButterKnife.bind(this);
        getTheApplication().getAppComponent().inject(this);

        setSupportActionBar(toolbar);

        // Check if user logged in
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        // Setup Nav Bar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (currentUser == null) {
            // User not logged in
            Log.d(TAG, "onCreate: User not loggedIn");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else{
            Log.d(TAG, "onCreate: User loggedIn!");

            // Presenter init
            presenter.attachView(this);

            // Load Categories -- mvp this later
            loadFragment(CategoriesFragment.newInstance(), false);

        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void onUserSignOut() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.categories_fragment, fragment);

        if(addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    @Override
    public void displayFoodItemFragment(String categoryId, String categoryLabel) {
        loadFragment(ItemsFragment.newInstance(categoryId, categoryLabel), true);
    }

    @Override
    public void displayFoodItemDetailsFragment(String itemId) {
        loadFragment(ItemDetailFragment.newInstance(itemId), true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            loadFragment(CategoriesFragment.newInstance(),false);
        }else if (id == R.id.nav_logout) {
            presenter.signOutUser();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void displayLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
