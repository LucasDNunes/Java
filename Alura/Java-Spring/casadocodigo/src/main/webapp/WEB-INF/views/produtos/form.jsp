<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do Código</title>
</head>
<body>
	<form action="/casadocodigo/produtos" method="POST">
		<div>
			
			<label>Titulo</label>
			<form:errors path="produto.titulo" />
			<input type="text" name="titulo" >

		</div>
		<div>
			
			<label>Descrição</label>
			<form:errors path="produto.descricao" />
			<textarea rows="10" cols="20" name="descricao"></textarea>
			
		</div>
		
		<div>
			
			<label>Páginas</label>
			<input type="text" name="paginas" >
			
		</div>
		<div>
		</div>
		
			<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
				<div>
					<label>${tipoPreco }</label>
					<input type="text" name="precos[${status.index }].valor">
					<input type="hidden" name="precos[${status.index }].tipo" value="${tipoPreco}">
				</div>
			</c:forEach>
			
			<button type="submit">Cadastrar</button>
	</form>
</body>
</html>