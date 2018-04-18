<%-- 
    Document   : index
    Created on : 03-14-2018, 04:57:17 PM
    Author     : JMagoSV
--%>

<%@page import="com.sv.udb.controlador.EquiposCtrl"%>
<%@page import="com.sv.udb.modelo.Equipos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Jugadores</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="alert alert-success">
                <strong>Indicaciones:</strong> Usando Bootstrap con la Guía 08.
            </div>
            <div class="row">
                <div class="col-md-5">
                    <div class="panel panel-primary">
                        <div class="panel-heading">El Formulario</div>
                        <div class="panel-body">
                            <c:if test="${resp}">
                                <div class="alert alert-success">
                                    ${mensAler}
                                </div>
                            </c:if>
                            <div class="alert alert-success">
                                ${mensAler}
                            </div>
                            <form method="POST" action="EquiposServ" name="Demo">
                                <input type="hidden" name="codi" id="codi" value="${codi}"/>
                                <div class="form-group">
                                    <label for="nomb">Nombre:</label>
                                    <input type="text" class="form-control" name="nomb" id="nomb" value="${nomb}"/>
                                </div>
                                <div class="form-group">
                                    <label for="desc">Descripción:</label>
                                    <input type="text" class="form-control" name="desc" id="desc" value="${desc}"/>
                                </div>
                                    <c:choose>
                                        <c:when test = "${mensAler != false}">
                                            <input type="submit" class="btn btn-default" name="btonEqui" value="Guardar"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="submit" class="btn btn-default" name="btonEqui" value="Nuevo"/>
                                            <input type="submit" class="btn btn-primary" name="btonEqui" value="Modificar"/>
                                            <input type="submit" class="btn btn-danger" name="btonEqui" value="Eliminar"/>
                                        </c:otherwise>
                                    </c:choose>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="panel panel-primary">
                        <div class="panel-heading">La Tabla</div>
                        <div class="panel-body">
                            <form method="POST" action="EquiposServ" name="Tabl">
                                <display:table export="true" id="tablEqui" name="<%= new EquiposCtrl().consTodo()%>">
                                    <display:column title="Cons" >
                                        <input type="radio" name="codiEquiRadi" value="${tablEqui.codiEqui}"/>
                                    </display:column>
                                    <display:column property="descEqui" title="Descripción" sortable="true"/>                                            
                                    <display:column property="nombEqui" title="Nombre Equipo" sortable="true"/>
                                </display:table>
                                <input type="submit" class="btn btn-success" name="btonEqui" value="Consultar"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
