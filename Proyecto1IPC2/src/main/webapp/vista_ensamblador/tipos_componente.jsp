<%-- 
    Document   : tipos_componente
    Created on : 26 feb 2025, 17:37:55
    Author     : rafael-cayax
--%>
<%@page import="java.util.List"%>
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

        <h1 class="text-center">Lista de tipos de componentes</h1>

        <div class="d-flex justify-content-center m-3">
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Agregar tipo de componente
            </button>
        </div>
          
          <!-- Modal -->
          <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form method="POST" action="${pageContext.servletContext.contextPath}/controllers/ensamblador/tipo_componente">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="nombre">
                            <label for="floatingInput">Nombre del componente</label>
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

        <c:forEach items="${tipos}" var="tipo">
            <div class="d-flex justify-content-center m-3">
                <div class="card w-50 m-3 ${componente.cantidad < 10 ? 'alert alert-danger' : 'border-success'}">
                    <div class="card-body">
                        <p>Nombre del tipo componente: ${tipo.nombre}</p>
                        <p>ID del tipo de componente: ${tipo.id}</p>
                        <div class="d-flex justify-content-center">
                            <button type="button" class="btn btn-danger w-25 py-2 m-3" data-bs-toggle="modal" data-bs-target="#exampleModal${tipo.id}">
                                Eliminar
                            </button>
                            <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/gestion_tipo_componente?id=${tipo.id}" class="btn btn-primary w-25 py-2 m-3">Actualizar</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="exampleModal${tipo.id}" tabindex="-1" aria-labelledby="exampleModal${tipo.id}Label" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5" id="exampleModal${tipo.id}Label">Confirmacion</h1>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <p>¿Esta seguro de eleminar el tipo de componente? con: </p>
                      <p>Nombre: ${tipo.nombre}</p>
                        <p>ID: ${tipo.id}</p>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                      <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/delete_tipo?id=${tipo.id}" class="btn btn-warning">Confirmar</a>
                    </div>
                  </div>
                </div>
              </div>
        </c:forEach>
    </body>
</html>
