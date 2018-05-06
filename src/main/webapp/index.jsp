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
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'/>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/plugins/jquery-fields.min.js"></script>
        <link rel='stylesheet' href='js/plugins/toastr.min.css'/>
        <script type="text/javascript" src="js/plugins/toastr.min.js"></script>
        <script type="text/javascript" src="js/mens.js"></script>
        <script type="text/javascript" src="js/equipos.js"></script>
    </head>
    <body>
        <div class="container">
            <jsp:useBean id="objeEqui" class="com.sv.udb.modelo.Equipos" scope="request">
                <jsp:setProperty name="objeEqui" property="*"/>
            </jsp:useBean>
            <c:if test = '<%=request.getHeader("Referer") != null %>'>
                <c:if test='${estaProcesado == false}'>
                    <% request.setAttribute("estaProcesado", true); %>
                    <jsp:forward page="EquiposServ"/>
                </c:if>
            </c:if>
            <div class="alert alert-success">
                <strong>Indicaciones:</strong> Usando Bootstrap con la Guía 08.
            </div>
            <div class="row">
                <div class="col-md-5">
                    <div class="panel panel-primary">
                        <div class="panel-heading">El Formulario</div>
                        <div class="panel-body">
                            <c:if test = "${mensAler != null}">
                                <c:choose>
                                    <c:when test = "${resp}">
                                        <div class="alert alert-success">
                                            ${mensAler}
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="alert alert-danger">
                                            ${mensAler}
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                            <form method="POST" action="EquiposServ" id="formEqui">
                                <input type="hidden" name="codiEqui"/>
                                <div class="form-group">
                                    <label for="nomb">Nombre:</label>
                                    <input type="text" class="form-control" name="nombEqui"/>
                                </div>
                                <div class="form-group">
                                    <label for="desc">Descripción:</label>
                                    <input type="text" class="form-control" name="descEqui"/>
                                </div>
                                <div class="btn-group">
                                    <input type="submit" class="btn btn-default" name="btonEqui" value="Guardar"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="panel panel-primary">
                        <div class="panel-heading">La Tabla</div>
                        <div class="panel-body">
                            <form method="POST" action="EquiposServ" id="formTabl">
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
