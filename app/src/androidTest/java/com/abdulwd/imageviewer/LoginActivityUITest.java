package com.abdulwd.imageviewer;

import android.os.Build;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class LoginActivityUITest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void ensureSignInWork() {
        onView(withId(R.id.email))
                .perform(typeText("abdulwd97@gmail.com"), closeSoftKeyboard())
                .check(matches(isDisplayed()));
        onView(withId(R.id.password))
                .perform(typeText("abdul"), closeSoftKeyboard())
                .check(matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            assertTrue(mActivityRule.getActivity().isDestroyed());
        }

    }

    @Test
    public void testFailLogin() {
        onView(withId(R.id.email))
                .perform(typeText("abdulwd97@gmail.com"), closeSoftKeyboard())
                .check(matches(isDisplayed()));
        onView(withId(R.id.password))
                .perform(typeText("abdul123"), closeSoftKeyboard())
                .check(matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click())
                .check(matches(isDisplayed()));
    }
}