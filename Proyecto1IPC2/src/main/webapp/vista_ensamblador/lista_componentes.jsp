<%-- 
    Document   : lista_componentes
    Created on : 24 feb 2025, 1:27:17
    Author     : rafael-cayax
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.ensamblador.Componente"%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente"%>
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

        <h1 class="text-center">Lista de componentes</h1> 
        
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
                  <h1 class="modal-title fs-5" id="exampleModalLabel">Crear Componente</h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form class="mb-5" method="POST" action="${pageContext.servletContext.contextPath}/controllers/ensamblador/componente">
                        <div class="form-floating mb-3">
                            <select class="form-select" id="validationCustom04" name="tipo">
                                <c:forEach items="${tipos}" var="tipo">
                                    <option value="${tipo.id}">${tipo.nombre}</option>
                                </c:forEach>
                            </select>
                            <label for="floatingInput">Categoria</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" id="precio" name="precio" placeholder="precio" step="0.01">
                            <label for="floatingInput">Precio</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" id="existencia" name="existencia" placeholder="existencia">
                            <label for="floatingInput">Existencia</label>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            <button class="btn btn-primary w-25 py-2" type="submit">Crear Componente</button>
                        </div>
                    </form>
                </div>
              </div>
            </div>
          </div>

          <div class="d-flex justify-content-center m-3">
            <form method="GET" action="${pageContext.servletContext.contextPath}/controllers/ensamblador/orden_componente">
              <button type="submit" name="orden" value="DESC" class="btn btn-primary m-2">Obtener de mayor a menor</button>
              <button type="submit" name="orden" value="ASC" class="btn btn-secondary m-2">Obtener de menor a mayor</button>
            </form>
          </div>

        <c:forEach items="${componentes}" var="componente">
            <div class="d-flex justify-content-center m-3">
                <div class="card w-50 m-3 text-bg-${componente.cantidad == 0 ? 'danger' : (componente.cantidad < 10 ? 'warning' : 'success')}">
                    <div class="card-body ">
                        <p>Nombre del componente: ${componente.tipo.nombre}</p>
                        <p>ID del componente: ${componente.id}</p>
                        <p>Precio del componente: ${componente.precio}</p>
                        <p>Existencia del componente: ${componente.cantidad}</p>
                        <div class="d-flex justify-content-center">
                            <button type="button" class="btn btn-danger w-25 py-2 m-3" data-bs-toggle="modal" data-bs-target="#exampleModal${componente.id}">
                                Eliminar
                            </button>
                            <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/gestion_componente?id=${componente.id}" class="btn btn-primary w-25 py-2 m-3">Actualizar</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="exampleModal${componente.id}" tabindex="-1" aria-labelledby="exampleModal${componente.id}Label" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5" id="exampleModal${componente.id}Label">Confirmacion</h1>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <p>¿Esta seguro de eleminar el componente? con: </p>
                      <p>Nombre: ${componente.tipo.nombre}</p>
                        <p>ID: ${componente.id}</p>
                        <p>Precio: ${componente.precio}</p>
                        <p>Existencia: ${componente.cantidad}</p>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                      <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/delete_componente?id=${componente.id}" class="btn btn-warning">Confirmar</a>
                    </div>
                  </div>
                </div>
              </div>
        </c:forEach>
    </body>
</html>
