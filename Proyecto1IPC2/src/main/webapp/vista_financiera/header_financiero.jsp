<%-- 
    Document   : header_financiero
    Created on : 10 mar 2025, 4:54:31
    Author     : rafael-cayax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header class="sticky-top">
    <div class="px-3 py-2 bg-primary border-bottom">
        <div class="container">

            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-center">

                <a href="#" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto link-body-emphasis text-decoration-none">
                    <span class="fs-4">Area Financiera</span>
                </a>
                <ul class="nav col-12 col-lg-auto my-2 justify-content-end my-md-0 text-small text-center">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/vista_ventas/entrada_nit.jsp" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/financiera/file-earmark-arrow-up.svg" width="24" height="24">
                            <br>
                            Cargar Archivo
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/controllers/financiero/usuario" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/financiera/person-gear.svg" width="24" height="24">
                            <br>
                            Gestion Usuarios
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/vista_ventas/form_compras_del_dia.jsp" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/financiera/pc-display-horizontal.svg" width="24" height="24">
                            <br>
                            Gestion Tipos de Computadoras
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-white" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="${pageContext.servletContext.contextPath}/svg/ventas/clipboard-data.svg" width="24" height="24">
                            <br>
                            Reportes
                        </a>
                        <ul class="dropdown-menu bg-primary">
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/vista_financiera/form_reporte_compras.jsp">
                                Reporte de ventas</a></li>
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/vista_financiera/form_reporte_devoluciones.jsp">
                                Reporte de devoluciones</a></li>
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/vista_financiera/form_reporte_ganancias.jsp">
                                Reporte de ganancias</a></li>
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/vista_financiera/form_reporte_compras_usuario.jsp">
                                Reporte de usuario con mas ventas</a></li>
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/vista_ventas/form_factura.jsp">
                                Reporte de usuario con mas ganancias</a></li>
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/vista_financiera/form_computadora_mas_vendida.jsp">
                                Reporte de computadora mas vendida</a></li>
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/vista_financiera/form_computadora_menos_vendida.jsp">
                                Reporte de computadora menos vendida</a></li>
                        </ul>
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
