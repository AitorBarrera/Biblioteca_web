<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista Autores</title>
<jsp:directive.include file="../includes/includefile.jspf" />
    <link rel="stylesheet" href="Resources/css/estiloPropio.css">
    <link rel="stylesheet" href="./resources/css/estiloPropio.css">
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<h1>Lista Autores</h1>
		<div style="align-items: center;">
		<table id="listadoAutores">
			<thead>
				<th>Id</th>
				<th>Nombre</th>
				<th>Fecha de nacimiento</th>
			</thead>
			<tbody>
				<c:forEach items="${listaAutores}" var="autor">
					<tr>
						<td>
							<c:out value="${autor.idAutor}"></c:out>
						</td>
						<td>
							<c:out value="${autor.nombre}"></c:out>
						</td>
						<td>
							<c:out value="${autor.fechaNacimiento}"></c:out>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
        </div>
	</div>
</body>
</html>