<%-- 
    Document   : crear_cliente
    Created on : 4 mar 2025, 0:50:26
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
        <jsp:include page="/vista_ventas/header_ventas.jsp"/>

        <jsp:include page="/includes/informacion.jsp"/>
        
        <div class="d-flex justify-content-center">
            <div class="card w-50 m-3 border-info">
                <div class="card-body">
                    <h5 class="card-title
                        text-center">Crear Cliente</h5>
                    <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/ventas/cliente" method="post">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="nit" name="nit" placeholder="nit">
                            <label for="nit">NIT</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="nombre">
                            <label for="nombre">Nombre</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="direccion" name="direccion" placeholder="direccion">
                            <label for="direccion">Direccion</label>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary w-25 py-2 m-3">Crear</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
