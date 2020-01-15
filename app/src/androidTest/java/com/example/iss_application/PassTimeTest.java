package com.example.iss_application;


import android.os.SystemClock;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.iss_application.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class PassTimeTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> main = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void testLocationDisplay() {
        onView(ViewMatchers.withId(R.id.latitude_textview)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.longitude_textview)).check(matches(isDisplayed()));
    }

    @Test
    public void testLaunch(){
        onView(ViewMatchers.withId(R.id.passtime_recyclerview))
                .check(matches(isDisplayed()));
    }


    @Test
    public void testRecyclerRefresh(){
        SystemClock.sleep(3000);
        onView(ViewMatchers.withId(R.id.swipe_recyclerview)).perform(swipeDown());
        SystemClock.sleep(3000);
        onView(ViewMatchers.withId(R.id.swipe_recyclerview)).perform(swipeDown());
        SystemClock.sleep(3000);
    }

}
