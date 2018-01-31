package com.shzlabs.themenu.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.shzlabs.themenu.TheApplication;

/**
 * Created by shaz on 18/9/17.
 */

public class BaseActivity extends AppCompatActivity {

    public TheApplication getTheApplication(){
        return ((TheApplication)getApplication());
    }
}
