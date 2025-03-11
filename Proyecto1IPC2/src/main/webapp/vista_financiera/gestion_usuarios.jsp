<%-- 
    Document   : gestion_usuarios
    Created on : 10 mar 2025, 21:26:27
    Author     : rafael-cayax
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.Usuario"%>
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
        <h1 class="text-center">Usuarios</h1>

        <div class="d-flex justify-content-center m-3">
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Agregar usuario
            </button>
        </div>
          
          <!-- Modal -->
          <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalLabel">Agregar Usuario</h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form method="POST" action="${pageContext.servletContext.contextPath}/controllers/financiero/usuario">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="nombre">
                            <label for="floatingInput">Nombre</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="contraseña" name="contraseña" placeholder="contraseña">
                            <label for="floatingInput">Contraseña</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="confirmacionContraseña" name="confirmacionContraseña" placeholder="contraseña">
                            <label for="floatingInput">Confirmar Contraseña</label>
                        </div>
                        <div class="form-floating mb-3">
                            <select class="form-select" id="rol" name="rol">
                                <c:forEach var="rol" items="${roles}">
                                    <option value="${rol}">${rol.descripcion}</option>
                                </c:forEach>
                            </select>
                            <label for="rol">Rol</label>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-success">Crear</button>
                        </div>
                    </form>
                </div>
              </div>
            </div>
          </div>

        <div class="d-flex justify-content-center m-3">
            <div class="card w-50 m-3 border-info">
                <div class="card-header">
                    Lista de Usuarios
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Rol</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${usuarios}" var="usuario">
                                <c:if test="${usuario.nombre != sessionScope.Usuario}">
                                    <tr>
                                        <td>${usuario.nombre}</td>
                                        <td>${usuario.rol.descripcion}</td>
                                        <td>
                                            <a href="${pageContext.servletContext.contextPath}/controllers/financiero/usuario_estado?nombre=${usuario.nombre}" class="btn ${usuario.activo ? 'btn-danger' : 'btn-success'}">
                                                ${usuario.activo ? 'Desactivar' : 'Activar'}
                                            </a>
                                            <a href="${pageContext.servletContext.contextPath}/controllers/usuario/editar?nombre=${usuario.nombre}" class="btn btn-primary">Actualizar</a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
