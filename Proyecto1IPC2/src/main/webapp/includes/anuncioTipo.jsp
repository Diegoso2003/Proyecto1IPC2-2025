<%-- 
    Document   : anuncioTipo
    Created on : 15 sept 2024, 15:42:57
    Author     : rafael-cayax
--%>

<%@page import="com.mycompany.proyecto1ipc2.backend.anuncios.Anuncio"%>
<%@page import="com.mycompany.proyecto1ipc2.backend.anuncios.EnumTipoAnuncio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  Anuncio anuncio = (Anuncio) request.getAttribute("anuncio");
    if (anuncio.getTipo() == EnumTipoAnuncio.TEXTO) {%>
<p>Anuncio: ${anuncio.getTexto()}</p>
<% } else if (anuncio.getTipo() == EnumTipoAnuncio.TEXTOIMAGEN) {%>
<p>Anuncio de Texto: ${anuncio.getTexto()}</p>
<p>Anuncio Imagen:</p>
<img src="${pageContext.servletContext.contextPath}/mvc/ServletMedia?id=${anuncio.getId()}" alt="Imagen anuncio">
<% } else {%>
<p>Anuncio Video: </p>
<video controls width="640">
    <source src="${pageContext.servletContext.contextPath}/mvc/ServletMedia?id=${anuncio.getId()}" type="video/${anuncio.getExtension()}">
    Video anuncio
</video>
<% } %>
