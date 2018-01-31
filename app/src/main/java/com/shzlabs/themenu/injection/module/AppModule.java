package com.shzlabs.themenu.injection.module;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shaz on 19/9/17.
 */

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }

    @Provides
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    FirebaseDatabase provideFirebaseDatabase() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.setPersistenceEnabled(true);
        return db;
    }
}
