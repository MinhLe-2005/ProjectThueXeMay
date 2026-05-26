/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smartride.constant;

/**
 *
 * @author LeQuangMinh
 */
public interface IConstant {
    String GOOGLE_CLIENT_ID = "YOUR_CLIENT_ID_HERE";

    String GOOGLE_CLIENT_SECRET = "YOUR_CLIENT_SECRET_HERE";

    String GOOGLE_REDIRECT_URI = "http://localhost:8080/SmartRide/login-google";

    String GOOGLE_GRANT_TYPE = "authorization_code";

    String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
}
