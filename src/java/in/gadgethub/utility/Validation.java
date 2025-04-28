/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.utility;

import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author ashmi
 */
public class Validation {
    public static Boolean isValidEmail(String emailId) {
        EmailValidator validate = EmailValidator.getInstance();
        return validate.isValid(emailId);
    }

    public static boolean isValidMobile(String mobileNo) {
        if (mobileNo.length() != 10) {
            return false;
        }
        char[] mobArr = mobileNo.toCharArray();
        for (char ch : mobArr) {
            if (Character.isDigit(ch) == false) {
                return false;
            }
        }
        return true;
    }
}
