<%-- 
    Document   : form_computadora_mas_vendida
    Created on : 11 mar 2025, 14:18:52
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
        <jsp:include page="/vista_financiera/header_financiero.jsp"/>

        <jsp:include page="/includes/informacion.jsp"/>
        <div class="d-flex justify-content-center">
            <div class="card w-50 m-3 border-info">
                <div class="card-body">
                    <h5 class="card-title
                        text-center">Reporte de computadora con mas ventas</h5>
                    <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/financiero/compu_mas_ventas" method="post">
                        <div class="form-floating mb-3">
                            <input type="date" class="form-control" id="fechaInicio" name="fechaInicio" placeholder="fechaInicio">
                            <label for="fecha">Fecha Inicio</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="date" class="form-control" id="fechaFin" name="fechaFin" placeholder="fechaFin">
                            <label for="fecha">Fecha Fin</label>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary w-25 py-2 m-3">Obtener Reporte</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
