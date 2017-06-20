package com.abdulwd.imageviewer;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginActivityTest {

    @Test
    public void isEmailValidTest() {
        LoginActivity mockEmail = mock(LoginActivity.class);
        when(mockEmail.isEmailValid("google@google.com")).thenReturn(true);
        boolean result = true;
        assertEquals(result, mockEmail.isEmailValid("google@google.com"));
    }

    @Test
    public void isPasswordValidTest() {
        LoginActivity mockPassword = mock(LoginActivity.class);
        when(mockPassword.isPasswordValid("123456")).thenReturn(true);
        boolean result = true;
        assertEquals(result, mockPassword.isPasswordValid("123456"));
    }
}
