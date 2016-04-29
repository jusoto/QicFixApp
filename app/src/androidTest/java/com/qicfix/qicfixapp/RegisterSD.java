package com.qicfix.qicfixapp;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

/**
 * Created by stevefoo on 4/26/16.
 */
public class RegisterSD extends ActivityInstrumentationTestCase2{
    private Solo solo;

    public RegisterSD() {
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
        solo.clickOnText("REGISTRATION");
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.emailRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.emailRegForm), "James21@email.com");
        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.firstNameRegForm));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.firstNameRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.firstNameRegForm), "Fred");
        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.lastNameRegForm));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.lastNameRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.lastNameRegForm), "Bed");

        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.phoneRegForm));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.phoneRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.phoneRegForm), "3052978888");

        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.addressRegForm));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.addressRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.addressRegForm), "123 ABCDFG");

        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.cityRegForm));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.cityRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.cityRegForm), "Sweet Water");

        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.stateRegForm));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.stateRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.stateRegForm), "Florida");

        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.zipcodeRegForm));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.zipcodeRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.zipcodeRegForm), "33199");

        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.passwordRegForm));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.passwordRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.passwordRegForm), "abc123");

        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.confirmPassRegForm));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.confirmPassRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.confirmPassRegForm), "abc123");

        //Click on Empty Text View
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.companyNameRegForm));
        //Enter the text: 'admin'
        solo.clearEditText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.companyNameRegForm));
        solo.enterText((android.widget.EditText) solo.getView(com.qicfix.qicfixapp.R.id.companyNameRegForm), "Clarkester");
        //Click on Login
        solo.clickOnView(solo.getView(com.qicfix.qicfixapp.R.id.registrationButton));
        Timeout.setSmallTimeout(51368);
    }
}
