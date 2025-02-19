<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/informacion.jsp"/>

        <div class="d-flex justify-content-center align-items-center vh-100">
            <div class="card">
            <div class="card-body">
                <form class="mb-5">
                <h1 class="h3 mb-3 fw-normal">Por favor ingrese sus datos</h1>
                
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="nombre">
                    <label for="floatingInput">Nombre de usuario</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="contraseña">
                    <label for="floatingPassword">Contraseña</label>
                </div>
                
                <button class="btn btn-primary w-100 py-2" type="submit">Iniciar Sesion</button>
                </form>
            </div>
            </div>
        </div>

    </body>
</html>
