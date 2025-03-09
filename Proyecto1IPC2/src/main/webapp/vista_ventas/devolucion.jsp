<%-- 
    Document   : devolucion.jsp
    Created on : 8 mar 2025, 1:37:56
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
                        text-center">Realizar Devolucion</h5>
                    <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/ventas/devolucion" method="post" enctype="multipart/form-data">
                        <div class="form-floating mb-3">
                            <input type="file" class="form-control" id="factura" name="factura" placeholder="factura" accept="application/pdf">
                            <label for="factura">Factura</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="codigo" name="id" placeholder="codigo">
                            <label for="codigo">Codigo de la computadora</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="date" class="form-control" id="fecha" name="fechaDevolucion" placeholder="fecha">
                            <label for="fecha">Fecha</label>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary w-25 py-2 m-3">Realizar Devolucion</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
