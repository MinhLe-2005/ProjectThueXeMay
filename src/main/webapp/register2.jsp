<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/newlogo_transparent.png">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="https://cdn.jsdelivr.net/npm/remixicon@4.1.0/fonts/remixicon.css" rel="stylesheet" />
        <title>Đăng ký tài khoản - SmartRide</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
        <style>
            @import url("https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&family=Poppins:wght@400;500;600;700;800&display=swap");

            :root {
                --primary-color: #b59349;
                --primary-hover: #9c7c3b;
                --bg-light: #f8fafc;
                --card-bg: #ffffff;
                --border-color: rgba(0, 0, 0, 0.08);
                --text-dark: #0f172a;
                --text-muted: #64748b;
            }

            body {
                font-family: "Plus Jakarta Sans", sans-serif;
                background-color: var(--bg-light);
                color: var(--text-dark);
                min-height: 100vh;
                margin: 0;
                overflow-x: hidden;
            }

            /* Floating Card Layout */
            .login-wrapper {
                min-height: 100vh;
                width: 100%;
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 3.5rem 6rem;
                box-sizing: border-box;
                background-image: linear-gradient(to right, rgba(0, 0, 0, 0.45) 0%, rgba(0, 0, 0, 0.1) 45%, rgba(0, 0, 0, 0.75) 100%), url('images/login_bg.png');
                background-size: cover;
                background-position: center;
                gap: 4rem;
            }

            .login-form-side {
                width: 600px;
                max-width: 100%;
                background: rgba(255, 255, 255, 0.94);
                backdrop-filter: blur(16px);
                border-radius: 28px;
                padding: 3rem 3.5rem;
                border: 1px solid rgba(255, 255, 255, 0.5);
                box-shadow: 0 25px 55px rgba(0, 0, 0, 0.22), 0 4px 15px rgba(0, 0, 0, 0.05);
                z-index: 2;
                display: flex;
                flex-direction: column;
                justify-content: center;
            }

            .login-image-side {
                flex: 1;
                display: flex;
                justify-content: flex-end;
                align-items: flex-end;
                height: 100%;
                background: transparent !important;
                border: none !important;
            }

            @media (max-width: 1024px) {
                .login-wrapper {
                    padding: 2rem;
                    justify-content: center;
                    gap: 2rem;
                }
                .login-image-side {
                    display: none;
                }
                .login-form-side {
                    width: 550px;
                }
            }

            @media (max-width: 576px) {
                .login-wrapper {
                    padding: 1rem;
                }
                .login-form-side {
                    padding: 2.5rem 1.8rem;
                    border-radius: 20px;
                }
            }

            /* Brand Logo Styling */
            .brand-header {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-bottom: 2rem;
                text-decoration: none;
            }

            .brand-logo {
                height: 52px;
                width: auto;
                object-fit: contain;
                filter: drop-shadow(0 2px 5px rgba(0, 0, 0, 0.05));
            }

            .brand-name-text {
                font-family: 'Poppins', 'Plus Jakarta Sans', sans-serif;
                font-size: 26px;
                font-weight: 800;
                letter-spacing: -0.5px;
                background: linear-gradient(135deg, #b59349 0%, #d4a843 50%, #8c6f32 100%);
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                background-clip: text;
                line-height: 1;
            }


            /* Headings */
            .welcome-title {
                font-size: 28px;
                font-weight: 800;
                letter-spacing: -0.5px;
                margin-bottom: 0.5rem;
                color: #0f172a;
            }

            .welcome-subtitle {
                font-size: 14px;
                color: var(--text-muted);
                margin-bottom: 1.5rem;
            }

            /* Elegant Input Controls */
            .form-group-custom {
                margin-bottom: 1rem;
                position: relative;
            }

            .input-wrapper-custom {
                display: flex;
                align-items: center;
                background-color: #f8fafc !important;
                border: 1px solid rgba(0, 0, 0, 0.08) !important;
                border-radius: 12px;
                padding: 0.75rem 1.2rem;
                transition: all 0.4s cubic-bezier(0.165, 0.84, 0.44, 1);
                gap: 12px;
                height: 52px; /* Fix height for inputs/selects */
            }

            .input-wrapper-custom:focus-within {
                border-color: var(--primary-color) !important;
                box-shadow: 0 0 20px rgba(181, 147, 73, 0.12) !important;
                background-color: #ffffff !important;
                transform: translateY(-1px);
            }

            .input-wrapper-custom i.input-icon {
                color: #64748b;
                font-size: 18px;
                transition: color 0.3s;
            }

            .input-wrapper-custom:focus-within i.input-icon {
                color: var(--primary-color);
            }

            .input-wrapper-custom input,
            .input-wrapper-custom select {
                border: none;
                outline: none;
                background: transparent !important;
                color: #0f172a !important;
                font-size: 14px;
                width: 100%;
                font-weight: 500;
                appearance: none;
            }

            .input-wrapper-custom select {
                cursor: pointer;
            }

            /* Override autofill colors */
            input:-webkit-autofill,
            input:-webkit-autofill:hover,
            input:-webkit-autofill:focus,
            input:-webkit-autofill:active {
                -webkit-box-shadow: 0 0 0 30px #f8fafc inset !important;
                -webkit-text-fill-color: #0f172a !important;
                transition: background-color 5000s ease-in-out 0s;
            }

            .input-wrapper-custom input::placeholder {
                color: #94a3b8;
            }

            .password-eye-icon {
                cursor: pointer;
                color: var(--text-muted);
                font-size: 18px;
                transition: color 0.2s;
            }

            .password-eye-icon:hover {
                color: var(--primary-color);
            }

            /* Action Buttons */
            .btn-login-submit {
                background: linear-gradient(135deg, var(--primary-color) 0%, #a38241 100%) !important;
                color: #ffffff !important;
                font-weight: 700;
                font-size: 15px;
                letter-spacing: 1px;
                text-transform: uppercase;
                border: none;
                border-radius: 12px;
                padding: 0.95rem 2rem;
                width: 100%;
                box-shadow: 0 4px 15px rgba(181, 147, 73, 0.25);
                transition: all 0.4s cubic-bezier(0.165, 0.84, 0.44, 1);
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 8px;
                margin-top: 1.5rem;
            }

            .btn-login-submit:hover {
                transform: translateY(-2px);
                box-shadow: 0 8px 25px rgba(181, 147, 73, 0.4);
                filter: brightness(1.05);
            }

            /* Back to Home & Login */
            .register-prompt {
                text-align: center;
                margin-top: 1.5rem;
                font-size: 14px;
                color: #64748b;
            }

            .register-prompt a {
                color: var(--primary-color);
                text-decoration: none;
                font-weight: 600;
            }

            .register-prompt a:hover {
                text-decoration: underline;
            }

            /* Cinematic Overlay Text */
            .cinematic-content {
                position: relative;
                color: #ffffff;
                max-width: 600px;
                z-index: 2;
                animation: fadeInUp 1.2s ease-out;
                background: rgba(0, 0, 0, 0.55) !important;
                backdrop-filter: blur(12px);
                padding: 2.5rem;
                border-radius: 24px;
                border: 1px solid rgba(255, 255, 255, 0.08);
                box-shadow: 0 20px 45px rgba(0, 0, 0, 0.25);
            }

            .cinematic-title {
                font-size: 34px;
                font-weight: 800;
                line-height: 1.3;
                letter-spacing: -0.5px;
                margin-bottom: 1rem;
                background: linear-gradient(135deg, #ffffff 60%, #ffe3a8 100%);
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
            }

            .cinematic-desc {
                font-size: 14.5px;
                color: rgba(255, 255, 255, 0.9);
                line-height: 1.6;
                font-weight: 400;
                margin: 0;
            }

            /* Error messages styling */
            .error-message {
                color: #dc3545;
                font-size: 0.8rem;
                font-weight: 500;
                margin-top: 4px;
                display: none;
                padding-left: 5px;
            }

            .text-error {
                color: #dc3545;
                font-size: 0.8rem;
                font-weight: 500;
                margin-top: 4px;
                padding-left: 5px;
            }

            .password-strength {
                font-size: 0.8rem;
                font-weight: 600;
                display: none;
                margin-top: 4px;
                padding-left: 5px;
            }
            .red { color: #dc3545; }
            .orange { color: #fd7e14; }
            .green { color: #198754; }

            .alert-custom {
                background-color: #fef2f2 !important;
                border: 1px solid #fecaca !important;
                color: #991b1b !important;
                border-radius: 12px;
                font-size: 13px;
                display: flex;
                align-items: center;
                gap: 12px;
                margin-bottom: 1rem;
                padding: 0.8rem 1.2rem;
                font-weight: 500;
            }

            @keyframes fadeInUp {
                from {
                    opacity: 0;
                    transform: translateY(20px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            .two-columns {
                display: flex;
                gap: 15px;
            }
            .two-columns .form-group-custom {
                flex: 1;
            }
        </style>
    </head>
    <body>
        <div class="login-wrapper">
            <!-- Form Side -->
            <div class="login-form-side animate__animated animate__fadeInLeft">
                <a href="home" class="brand-header" style="display: flex; align-items: center; gap: 10px;">
                    <img src="${pageContext.request.contextPath}/images/newlogo_transparent.png" alt="SmartRide Logo" class="brand-logo" />
                    <span class="brand-name-text">SmartRide</span>
                </a>

                <h1 class="welcome-title">Tạo Tài Khoản</h1>
                <p class="welcome-subtitle">Tham gia cộng đồng SmartRide và bắt đầu chuyến hành trình của riêng bạn.</p>

                <c:if test="${not empty errorInput}">
                    <div class="alert-custom animate__animated animate__headShake" role="alert">
                        <i class="ri-error-warning-line" style="font-size: 20px;"></i>
                        <span>${errorInput}</span>
                    </div>
                </c:if>
                <div id="checkValid" class="alert-custom" style="display: none;">
                    <i class="ri-error-warning-line" style="font-size: 20px;"></i>
                    <span>Vui lòng kiểm tra lại thông tin không hợp lệ.</span>
                </div>

                <form action="register" method="post" onsubmit="return validateForm()">
                    <!-- First Name & Last Name -->
                    <div class="two-columns">
                        <div class="form-group-custom">
                            <div class="input-wrapper-custom">
                                <i class="ri-user-line input-icon"></i>
                                <input id="firstName" type="text" name="firstname" placeholder="Tên" required>
                            </div>
                        </div>
                        <div class="form-group-custom">
                            <div class="input-wrapper-custom">
                                <input id="lastName" type="text" name="lastname" placeholder="Họ" required style="padding-left: 5px;">
                            </div>
                        </div>
                    </div>

                    <!-- Gender & Username -->
                    <div class="two-columns">
                        <div class="form-group-custom">
                            <div class="input-wrapper-custom">
                                <i class="ri-men-line input-icon"></i>
                                <select id="gender" name="gender" required>
                                    <option disabled selected value="Giới tính">Giới tính</option>
                                    <option id="male" value="Nam">Nam</option>
                                    <option name="female" id="female" value="Nữ">Nữ</option>
                                    <option id="unknown" value="Không muốn tiết lộ">Không xác định</option>
                                </select>
                            </div>
                            <span class="error-message gender" id="gender-error">*Vui lòng chọn giới tính.</span>
                        </div>
                        <div class="form-group-custom">
                            <div class="input-wrapper-custom">
                                <i class="ri-user-settings-line input-icon"></i>
                                <input id="Username" type="text" name="username" placeholder="Tên đăng nhập" required>
                            </div>
                            <span class="error-message username" id="username-error"></span>
                        </div>
                    </div>

                    <!-- Email Address -->
                    <div class="form-group-custom">
                        <div class="input-wrapper-custom">
                            <i class="ri-mail-line input-icon"></i>
                            <input id="email" type="email" name="email" placeholder="Email" required>
                        </div>
                        <span id="email-error" class="error-message"></span>
                    </div>

                    <!-- Phone Number -->
                    <div class="form-group-custom">
                        <div class="input-wrapper-custom">
                            <i class="ri-phone-line input-icon"></i>
                            <input id="phoneNumber" type="tel" name="phone" placeholder="Số điện thoại" required>
                        </div>
                        <span class="error-message" id="phone-error">*Số điện thoại phải có 10 chữ số.</span>
                    </div>

                    <!-- Password & Confirm -->
                    <div class="two-columns">
                        <div class="form-group-custom">
                            <div class="input-wrapper-custom">
                                <i class="ri-lock-2-line input-icon"></i>
                                <input type="password" name="password" id="password" placeholder="Mật khẩu" maxlength="30" required />
                                <span id="password-eye-1" class="password-eye-icon"><i class="ri-eye-off-line"></i></span>
                            </div>
                        </div>
                        <div class="form-group-custom">
                            <div class="input-wrapper-custom">
                                <i class="ri-lock-password-line input-icon"></i>
                                <input type="password" name="passwordConfirmation" id="passwordConfirmation" placeholder="Xác nhận MK" maxlength="30" required />
                                <span id="password-eye-2" class="password-eye-icon"><i class="ri-eye-off-line"></i></span>
                            </div>
                        </div>
                    </div>
                    <span class="password-strength" id="password-strength"></span>

                    <!-- Submit Button -->
                    <button type="submit" name="register-submit" id="register-submit" class="btn-login-submit">
                        <span>Tạo Tài Khoản</span>
                        <i class="ri-user-add-line"></i>
                    </button>
                </form>

                <!-- Login Prompt -->
                <p class="register-prompt">
                    Đã có tài khoản? <a href="login.jsp">Đăng nhập</a>
                </p>
                <p class="register-prompt" style="margin-top: 5px;">
                    <a href="home"><i class="ri-home-4-line"></i> Về Trang Chủ</a>
                </p>
            </div>

            <!-- Image Side -->
            <div class="login-image-side">
                <div class="cinematic-content">
                    <h2 class="cinematic-title">Bắt Đầu Chuyến Đi Cùng SmartRide</h2>
                    <p class="cinematic-desc">Đăng ký ngay để trải nghiệm dịch vụ thuê xe máy tiện lợi, an toàn và mức giá tốt nhất, khám phá vẻ đẹp ở mọi nơi bạn đến.</p>
                </div>
            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", () => {
                const phoneInput = document.getElementById("phoneNumber");
                const phoneError = document.getElementById("phone-error");
                const passwordInput = document.getElementById("password");
                const passwordStrength = document.getElementById("password-strength");
                const genderSelect = document.getElementById("gender");
                const genderError = document.getElementById("gender-error");
                const checkValid = document.getElementById("checkValid");
                const confirmPass = document.getElementById("passwordConfirmation");
                const passwordBtn1 = document.getElementById("password-eye-1");
                const passwordBtn2 = document.getElementById("password-eye-2");

                const validatePhoneNumber = () => {
                    const phoneValue = phoneInput.value.trim();
                    const phoneRegex = /^0\d{9}$/;
                    if (phoneValue.length === 0) {
                        phoneError.style.display = "none";
                        return false;
                    } else if (!/^\d+$/.test(phoneValue)) {
                        phoneError.textContent = "*Số điện thoại chỉ được chứa các chữ số.";
                        phoneError.style.display = "block";
                        return false;
                    } else if (!phoneValue.startsWith('0')) {
                        phoneError.textContent = "*Số điện thoại phải bắt đầu bằng số 0.";
                        phoneError.style.display = "block";
                        return false;
                    } else if (phoneValue.length !== 10) {
                        phoneError.textContent = "*Số điện thoại phải có đúng 10 chữ số.";
                        phoneError.style.display = "block";
                        return false;
                    } else {
                        phoneError.style.display = "none";
                        return true;
                    }
                };

                const validateNewPassword = () => {
                    const newPasswordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
                    const password = passwordInput.value.trim();
                    if (newPasswordRegex.test(password)) {
                        if (password.length > 12) {
                            passwordStrength.textContent = "*Mật khẩu mạnh";
                            passwordStrength.className = "password-strength green";
                        } else if (password.length >= 10) {
                            passwordStrength.textContent = "*Mật khẩu vừa";
                            passwordStrength.className = "password-strength orange";
                        } else if (password.length > 8) {
                            passwordStrength.textContent = "*Mật khẩu yếu";
                            passwordStrength.className = "password-strength green"; /* Use green if valid */
                        } else {
                            passwordStrength.textContent = "";
                            passwordStrength.className = "password-strength";
                        }
                    } else {
                        passwordStrength.textContent = "*Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm ít nhất 1 chữ cái in hoa, 1 chữ số và 1 kí tự đặc biệt.";
                        passwordStrength.className = "password-strength red";
                    }
                    passwordStrength.style.display = password.length > 0 ? "block" : "none";
                };

                const togglePasswordVisibility = (inputId, iconId) => {
                    const passInput = document.getElementById(inputId);
                    const icon = document.getElementById(iconId).querySelector("i");
                    const isVisible = passInput.type === "text";
                    passInput.type = isVisible ? "password" : "text";
                    icon.classList.toggle("ri-eye-line", !isVisible);
                    icon.classList.toggle("ri-eye-off-line", isVisible);
                };

                const emailInput = document.getElementById("email");
                const emailText = document.getElementById("email-error");
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

                const validEmail = () => {
                    if (emailInput.value.trim() !== "") {
                        if (emailRegex.test(emailInput.value)) {
                            emailText.textContent = "";
                            emailText.className = "error-message";
                            emailText.style.display = "none";
                            return true;
                        } else {
                            emailText.textContent = "*Email chưa đúng format.";
                            emailText.className = "text-error red";
                            emailText.style.display = "block";
                            return false;
                        }
                    } else {
                        emailText.textContent = "*Không được để trống email.";
                        emailText.className = "text-error red";
                        emailText.style.display = "block";
                        return false;
                    }
                };

                const userInput = document.getElementById("Username");
                const userText = document.getElementById("username-error");
                const validUsername = () => {
                    if (userInput.value.trim() === "") {
                        userText.textContent = "*Không được để trống username.";
                        userText.className = "text-error red";
                        userText.style.display = "block";
                        return false;
                    } else {
                        userText.textContent = "";
                        userText.className = "error-message";
                        userText.style.display = "none";
                        return true;
                    }
                };

                const validateForm = () => {
                    const isPhoneValid = validatePhoneNumber();
                    const isEmailValid = validEmail();
                    const isUsernameValid = validUsername();

                    if (genderSelect.value === "Giới tính") {
                        genderError.style.display = "block";
                        return false;
                    } else {
                        genderError.style.display = "none";
                    }

                    return isPhoneValid && isUsernameValid && isEmailValid;
                };

                userInput.addEventListener("input", validUsername);
                emailInput.addEventListener("input", validEmail);
                phoneInput.addEventListener("input", validatePhoneNumber);
                passwordInput.addEventListener("input", validateNewPassword);
                passwordBtn1.addEventListener("click", () => togglePasswordVisibility("password", "password-eye-1"));
                passwordBtn2.addEventListener("click", () => togglePasswordVisibility("passwordConfirmation", "password-eye-2"));

                document.querySelector("form").addEventListener("submit", (event) => {
                    const isValid = validateForm();
                    if (!isValid) {
                        event.preventDefault();
                        checkValid.style.display = "flex";
                    } else {
                        checkValid.style.display = "none";
                    }
                });
            });
        </script>
    </body>
</html>
