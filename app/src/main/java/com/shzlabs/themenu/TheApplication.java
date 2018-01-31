package com.shzlabs.themenu;

import android.app.Application;

import com.shzlabs.themenu.injection.component.AppComponent;
import com.shzlabs.themenu.injection.component.DaggerAppComponent;
import com.shzlabs.themenu.injection.module.AppModule;

/**
 * Created by shaz on 19/9/17.
 */

public class TheApplication extends Application{

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
