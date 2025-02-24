<%-- 
    Document   : crear_componente.jsp
    Created on : 20 feb 2025, 1:26:06
    Author     : rafael-cayax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        
        <div class="d-flex justify-content-center m-5">
            <div class="card w-50">
                <div class="card-body">
                    <form class="mb-5" method="POST" action="${pageContext.servletContext.contextPath}/controllers/ensamblador/crear_componente">
                        <h1 class="h3 mb-3 text-center">Crear Componente</h1>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="nombre">
                            <label for="floatingInput">Nombre</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" id="precio" name="precio" placeholder="precio">
                            <label for="floatingInput">Precio</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="existencia" name="existencia" placeholder="existencia">
                            <label for="floatingInput">Existencia</label>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button class="btn btn-primary w-25 py-2" type="submit">Crear Componente</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
