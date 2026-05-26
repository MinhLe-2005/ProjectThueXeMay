<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <jsp:include page="/includes/customer/header.jsp" />

        <style>
            /* Custom Hero Layout */
            #hero {
                min-height: 100vh;
                display: flex;
                align-items: center;
                position: relative;
                background: url('https://images.unsplash.com/photo-1558981806-ec527fa84c39?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80') no-repeat center center/cover !important;
                padding: 140px 0 60px 0;
            }
            .hero-overlay {
                position: absolute;
                inset: 0;
                background: linear-gradient(135deg, rgba(15,12,10,0.78) 0%, rgba(20,18,16,0.55) 60%, rgba(20,18,16,0.30) 100%);
                z-index: 2;
            }
            #hero .container { position: relative; z-index: 3; }
            h2, p { color: white !important; }
        </style>
    </head>

    <body class="index-page">

        <jsp:include page="/includes/customer/navbar.jsp" />

        <main class="main">
            <section id="hero" class="hero section dark-background">
                <div class="hero-overlay"></div>
                <div class="container">
                    <div class="row justify-content-center text-center">
                        <div class="col-lg-8">
                            <h2 style="font-size: 3rem; font-weight: 700; margin-bottom: 20px;">Chào mừng đến với SmartRide</h2>

                        </div>
                    </div>
                </div>
            </section>
        </main>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>
        <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>

        <!-- Main JS File -->
        <script src="assets/js/main.js"></script>

    </body>

</html>
