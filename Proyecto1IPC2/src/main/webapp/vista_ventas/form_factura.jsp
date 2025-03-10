<%-- 
    Document   : form_factura
    Created on : 9 mar 2025, 22:19:03
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
                        text-center">Consulta de factura</h5>
                    <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/ventas/consulta_factura" method="post">
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" id="id" name="id" placeholder="id">
                            <label for="codigo">NO de factura</label>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary w-25 py-2 m-3">Consultar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
