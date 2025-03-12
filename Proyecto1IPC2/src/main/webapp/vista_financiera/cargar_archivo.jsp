<%-- 
    Document   : cargar_archivo
    Created on : 10 mar 2025, 5:06:57
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

        <div class="d-flex justify-content-center">
            <div class="card w-50 m-3 border-info">
                <div class="card-body">
                    <h5 class="card-title text-center">Cargar Archivo</h5>
                    <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/financiero/carga_archivo" method="post" enctype="multipart/form-data">
                        <div class="form-floating mb-3">
                            <input type="file" class="form-control" id="archivo" name="archivo" placeholder="archivo">
                            <label for="archivo">Seleccionar Archivo</label>
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary w-25 py-2 m-3">Cargar Archivo</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/includes/informacion.jsp"/>
    </body>
</html>
