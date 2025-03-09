<%-- 
    Document   : header_ventas
    Created on : 3 mar 2025, 0:35:54
    Author     : rafael-cayax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header class="sticky-top">
    <div class="px-3 py-2 bg-primary border-bottom">
        <div class="container">

            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-center">

                <a href="#" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto link-body-emphasis text-decoration-none">
                    <span class="fs-4">Area de Ventas</span>
                </a>
                <ul class="nav col-12 col-lg-auto my-2 justify-content-end my-md-0 text-small text-center">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/vista_ventas/entrada_nit.jsp" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/ventas/receipt.svg" width="24" height="24">
                            <br>
                            Realizar Compra
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/controllers/ventas/computadoras" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/ventas/shop.svg" width="24" height="24">
                            <br>
                            Sala de ventas
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/tipo_componente" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/ventas/graph-up-arrow.svg" width="24" height="24">
                            <br>
                            Ventas del dia
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/vista_ventas/devolucion.jsp" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/ventas/refund-svgrepo-com.svg" width="24" height="24">
                            <br>
                            Realizar devolucion
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-white" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="${pageContext.servletContext.contextPath}/svg/ventas/clipboard-data.svg" width="24" height="24">
                            <br>
                            Realizar consulta
                        </a>
                        <ul class="dropdown-menu bg-primary">
                          <li><a class="dropdown-item" href="#">Consulta de compras</a></li>
                          <li><a class="dropdown-item" href="#">Consulta de devoluciones</a></li>
                          <li><a class="dropdown-item" href="#">Consultar facturas</a></li>
                        </ul>
                    </li>
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
