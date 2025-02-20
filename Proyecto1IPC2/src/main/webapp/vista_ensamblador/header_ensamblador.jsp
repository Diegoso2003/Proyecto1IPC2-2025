<%-- 
    Document   : header_ensamblador
    Created on : 20 feb 2025, 1:25:20
    Author     : rafael-cayax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>
    <div class="px-3 py-2 bg-primary border-bottom">
        <div class="container">

            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-center">

                <a href="#" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto link-body-emphasis text-decoration-none">
                    <span class="fs-4">La computadora Feliz</span>
                </a>
                <ul class="nav col-12 col-lg-auto my-2 justify-content-end my-md-0 text-small text-center">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/mvc/ServletConsultarCategorias" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/file-earmark-arrow-up.svg" width="24" height="24">
                            <br>
                            Subir Revista
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/mvc/ServletConsultarRevistas" class="nav-link text-white">
                            <img src="${pageContext.servletContext.contextPath}/svg/card-list.svg" width="24" height="24">
                            <br>
                            Administrar Revistas Subidas
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/vista_editor/reportes.jsp" class="nav-link text-white">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="black" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                <rect x="4" y="2" width="16" height="20" rx="2" ry="2"></rect>
                                <line x1="8" y1="6" x2="16" y2="6"></line>
                                <line x1="8" y1="10" x2="16" y2="10"></line>
                                <line x1="8" y1="14" x2="12" y2="14"></line>
                            </svg>
                            <br>
                            Ver Reportes
                        </a>
                    </li>
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/vista_editor/perfil.jsp" class="nav-link text-white">
                                <img src="${pageContext.servletContext.contextPath}/svg/person-circle.svg" width="24" height="24">
                                    <br>
                                        Editar Perfil
                                        </a>
                                        </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/mvc/ServletLogout" class="nav-link text-white">
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
