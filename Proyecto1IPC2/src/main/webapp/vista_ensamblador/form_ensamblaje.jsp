<%-- 
    Document   : form_ensamblaje
    Created on : 2 mar 2025, 0:10:43
    Author     : rafael-cayax
--%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente"%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora"%>
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
        
        <c:if test="${tipo_computadora != null}">
            <div class="d-flex justify-content-center m-5">
                <div class="card border-dark w-50">
                    <div class="card-body">
                       <p class="text-center">Nombre del tipo de computadora: ${tipo_computadora.nombre}</p>

                        <form class="mb-5" method="POST" action="${pageContext.servletContext.contextPath}/controllers/ensamblador/ensamblaje">
                            <h1 class="h3 mb-3 text-center">Ensamblar computadora</h1>
                            <input type="hidden" name="id" id="id" value="${tipo_computadora.idTipo}">
                            <div class="form-floating mb-3">
                                <input type="date" class="form-control" id="fecha" name="fecha" placeholder="fecha">
                                <label for="floatingInput">fecha de ensamblaje</label>
                            </div>
                            <div class="d-flex justify-content-center">
                                <a class="btn btn-secondary w-25 py-2 m-3" href="${pageContext.servletContext.contextPath}/controllers/ensamblador/tipo_computadora?area=ensamblaje">Cancelar</a>
                                <button class="btn btn-primary w-25 py-2 m-3" type="submit">Ensamblar</button>
                            </div>
                        </form>

                        <h5 class="card-title text-center">Indicaciones</h5>
                        <table class="table table-bordered text-center">
                            <thead>
                                <tr>
                                    <th>Nombre de Componente</th>
                                    <th>Cantidad Necesaria</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${tipo_computadora.indicaciones}" var="indicacion">
                                    <tr>
                                        <td>${indicacion.nombre}</td>
                                        <td>${indicacion.cantidad}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
