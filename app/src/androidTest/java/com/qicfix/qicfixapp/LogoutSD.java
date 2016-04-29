package com.qicfix.qicfixapp;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

/**
 * Created by stevefoo on 4/26/16.
 */
public class LogoutSD extends ActivityInstrumentationTestCase2 {
    private Solo solo;

    public LogoutSD() {
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
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.loginPassword), "user2");
        //Click on Login
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.loginButton));
        Timeout.setSmallTimeout(51368);
        solo.goBack();
        solo.clickOnButton("OK");
    }
}
