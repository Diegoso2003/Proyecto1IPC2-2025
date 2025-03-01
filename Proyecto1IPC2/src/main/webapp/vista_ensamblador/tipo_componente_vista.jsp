<%-- 
    Document   : tipo_componente_vista
    Created on : 27 feb 2025, 21:18:24
    Author     : rafael-cayax
--%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.TipoComponente"%>
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
        <c:if test="${!empty tipo}">
            <div class="d-flex justify-content-center m-5">
                <div class="card w-50">
                    <div class="card-body">
                        <form class="mb-5" method="POST" action="${pageContext.servletContext.contextPath}/controllers/ensamblador/gestion_tipo_componente">
                            <h1 class="h3 mb-3 text-center">Actualizar tipo de componente</h1>
                            <input type="hidden" name="id" value="${tipo.id}">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="nombre" name="nombre" value="${tipo.nombre}" placeholder="nombre">
                                <label for="floatingInput">nombre</label>
                            </div>
                            <div class="d-flex justify-content-center">
                                <a class="btn btn-secondary w-25 py-2 m-3" href="${pageContext.servletContext.contextPath}/controllers/ensamblador/tipo_componente">Cancelar</a>
                                <button class="btn btn-primary w-25 py-2 m-3" type="submit">Actualizar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
