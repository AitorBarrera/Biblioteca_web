<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista Socios</title>
<jsp:directive.include file="../includes/includefile.jspf"/>
</head>
<body>
	<div class="container">
		<div class="header"></div>
		<div class="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<h1>Lista Socios</h1>
		<div style="width:100%">
		<div class="w-75 ma">
		<table id="listadoSocios" class="table tablacebra tablaconborde">
			<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Email</th>
				<th>Direccion</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach items="${listadosocios}" var="socio">
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
			<div class="w-75 ma py-2">
				<c:set var="totalregistros" value="${totalregistros}"></c:set>
				<c:set var="paginaactual" value="${pagina}"></c:set>
				<c:set var="registrosporpagina" value="${numregpag}"></c:set>
				<c:set var="paginamasalta" value="${paginamasalta}"></c:set>
				<c:out value="Total Registros: ${totalregistros}"></c:out>
				<c:out
					value="Mostrando desde ${(registrosporpagina*paginaactual)+1} a ${(registrosporpagina*paginaactual)+registrosporpagina < totalregistros?(registrosporpagina*paginaactual)+registrosporpagina:totalregistros}"></c:out>
				<a href="${pageContext.request.contextPath}/controllerAdmin?operacion=listadoSociosPaginado&pag=${paginaactual-1>=0?paginaactual-1:paginamasalta}&nrp=${registrosporpagina} ">Ant</a>
				<a
					href="${pageContext.request.contextPath}/controllerAdmin?operacion=listadoSociosPaginado&pag=${paginaactual+1>paginamasalta?0:paginaactual+1}&nrp=${registrosporpagina} ">
					Sig</a> 
			</div>
        </div>
	</div>
</body>
</html>