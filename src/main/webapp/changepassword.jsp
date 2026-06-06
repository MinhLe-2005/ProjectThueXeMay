<%-- 
    Document   : changepassword
    Created on : 2 thg 6, 2024, 12:33:57
    Author     : MINH TUAN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thay đổi mật khẩu</title>
        <link rel="stylesheet" href="./fonts/themify-icons-font/themify-icons/themify-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/remixicon@4.1.0/fonts/remixicon.css" rel="stylesheet" />
        <style type="text/css">
            @import url('https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap');
            body {
                margin: 0;
                padding: 0;
                height: 100vh;
                background: url('images/smartride_premium_bg.png') no-repeat center center fixed;
                background-size: cover;
                font-family: 'Plus Jakarta Sans', sans-serif;
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: #0f172a;
            }
            .mainDiv {
                display: flex;
                align-items: center;
                justify-content: center;
                width: 100%;
                height: 100%;
                background-color: rgba(15, 23, 42, 0.4);
                backdrop-filter: blur(4px);
                -webkit-backdrop-filter: blur(4px);
            }
            .cardStyle {
                width: 480px;
                background: rgba(255, 255, 255, 0.95);
                backdrop-filter: blur(20px);
                -webkit-backdrop-filter: blur(20px);
                border: 1px solid rgba(255, 255, 255, 0.3);
                border-radius: 24px;
                padding: 48px;
                box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.5);
                transition: transform 0.3s ease, box-shadow 0.3s ease;
                box-sizing: border-box;
            }
            .cardStyle:hover {
                transform: translateY(-5px);
                box-shadow: 0 35px 60px -15px rgba(0, 0, 0, 0.6);
            }
            #signupLogo {
                max-height: 100px;
                margin: 0 auto 24px auto;
                display: block;
            }
            .formTitle {
                text-align: center;
                font-weight: 700;
                font-size: 26px;
                color: #0f172a;
                margin-bottom: 32px;
                margin-top: 0;
            }
            .inputDiv {
                margin-bottom: 24px;
                position: relative;
            }
            .inputLabel {
                font-size: 14px;
                font-weight: 600;
                color: #475569;
                margin-bottom: 8px;
                display: block;
            }
            .input-wrapper {
                position: relative;
                display: block;
                width: 100%;
            }
            .input-wrapper input {
                width: 100%;
                padding: 14px 16px;
                border: 1.5px solid #cbd5e1;
                border-radius: 12px;
                font-size: 15px;
                font-family: 'Plus Jakarta Sans', sans-serif;
                transition: all 0.3s ease;
                background: #f8fafc;
                box-sizing: border-box;
                color: #0f172a;
            }
            .input-wrapper input:focus {
                outline: none;
                border-color: #b59349;
                background: #ffffff;
                box-shadow: 0 0 0 4px rgba(181, 147, 73, 0.1);
            }
            .input-wrapper span {
                position: absolute;
                top: 50%;
                right: 16px;
                transform: translateY(-50%);
                cursor: pointer;
                font-size: 20px;
                color: #94a3b8;
                transition: color 0.3s ease;
            }
            .input-wrapper span:hover {
                color: #475569;
            }
            .submitButton {
                width: 100%;
                height: 52px;
                margin-top: 32px;
                display: flex;
                align-items: center;
                justify-content: center;
                color: #ffffff;
                background: #b59349;
                border: none;
                border-radius: 12px;
                font-size: 16px;
                font-weight: 700;
                letter-spacing: 0.5px;
                font-family: 'Plus Jakarta Sans', sans-serif;
                cursor: pointer;
                transition: all 0.3s ease;
                box-shadow: 0 4px 6px -1px rgba(181, 147, 73, 0.3);
            }
            .submitButton:hover {
                background: #9c7c3b;
                transform: translateY(-2px);
                box-shadow: 0 10px 15px -3px rgba(181, 147, 73, 0.4);
            }
            .text-error {
                display: block;
                font-size: 13px;
                margin-top: 6px;
                font-weight: 500;
            }
            .red {
                color: #ef4444;
            }
            .green {
                color: #10b981;
            }
            input[type="password"]::-ms-reveal,
            input[type="password"]::-ms-clear {
                display: none;
            }
        </style>
    </head>
    <body>
        <div class="mainDiv">
            <div class="cardStyle" id="changePasswordForm">
                <form action="changepassword" method="post" name="signupForm" id="signupForm">
                    <input type="hidden" value="${account.roleID}" name="roleId" id="roleID">
                    <img src="" id="signupLogo"/>
                    <h1 class="formTitle" style="font-weight: bold;">Thay đổi mật khẩu</h1>
                    <c:if test="${not empty requestScope.errorPass}">
                        <div class="inputDiv" style="text-align: justify;">
                            <p style="color: red;font-size: 14px;margin: 0;font-weight: 600;">${errorPass}</p>
                        </div>
                    </c:if>
                    <c:if test="${not empty requestScope.successChange}">
                        <div class="inputDiv" style="text-align: justify;">
                            <p style="color: green;font-size: 14px;margin: 0;">${successChange}</p>
                        </div>
                    </c:if>
                    <div class="inputDiv">
                        <label class="inputLabel" for="password">Mật khẩu hiện tại</label>
                        <div class="input-wrapper">
                            <input type="password" id="password" name="password" required>
                            <span id="password-eye-2" onclick="showPassword('password')">
                                <i class="ri-eye-off-line"></i>
                            </span>
                        </div>
                        <span class="text-error" id="password-text"></span>
                    </div>

                    <div class="inputDiv">
                        <label class="inputLabel" for="newPassword">Mật khẩu mới</label>
                        <div class="input-wrapper">
                            <input type="password" id="newPassword" name="newPassword" required>
                            <span id="password-eye-2" onclick="showPassword('newPassword')">
                                <i class="ri-eye-off-line"></i>
                            </span>
                        </div>
                        <span class="text-error" id="newpassword-text"></span>
                    </div>

                    <div class="inputDiv">
                        <label class="inputLabel" for="confirmPassword">Xác nhận mật khẩu mới</label>
                        <div class="input-wrapper">
                            <input type="password" id="confirmPassword" name="confirmPassword" required>
                            <span id="password-eye-2" onclick="showPassword('confirmPassword')">
                                <i class="ri-eye-off-line"></i>
                            </span>
                        </div>
                        <span class="text-error" id="confirmpassword-text"></span>
                    </div>
                    <div class="buttonWrapper" >
                        <button type="submit" id="submitButton" class="submitButton pure-button pure-button-primary">
                            <span>ĐỔI MẬT KHẨU</span>
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <div>
            <a href="settingsProfile.jsp" style="position: absolute; top: 32px; left: 32px; text-decoration: none; color: rgba(255, 255, 255, 0.8); transition: all 0.3s ease; display: flex; align-items: center; justify-content: center; width: 48px; height: 48px; background: rgba(0, 0, 0, 0.4); backdrop-filter: blur(8px); -webkit-backdrop-filter: blur(8px); border-radius: 50%; border: 1px solid rgba(255, 255, 255, 0.1);" onmouseover="this.style.background='rgba(0, 0, 0, 0.6)'; this.style.color='#ffffff';" onmouseout="this.style.background='rgba(0, 0, 0, 0.4)'; this.style.color='rgba(255, 255, 255, 0.8)';">
                <i style="font-size: 24px;" class="ri-arrow-left-line"></i>
            </a>
        </div>


        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var form = document.getElementById('changePasswordForm');
                setTimeout(function () {
                    form.classList.add('visible');
                }, 100);
            });
            document.getElementById('signupLogo').src = "images/newlogo_transparent.png";

            function showPassword(inputId) {
                const passInput = document.getElementById(inputId);
                const icon = passInput.nextElementSibling.querySelector('i');

                if (passInput.type === 'password') {
                    passInput.type = 'text';
                    icon.className = 'ri-eye-line';
                } else {
                    passInput.type = 'password';
                    icon.className = 'ri-eye-off-line';
                }
            }
            /////////////////////////////////////////
            const newPassword = document.getElementById("newPassword");
            const newPassText = document.getElementById("newpassword-text");
            const newpasswordRegex = /^(?=.*[A-Z])(?=.*\d).{8,}$/;

            const validnewPass = () => {
                if (newpasswordRegex.test(newPassword.value)) {
                    newPassText.textContent = "";
                    newPassText.className = "";
                } else {
                    newPassText.textContent = "Password phải chứa ít nhất 8 ký tự, bao gồm ít nhất 1 ký tự in hoa và 1 chữ số.";
                    newPassText.className = "text-error red";
                }
            };
            /////////////////////////////////////////
            const confirmPass = document.getElementById("confirmPassword");
            const confirmPassText = document.getElementById("confirmpassword-text");

            const validConfirmPass = () => {
                if (confirmPass.value !== newPassword.value) {
                    confirmPassText.textContent = "Mật khẩu mới và mật khẩu xác nhận không khớp";
                    confirmPassText.className = "text-error red";
                } else {
                    confirmPassText.textContent = "";
                    confirmPassText.className = "";
                }
            };

            newPassword.addEventListener("input", validnewPass);
            confirmPass.addEventListener("input", validConfirmPass);
        </script>
    </body>
</html>
