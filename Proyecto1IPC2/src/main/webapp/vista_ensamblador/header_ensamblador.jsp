<%-- 
    Document   : header_ensamblador
    Created on : 20 feb 2025, 1:25:20
    Author     : rafael-cayax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="sticky-top">
    <div class="px-3 py-2 bg-primary border-bottom">
        <div class="container">

            <div class="d-flex flex-wrap align-items-center justify-content-center">
                <a href="#" class="w-0 d-flex align-items-center mb-3 mb-lg-0 me-lg-auto link-body-emphasis text-decoration-none">
                    <span class="fs-4">Area de ensamblaje</span>
                </a>
                <ul class="nav col-12 col-lg-auto my-2 justify-content-end my-md-0 text-small text-center">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/componente" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/ensamblaje/cpu.svg" width="24" height="24">
                            <br>
                            Gestion componentes
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/tipo_componente" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/ensamblaje/device-ssd.svg" width="24" height="24">
                            <br>
                            Gestion categorias
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/tipo_computadora?area=ensamblaje" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/ensamblaje/motherboard.svg" width="24" height="24">
                            <br>
                            ensamblar computadora
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/computadora" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/ensamblaje/pc-display.svg" width="24" height="24">
                            <br>
                            computadoras ensambladas
                        </a>
                    </li>
                    <jsp:include page="/includes/cambio_de_area.jsp"/>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/controllers/usuario/sesion" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/box-arrow-in-right.svg" width="24" height="24">
                            <br>
                            Cerrar Sesion
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
