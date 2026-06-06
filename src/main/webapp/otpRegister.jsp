<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/newlogo_transparent.png">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="https://cdn.jsdelivr.net/npm/remixicon@4.1.0/fonts/remixicon.css" rel="stylesheet" />
        <title>Xác minh OTP - SmartRide</title>
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
                width: 520px;
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

            .btn-login-submit:hover:not(:disabled) {
                transform: translateY(-2px);
                box-shadow: 0 8px 25px rgba(181, 147, 73, 0.4);
                filter: brightness(1.05);
            }

            .btn-login-submit:disabled {
                background: #cbd5e1 !important;
                box-shadow: none;
                cursor: not-allowed;
                transform: none;
                filter: none;
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

            /* OTP Verification CSS */
            .otp_input {
                display: flex;
                gap: 12px;
                justify-content: space-between;
                margin-top: 10px;
                margin-bottom: 1.5rem;
            }
            .otp_input input {
                width: 50px;
                height: 60px;
                text-align: center;
                font-size: 24px;
                font-weight: 700;
                border-radius: 12px;
                border: 1px solid rgba(0, 0, 0, 0.1);
                background-color: #f8fafc;
                color: var(--text-dark);
                transition: all 0.3s ease;
                box-shadow: inset 0 2px 4px rgba(0,0,0,0.02);
            }
            .otp_input input:focus {
                border-color: var(--primary-color);
                box-shadow: 0 0 15px rgba(181, 147, 73, 0.15);
                background-color: #ffffff;
                outline: none;
                transform: translateY(-2px);
            }
            .otp_input input:disabled {
                background-color: #e2e8f0;
                cursor: not-allowed;
            }

            @media (max-width: 576px) {
                .otp_input {
                    gap: 8px;
                }
                .otp_input input {
                    width: 42px;
                    height: 52px;
                    font-size: 20px;
                }
            }

            .error-message {
                color: #dc3545;
                font-size: 14px;
                font-weight: 500;
                margin-bottom: 1rem;
                text-align: center;
            }

            .resend-link {
                text-align: center;
                margin-top: 1.5rem;
                font-size: 14px;
                color: var(--text-muted);
            }
            .resend-link a {
                color: var(--primary-color);
                font-weight: 600;
                text-decoration: none;
                transition: all 0.2s;
            }
            .resend-link a:hover {
                text-decoration: underline;
                color: var(--primary-hover);
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
                <a href="home" class="brand-header">
                    <img src="${pageContext.request.contextPath}/images/newlogo_transparent.png" alt="SmartRide Logo" class="brand-logo" />
                </a>

                <h1 class="welcome-title">Xác Minh Email</h1>
                <p class="welcome-subtitle">Chúng tôi đã gửi một mã OTP gồm 6 chữ số đến email của bạn. Vui lòng kiểm tra và nhập mã bên dưới.</p>

                <form action="otp" method="post">
                    <div class="otp_input">
                        <input type="text" maxlength="1" name="input1" autofocus>
                        <input type="text" maxlength="1" name="input2" disabled>
                        <input type="text" maxlength="1" name="input3" disabled>
                        <input type="text" maxlength="1" name="input4" disabled>
                        <input type="text" maxlength="1" name="input5" disabled>
                        <input type="text" maxlength="1" name="input6" disabled>
                    </div>

                    <% String msg = (String)request.getAttribute("message");
                       if(msg != null && !msg.isEmpty()) { %>
                        <div class="error-message animate__animated animate__headShake">
                            <i class="ri-error-warning-line"></i> <%=msg%>
                        </div>
                    <% } %>

                    <button type="submit" class="btn-login-submit verification" disabled>
                        <span>Xác nhận mã OTP</span>
                        <i class="ri-check-double-line"></i>
                    </button>
                </form>

                <div class="resend-link">
                    Không nhận được mã? <a href="reOtp">Gửi lại ngay</a>
                </div>
            </div>

            <!-- Image Side -->
            <div class="login-image-side">
                <div class="cinematic-content">
                    <h2 class="cinematic-title">Bảo Mật Tuyệt Đối</h2>
                    <p class="cinematic-desc">Mã OTP giúp bảo vệ tài khoản của bạn khỏi những truy cập trái phép. SmartRide cam kết mang đến trải nghiệm thuê xe an toàn nhất cho mọi hành trình.</p>
                </div>
            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", () => {
                const inputs = document.querySelectorAll('.otp_input input');
                const button = document.querySelector('.verification');

                inputs.forEach((input, index) => {
                    input.addEventListener('keyup', (e) => {
                        const currentElement = e.target;
                        const nextElement = currentElement.nextElementSibling;
                        const prevElement = currentElement.previousElementSibling;

                        // Handle backspace
                        if (e.key === 'Backspace') {
                            inputs.forEach((inpt, idx) => {
                                if (idx > index) {
                                    inpt.value = '';
                                    inpt.setAttribute('disabled', true);
                                }
                            });

                            if (prevElement) {
                                prevElement.focus();
                            }
                            button.setAttribute('disabled', true);
                            return;
                        }

                        // Validate numeric input
                        const reg = /^[0-9]+$/;
                        if (!reg.test(currentElement.value)) {
                            currentElement.value = currentElement.value.replace(/\D/g, '');
                            return;
                        }

                        // Move to next input
                        if (currentElement.value && nextElement) {
                            nextElement.removeAttribute('disabled');
                            nextElement.focus();
                        }

                        // Check if all filled
                        const isAllDigitsEntered = Array.from(inputs).every(input => input.value !== '');
                        if (isAllDigitsEntered) {
                            button.removeAttribute('disabled');
                        } else {
                            button.setAttribute('disabled', true);
                        }
                    });

                    // Handle pasting 6 digits
                    input.addEventListener('paste', (e) => {
                        e.preventDefault();
                        const pastedData = e.clipboardData.getData('text').trim();
                        const reg = /^[0-9]{6}$/;

                        if (reg.test(pastedData)) {
                            inputs.forEach((inpt, idx) => {
                                inpt.value = pastedData[idx];
                                inpt.removeAttribute('disabled');
                            });
                            inputs[5].focus();
                            button.removeAttribute('disabled');
                        }
                    });
                });
            });
        </script>
    </body>
</html>
