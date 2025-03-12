<%-- 
    Document   : gestion_compu
    Created on : 12 mar 2025, 4:37:18
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
        <jsp:include page="/vista_financiera/header_financiero.jsp"/>

        <jsp:include page="/includes/informacion.jsp"/>
        
        <c:if test="${computadora != null}">
            <div class="d-flex justify-content-center m-5">
                <div class="card border-dark w-50">
                    <div class="card-body">
                       <p class="text-center">Nombre del tipo de computadora: ${computadora.nombre}</p>

                        <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/financiero/gestion_compu" method="post">
                            <input type="hidden" name="id" value="${computadora.idTipo}">
                            <div class="form-floating mb-3">
                                <select class="form-control" id="componente" name="componenteId">
                                    <c:forEach items="${componentes}" var="componente">
                                        <option value="${componente.id}">${componente.nombre}</option>
                                    </c:forEach>
                                </select>
                                <label for="componente">Componente:</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" id="cantidad" name="cantidad" required>
                                <label for="cantidad">Cantidad Necesaria:</label>
                            </div>
                            <div class="d-flex justify-content-center">
                                <button type="submit" class="btn btn-primary w-25 py-2 m-3">Agregar Indicacion</button>
                            </div>
                        </form>

                        <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/financiero/actualizar_computadora" method="post">
                            <input type="hidden" name="id" value="${computadora.idTipo}">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="nombre" name="nombre" value="${computadora.nombre}" required>
                                <label for="nombre">Nombre del tipo de computadora:</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="number" step="0.01" class="form-control" id="precio" name="precio" value="${computadora.precio}" required>
                                <label for="precio">Precio:</label>
                            </div>
                            <div class="d-flex justify-content-center">
                                <button type="submit" class="btn btn-primary w-25 py-2 m-3">Actualizar</button>
                            </div>
                        </form>

                        <h5 class="card-title text-center">Indicaciones</h5>
                        <table class="table table-bordered text-center">
                            <thead>
                                <tr>
                                    <th>Nombre de Componente</th>
                                    <th>Cantidad Necesaria</th>
                                    <th>Eliminar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${computadora.indicaciones}" var="indicacion">
                                    <tr>
                                        <td>${indicacion.nombre}</td>
                                        <td>
                                            <form action="${pageContext.servletContext.contextPath}/controllers/financiero/gestion_indicacion" method="post">
                                                <input type="hidden" name="componenteId" value="${indicacion.id}">
                                                <input type="hidden" name="id" value="${computadora.idTipo}">
                                                <input type="number" name="cantidad" value="${indicacion.cantidad}" class="form-control" required>
                                                <button type="submit" class="btn btn-primary mt-2">Actualizar</button>
                                            </form>
                                        </td>
                                        <td>
                                            <a href="${pageContext.servletContext.contextPath}/controllers/financiero/gestion_indicacion?componenteId=${indicacion.id}&id=${computadora.idTipo}" class="btn btn-danger">Eliminar</a>
                                        </td>
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
