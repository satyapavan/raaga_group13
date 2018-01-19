
package com.raaga.testcases;



import org.junit.Assert;
import org.junit.Test;

import com.raaga.exceptions.InvalidUsernameException;
import com.raaga.exceptions.WrongPasswordException;
import com.raaga.manager.LoginManager;
import com.raaga.to.LoginTO;



public class TestCase1 {

	@Test

    public void testLoginValid() throws InvalidUsernameException,Exception{

    LoginTO loginTO=new LoginTO();

    loginTO.setUserId("Bala_10005");

    loginTO.setPassword("BalaRocks!");

    Assert.assertTrue(new LoginManager().validateLogin(loginTO)!=null);

}

     @Test(expected=InvalidUsernameException.class)

     public void testLogin() throws InvalidUsernameException,Exception{

     LoginTO loginTO=new LoginTO();

     loginTO.setUserId("neeharika_0010");

     loginTO.setPassword("niru@pama");

     new LoginManager().validateLogin(loginTO);

}

@Test(expected=WrongPasswordException.class)

     public void testPassword() throws InvalidUsernameException,Exception{

     LoginTO loginTO=new LoginTO();

     loginTO.setUserId("Padma_10004");

     loginTO.setPassword("xyz@a");

     new LoginManager().validateLogin(loginTO);

}



}




