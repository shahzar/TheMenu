package com.shzlabs.themenu.injection.component;

import com.shzlabs.themenu.injection.module.AppModule;
import com.shzlabs.themenu.ui.categories.CategoriesFragment;
import com.shzlabs.themenu.ui.fooditems.ItemsFragment;
import com.shzlabs.themenu.ui.itemdetail.ItemDetailFragment;
import com.shzlabs.themenu.ui.login.LoginActivity;
import com.shzlabs.themenu.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shaz on 19/9/17.
 */

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(LoginActivity loginActivity);
    void inject(CategoriesFragment categoriesFragment);
    void inject(ItemsFragment itemsFragment);
    void inject(ItemDetailFragment itemDetailFragment);
}
