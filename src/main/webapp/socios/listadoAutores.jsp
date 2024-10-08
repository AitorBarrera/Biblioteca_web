<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista Autores</title>
<jsp:directive.include file="../includes/includefile.jspf" />
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<h1>Lista Autores</h1>
		<div style="align-items: center;">
		<div class="w-75 ma">
		<table id="listadoAutores" class=" table tablacebra tablaconborde">
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
							<!--<c:out value="${autor.fechaNacimiento}"></c:out>-->
        					<fmt:formatDate pattern="dd/MM/yyyy" value="${autor.fechaNacimiento}"/>
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