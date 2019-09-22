package com.drople;

import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaceOrderActivityTest {
//
    @Rule
    public ActivityTestRule<PlaceOrderActivity> activityTestRule = new ActivityTestRule<PlaceOrderActivity>(PlaceOrderActivity.class);

    private PlaceOrderActivity placeOrderActivity = null;

    @Before
    public void setUp() throws Exception {
        placeOrderActivity = activityTestRule.getActivity();
    }


    @Test
    public void test_launch()
    {
        TextView selectaddress = placeOrderActivity.findViewById(R.id.heading);
        assertNotNull(selectaddress);
    }

    @After
    public void tearDown() throws Exception {
        placeOrderActivity = null;
    }

}