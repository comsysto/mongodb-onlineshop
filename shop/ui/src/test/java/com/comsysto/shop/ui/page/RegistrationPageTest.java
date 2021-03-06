package com.comsysto.shop.ui.page;

import com.comsysto.shop.ui.AbstractWicketTest;
import com.comsysto.shop.ui.page.user.RegistrationPage;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;

/**
 * @author zutherb
 */
public class RegistrationPageTest extends AbstractWicketTest {

    @Test
    public void testRender() {
        wicketTester.startPage(RegistrationPage.class);
        wicketTester.assertRenderedPage(RegistrationPage.class);
    }

    @Test
    public void testSaveValid() {
        wicketTester.startPage(RegistrationPage.class);
        FormTester formTester = wicketTester.newFormTester("registration");
        formTester.setValue("firstname", "Max");
        formTester.setValue("lastname", "Müller");
        formTester.setValue("username", "Max.M");
        formTester.setValue("email", "max.m@mail.com");
        formTester.setValue("password", "password123");
        formTester.setValue("repeatPassword", "password123");
        formTester.setValue("city", "München");
        formTester.setValue("street", "Lindwurmstr. 33");
        formTester.setValue("zip", "81369");

        wicketTester.hasNoInfoMessage();
    }

    @Test
    public void testSaveInvalid() {
        wicketTester.startPage(RegistrationPage.class);
        FormTester formTester = wicketTester.newFormTester("registration");
        formTester.setValue("firstname", "Max");
        formTester.setValue("lastname", "Müller");
        formTester.setValue("username", "Max.M");
        formTester.setValue("email", "max.m@mail.com");
        formTester.setValue("password", "passsword123");
        formTester.setValue("repeatPassword", "password123");
        formTester.setValue("city", "München");
        formTester.setValue("street", "Lindwurmstr. 33");
        formTester.setValue("zip", "81369");

        formTester.submit();

        //verify(userService, times(0));
        wicketTester.assertErrorMessages("The entered Passwords do not match");
    }


    @Override
    public void initMockData() {

    }
}
