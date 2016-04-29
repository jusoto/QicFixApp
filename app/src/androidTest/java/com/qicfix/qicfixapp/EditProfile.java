package com.qicfix.qicfixapp;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

/**
 * Created by stevefoo on 4/29/16.
 */
public class EditProfile extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public EditProfile() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testRun() {
        //Wait for activity to start
        solo.waitForActivity(com.qicfix.qicfixapp.MainActivity.class, 2000);
        //Clear login email field
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.loginEmail));
        //Enter the text: 'user2@email.com'
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.loginEmail), "user2@email.com");
        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.loginPassword));
        //Enter the text: 'user2'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.loginPassword));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.loginPassword), "user2");
        //Click on Login
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.loginButton));
        Timeout.setSmallTimeout(51368);
        assertTrue("com.qicfix.qicfixapp.Tower.TowerHome is not found!", solo.waitForActivity(com.qicfix.qicfixapp.Tower.TowerHome.class));
        //go to profile tab
        solo.clickOnText("PROFILE");
        Timeout.setSmallTimeout(51368);
        //hit the edit profile button
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.editProfileButton));
        Timeout.setSmallTimeout(51368);


    }
}
