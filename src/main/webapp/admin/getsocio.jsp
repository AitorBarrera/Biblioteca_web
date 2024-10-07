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
					<legend><img src="${pageContext.request.contextPath}/resources/img/azarquiel.gif">&nbsp;Modificar Socio</legend>
					<div class="etiquetas">
						<label for="nombre">Nombre:</label>
					</div>
					<div class="campos">
						<input type="text" 
						       id="nombre" 
						       name="nombre"
						       value="${modificarSocio.nombre}" />
					</div>
					<div class="campos">
						<input name="operacion" 
						       type="hidden" 
						       id="operacion" 
						       value="modificarSocio">
					</div>
					<div class="botones">	
							<input type="submit" name="Submit" value="Guardar">
					</div>
				</fieldset>
			</form>
		
		<h1>Lista Socios</h1>
		<div style="width:100%">
		<table id="listadoSocios" style="margin-left: auto; margin-right: auto;">
			<thead>
				<th>Id</th>
				<th>Nombre</th>
				<th>Email</th>
				<th>Direccion</th>
			</thead>
			<tbody>
				<c:forEach items="${listaSocios}" var="socio">
					<tr>
						<td>
							<c:out value="${socio.idSocio}"></c:out>
						</td>
						<td>
							<c:out value="${socio.nombre}"></c:out>
						</td>
						<td>
							<c:out value="${socio.email}"></c:out>
						</td>
						<td>
							<c:out value="${socio.direccion}"></c:out>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
        </div>
		</div>
	</div>
</body>
</html>