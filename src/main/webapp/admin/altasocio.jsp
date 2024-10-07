<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuevo Socio</title>
<jsp:directive.include file="../includes/includefile.jspf" />
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<c:if test="${error != null}">
			<div class="diverror">
				<p>
					<strong><c:out value="Error" /></strong> <br>
					<c:out value="${error}" />
				</p>
			</div>
		</c:if>
		<c:if test="${confirmaroperacion != null}">
			<div class="divconfirmacion">
				<p>
					<strong><c:out value="Mensaje" /></strong> <br>
					<c:out value="${confirmaroperacion}" />
				</p>
			</div>
		</c:if>
		<div id="formSocio" class="formulariogeneral">
			<form name="frmSocio" method="post" action="${pageContext.request.contextPath}/controllerAdmin">
				<fieldset id="datosSocio">
					<legend><img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">&nbsp;Nuevo Socio</legend>
					<div class="etiquetas">
						<label for="nombre">Nombre:</label>
					</div>
					<div class="campos">
						<input type="text" 
						       id="nombre" 
						       name="nombre"
						       value="${nuevoSocio.nombre}" />
					</div>
					<div class="etiquetas">
						<label for="email">Email:</label>
					</div>
					<div class="campos">
						<input type="text" 
						       id="email" 
						       name="email"
						       value="${nuevoSocio.email}" />
					</div>
					<div class="etiquetas">
						<label for="direccion">Direccion:</label>
					</div>
					<div class="campos">
						<input type="text" 
						       id="direccion" 
						       name="direccion" 
						       value="${fechaErronea}"
						       required/>
					</div>
					<div class="etiquetas">
						<label for="Password">Password:</label>
					</div>
					<div class="campos">
						<input type="password" 
						       id="password" 
						       name="password"
						       required/>
						       
						<input name="operacion" 
						       type="hidden" 
						       id="operacion" 
						       value="insertaSocio">
					</div>
					<div class="botones">	
							<input type="submit" name="Submit" value="Alta">
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>