<%-- 
    Sidebar dùng chung cho trang profile khách hàng
    Dùng: <jsp:include page="/includes/customer/sidebarProfile.jsp">
              <jsp:param name="activePage" value="transaction|bookingHistory|profile|security"/>
          </jsp:include>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside class="max-w-62.5 ease-nav-brand z-990 absolute inset-y-0 my-4 ml-4 block w-full -translate-x-full flex-wrap items-center justify-between overflow-y-auto rounded-2xl border-0 p-0 antialiased shadow-none transition-transform duration-200 xl:left-0 xl:translate-x-0 xl:bg-transparent text-slate-500"
       id="sidenav-main">
    <hr class="h-px mt-0 bg-transparent bg-gradient-horizontal-dark">
    <div style="margin-top: 6rem" class="items-center block w-auto max-h-screen overflow-auto grow basis-full">
        <ul class="flex flex-col pl-0 mb-0">
            <li class="w-full">
                <div class="sidebar-heading">Quản lý tài khoản</div>
            </li>
            <li class="w-full">
                <a class="sidebar-item ${param.activePage == 'profile' ? 'active' : ''}" href="profileCustomer.jsp">
                    <div class="icon-box">
                        <i class="fas fa-user-circle text-xs"></i>
                    </div>
                    <span class="nav-text">Thông tin cá nhân</span>
                </a>
            </li>
            <li class="w-full">
                <a class="sidebar-item ${param.activePage == 'security' ? 'active' : ''}" href="settingsProfile.jsp">
                    <div class="icon-box">
                        <i class="fas fa-shield-alt text-xs"></i>
                    </div>
                    <span class="nav-text">Mật khẩu và bảo mật</span>
                </a>
            </li>
        </ul>
    </div>
</aside>

