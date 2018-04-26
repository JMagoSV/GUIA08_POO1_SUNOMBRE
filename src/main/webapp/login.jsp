<%-- 
    Document   : login
    Created on : 04-26-2018, 04:51:20 PM
    Author     : JMagoSV
--%>

<%@page import="com.sv.udb.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <jsp:useBean id="objeUsua" class="com.sv.udb.modelo.Usuario" scope="request">
                <jsp:setProperty name="objeUsua" property="*"/>
            </jsp:useBean>
            <div class="row">
                <div class="col-md-offset-5 col-md-3">
                    <form class="form-login" method="POST">
                        <h4>Login</h4>
                        <input type="text" id="userName" name="usua" class="form-control input-sm chat-input" placeholder="username" />
                        <br/>
                        <input type="text" id="userPassword" name="cont" class="form-control input-sm chat-input" placeholder="password" />
                        <br/>
                        <div class="wrapper">
                            <span class="group-btn">
                                <input type="submit" value="Entrar" class="btn btn-primary btn-md" />
                            </span>
                        </div>
                    </form>
                    <form class="form-login" method="POST" action="LoginServ">
                                <input type="submit" value="Logout" class="btn btn-primary btn-md" />                        
                    </form>
                </div>
            </div>
            <%
                Object usua = request.getAttribute("objeUsua");
                Usuario usuaLoge = usua != null ? (Usuario)usua : null;
                System.err.println("El usuario es: " + usuaLoge.getUsua());
                if(usuaLoge != null)
                    session.setAttribute("usuaLogeado", usuaLoge);
            %>
        </div>
    </body>
</html>
