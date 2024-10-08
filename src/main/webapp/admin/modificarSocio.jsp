<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Socio</title>
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
					<legend><img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">&nbsp;Modificar Socio de id: ${socioAEditar.idSocio} y de nombre: ${socioAEditar.nombre}</legend>
					<div class="etiquetas">
						<label for="nombre">Nombre:</label>
					</div>
					<div class="campos">
						<input type="text" 
						       id="nombre" 
						       name="nombre"
						       value="${socioAEditar.nombre}" />
					</div>
					<div class="etiquetas">
						<label for="direccion">Direccion:</label>
					</div>
					<div class="campos">
						<input type="text" 
						       id="direccion" 
						       name="direccion" 
						       value="${socioAEditar.direccion}"
						       required/>
						<input name="operacion" 
						       type="hidden" 
						       id="operacion" 
						       value="guardarSocio">
						<input name="idSocioAEditar" 
						       type="hidden" 
						       id="idSocioAEditar" 
						       value="${socioAEditar.idSocio}">
					</div>
					<div class="cb"></div>
					<div class="botones">	
							<input type="submit" name="Submit" value="Cambiar">
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>