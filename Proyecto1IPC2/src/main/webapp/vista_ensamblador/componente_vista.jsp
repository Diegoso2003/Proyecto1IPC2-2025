<%-- 
    Document   : componente_vista
    Created on : 24 feb 2025, 23:51:04
    Author     : rafael-cayax
--%>

<%@page import="com.mycompany.proyecto1ipc2.dtos.Componente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/vista_ensamblador/header_ensamblador.jsp"/>

        <jsp:include page="/includes/informacion.jsp"/>

        <c:if test="${componente != null}">
            <div class="d-flex justify-content-center m-5">
                <div class="card w-50">
                    <div class="card-body">
                        <form class="mb-5" method="POST" action="${pageContext.servletContext.contextPath}/controllers/ensamblador/componente">
                            <h1 class="h3 mb-3 text-center">Actualizar Componente</h1>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" value="${componente.nombre}" id="nombre" name="nombre" placeholder="nombre">
                                <label for="floatingInput">Nombre</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" value="${componente.precio}" id="precio" name="precio" placeholder="precio">
                                <label for="floatingInput">Precio</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="existencia" value="${componente.cantidad}" name="existencia" placeholder="existencia">
                                <label for="floatingInput">Existencia</label>
                            </div>
                            <input type="hidden" class="form-control" value="${componente.id}" id="id" name="id">
                            <div class="d-flex justify-content-center">
                                <button class="btn btn-primary w-25 py-2" type="submit">Actualizar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
