<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/newlogo_transparent.png">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="https://cdn.jsdelivr.net/npm/remixicon@4.1.0/fonts/remixicon.css" rel="stylesheet" />
        <title>Đăng nhập - SmartRide</title>
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
                width: 480px;
                max-width: 100%;
                background: rgba(255, 255, 255, 0.94);
                backdrop-filter: blur(16px);
                border-radius: 28px;
                padding: 3.5rem;
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
                    width: 480px;
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
                margin-bottom: 2.5rem;
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
                font-size: 32px;
                font-weight: 800;
                letter-spacing: -0.5px;
                margin-bottom: 0.5rem;
                color: #0f172a;
            }

            .welcome-subtitle {
                font-size: 14px;
                color: var(--text-muted);
                margin-bottom: 2rem;
            }

            /* Elegant Input Controls */
            .form-group-custom {
                margin-bottom: 1.25rem;
                position: relative;
            }

            .input-wrapper-custom {
                display: flex;
                align-items: center;
                background-color: #f8fafc !important;
                border: 1px solid rgba(0, 0, 0, 0.08) !important;
                border-radius: 12px;
                padding: 0.85rem 1.3rem;
                transition: all 0.4s cubic-bezier(0.165, 0.84, 0.44, 1);
                gap: 12px;
            }

            .input-wrapper-custom:focus-within {
                border-color: var(--primary-color) !important;
                box-shadow: 0 0 20px rgba(181, 147, 73, 0.12) !important;
                background-color: #ffffff !important;
                transform: translateY(-1px);
            }

            .input-wrapper-custom i.input-icon {
                color: #64748b;
                font-size: 20px;
                transition: color 0.3s;
            }

            .input-wrapper-custom:focus-within i.input-icon {
                color: var(--primary-color);
            }

            .input-wrapper-custom input {
                border: none;
                outline: none;
                background: transparent !important;
                color: #0f172a !important;
                font-size: 15px;
                width: 100%;
                font-weight: 500;
            }

            /* Override autofill colors to preserve light theme aesthetics */
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

            .input-wrapper-custom input::placeholder {
                color: #555555;
            }

            #password-eye {
                cursor: pointer;
                color: var(--text-muted);
                font-size: 18px;
                transition: color 0.2s;
            }

            #password-eye:hover {
                color: var(--primary-color);
            }

            /* Custom Utilities */
            .remember-forgot {
                display: flex;
                align-items: center;
                justify-content: space-between;
                margin-top: 1rem;
                margin-bottom: 2rem;
                font-size: 13.5px;
            }

            .remember-me-wrap {
                display: flex;
                align-items: center;
                gap: 8px;
            }

            .remember-me-wrap input[type="checkbox"] {
                accent-color: var(--primary-color);
                width: 16px;
                height: 16px;
                cursor: pointer;
            }

            .remember-me-wrap label {
                cursor: pointer;
                color: #475569;
            }

            .forgot-link {
                color: #475569;
                text-decoration: none;
                font-weight: 500;
                transition: color 0.2s;
            }

            .forgot-link:hover {
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
            }

            .btn-login-submit:hover {
                transform: translateY(-2px);
                box-shadow: 0 8px 25px rgba(181, 147, 73, 0.4);
                filter: brightness(1.05);
            }

            .divider-or {
                display: flex;
                align-items: center;
                text-align: center;
                color: #94a3b8;
                margin-block: 1.8rem;
                font-size: 12px;
                text-transform: uppercase;
                font-weight: 700;
                letter-spacing: 1px;
            }

            .divider-or::before,
            .divider-or::after {
                content: '';
                flex: 1;
                border-bottom: 1px solid rgba(0, 0, 0, 0.05);
            }

            .divider-or:not(:empty)::before {
                margin-right: .75em;
            }

            .divider-or:not(:empty)::after {
                margin-left: .75em;
            }

            /* Google Button */
            .btn-google-login {
                background: #ffffff;
                border: 1px solid rgba(0, 0, 0, 0.08);
                border-radius: 12px;
                padding: 0.85rem 2rem;
                width: 100%;
                color: #334155;
                font-weight: 600;
                font-size: 14.5px;
                text-decoration: none;
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 10px;
                transition: all 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.02);
            }

            .btn-google-login:hover {
                background: #f8fafc;
                border-color: rgba(0, 0, 0, 0.15);
                color: #0f172a;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
            }

            .btn-google-login img {
                height: 20px;
                width: auto;
            }

            /* Register & Back */
            .register-prompt {
                text-align: center;
                margin-top: 2rem;
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

            .back-home-wrap {
                margin-top: 2.5rem;
                text-align: center;
            }

            .btn-back-home {
                display: inline-flex;
                align-items: center;
                gap: 8px;
                color: #475569;
                font-size: 13.5px;
                font-weight: 700;
                text-transform: uppercase;
                letter-spacing: 0.5px;
                text-decoration: none;
                transition: all 0.2s;
                border: 1px solid rgba(0, 0, 0, 0.08);
                padding: 8px 20px;
                border-radius: 30px;
                background: #ffffff;
            }

            .btn-back-home:hover {
                background: #f8fafc;
                color: #0f172a;
                border-color: rgba(0, 0, 0, 0.15);
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

            /* Custom Error Message */
            .alert-custom {
                background-color: #fef2f2 !important;
                border: 1px solid #fecaca !important;
                color: #991b1b !important;
                border-radius: 12px;
                font-size: 14px;
                display: flex;
                align-items: center;
                gap: 12px;
                margin-bottom: 1.5rem;
                padding: 0.9rem 1.3rem;
                font-weight: 500;
                box-shadow: 0 4px 12px rgba(220, 53, 69, 0.03);
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

                <h1 class="welcome-title">Chào Mừng Bạn Trở Lại</h1>
                <p class="welcome-subtitle">Đăng nhập tài khoản của bạn để khám phá hành trình mới.</p>

                <c:if test="${not empty error}">
                    <div class="alert-custom animate__animated animate__headShake" role="alert">
                        <i class="ri-error-warning-line" style="font-size: 20px;"></i>
                        <span>${error}</span>
                    </div>
                </c:if>

                <form id="loginForm" action="login" method="post">
                    <!-- Username -->
                    <div class="form-group-custom">
                        <div class="input-wrapper-custom animate__animated animate__pulse">
                            <i class="ri-user-3-line input-icon"></i>
                            <input value="${cookie.Username.value}" type="text" name="Username" id="Username" placeholder="Tên tài khoản" required />
                        </div>
                    </div>

                    <!-- Password -->
                    <div class="form-group-custom">
                        <div class="input-wrapper-custom animate__animated animate__pulse">
                            <i class="ri-lock-2-line input-icon"></i>
                            <input value="${cookie.Password.value}" type="password" name="Password" id="Password" placeholder="Mật khẩu" required />
                            <span id="password-eye"><i class="ri-eye-off-line"></i></span>
                        </div>
                    </div>

                    <!-- Utilities -->
                    <div class="remember-forgot">
                        <div class="remember-me-wrap">
                            <input ${cookie.rem != null ? "checked" : ""} type="checkbox" id="rememberMe" name="rem" value="ON" />
                            <label for="rememberMe">Nhớ mật khẩu</label>
                        </div>
                        <a href="forgotPassword.jsp" class="forgot-link">Quên mật khẩu?</a>
                    </div>

                    <!-- Submit Button -->
                    <button class="btn-login-submit" type="submit">
                        <span>Đăng Nhập</span>
                        <i class="ri-arrow-right-line"></i>
                    </button>
                </form>

                <div class="divider-or">Hoặc</div>

                <!-- Google -->
                <a class="btn-google-login" href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/SmartRide/login-google&response_type=code&client_id=427529527508-oc10hcpl7tv41q12bhjkk1lpvnupe80k.apps.googleusercontent.com&approval_prompt=force">
                    <img src="images/search.png" alt="Google" />
                    <span>Đăng nhập với Google</span>
                </a>

                <!-- Register Prompt -->
                <p class="register-prompt">
                    Nếu bạn chưa có tài khoản? <a href="register2.jsp">Tạo tài khoản</a>
                </p>

                <!-- Back to Home -->
                <div class="back-home-wrap">
                    <a href="home" class="btn-back-home">
                        <i class="ri-home-4-line"></i>
                        <span>Về Trang Chủ</span>
                    </a>
                </div>
            </div>

            <!-- Image Side -->
            <div class="login-image-side">
                <div class="cinematic-content">
                    <h2 class="cinematic-title">Khám Phá Hành Trình Tuyệt Vời Của Bạn</h2>
                    <p class="cinematic-desc">Trải nghiệm dịch vụ thuê xe máy cao cấp hàng đầu với bộ sưu tập các dòng xe chất lượng vượt trội, vận hành êm ái cùng sự phục vụ tận tâm nhất.</p>
                </div>
            </div>
        </div>

        <script>
            const passwordBtn = document.getElementById("password-eye");
            passwordBtn.addEventListener("click", () => {
                const passwordInput = document.getElementById("Password");
                const icon = passwordBtn.querySelector("i");
                const isVisible = icon.classList.contains("ri-eye-line");
                passwordInput.type = isVisible ? "password" : "text";
                icon.setAttribute("class", isVisible ? "ri-eye-off-line" : "ri-eye-line");
            });
        </script>
    </body>
</html>
