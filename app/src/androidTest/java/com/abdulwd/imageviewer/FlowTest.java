package com.abdulwd.imageviewer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class FlowTest {

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule = new
            ActivityTestRule<>(MainActivity.class, true, false);

    Intent intent;
    SharedPreferences.Editor preferencesEditor;

    @Before
    public void setUp() {
        intent = new Intent();
        Context context = getInstrumentation().getTargetContext();

        // create a SharedPreferences editor
        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
    }

    @Test
    public void ensureFlow() throws InterruptedException {
        onView(withId(R.id.email_sign_in_button)).perform(click());

        Thread.sleep(1500);

        onView(withId(R.id.email))
                .perform(typeText("abdulwd97@gmail.com"), closeSoftKeyboard())
                .check(matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click())
                .check(matches(isDisplayed()));

        Thread.sleep(2500);

        onView(withId(R.id.email))
                .perform(clearText(), typeText("abdul"), closeSoftKeyboard())
                .check(matches(isDisplayed()));
        onView(withId(R.id.password))
                .perform(clearText(), typeText("abdul"), closeSoftKeyboard())
                .check(matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click())
                .check(matches(isDisplayed()));

        Thread.sleep(2500);

        onView(withId(R.id.email))
                .perform(clearText(), typeText("abdulwd97@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(clearText(), typeText("abd"), closeSoftKeyboard())
                .check(matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click())
                .check(matches(isDisplayed()));

        Thread.sleep(2500);

        onView(withId(R.id.password))
                .perform(clearText(), typeText("abdul"), closeSoftKeyboard())
                .check(matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click());

        preferencesEditor.putBoolean("pref_previously_started", Boolean.TRUE);
        preferencesEditor.commit();
        mMainActivityRule.launchActivity(intent);

        Thread.sleep(2000);
        onView(withId(R.id.recycler_view)).perform(scrollToPosition(8));
        Thread.sleep(2000);
        onView(withId(R.id.recycler_view))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(
                                8,
                                new ViewAction() {
                                    @Override
                                    public Matcher<View> getConstraints() {
                                        return null;
                                    }

                                    @Override
                                    public String getDescription() {
                                        return "Click on specific button";
                                    }

                                    @Override
                                    public void perform(UiController uiController, View view) {
                                        View button = view.findViewById(R.id.image_view);
                                        // Maybe check for null
                                        button.performClick();
                                    }
                                })
                );
        Thread.sleep(1000);
    }
}