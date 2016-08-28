<%-- 
    Document   : login
    Created on : Aug 27, 2016, 4:58:04 PM
    Author     : mauricio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <h1>Login</h1>
    
    <f:view>
        <h:messages style="color: red"
                    showDetail="true"/>
        <h:form id="login">
            <h:panelGrid columns="2" border="0">
                Usuario: <h:inputText id="username" 
                                       value="#{userHandlerBean.username}"/>        
                Contraseña: <h:inputSecret id="password"
                                         value="#{userHandlerBean.password}"/>
            </h:panelGrid>
            <h:commandButton id="submit" 
                             type="submit"
                             value="Ingresar"
                             action="#{userHandlerBean.validateUser}"/>
            <br>
            <h:commandLink id="create"
                           value="Create New Account"
                           action="create"/>
        </h:form>
       
    </f:view>
    
    </body>
</html>
