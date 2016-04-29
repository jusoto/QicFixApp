package com.qicfix.qicfixapp;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by stevefoo on 4/29/16.
 */
public class LoginRD extends ActivityInstrumentationTestCase2{
    private Solo solo;

    public LoginRD() {
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
        //Wait for activity: 'wrestt.client.common.WReSTT_welcome'
        solo.waitForActivity(com.qicfix.qicfixapp.MainActivity.class, 2000);
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.loginEmail));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.loginEmail), "user2@email.com");
        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.loginPassword));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.loginPassword));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.loginPassword), "user1");
        //Click on Login
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.loginButton));
    }
}
