<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<fatec:genericpage title="Editora">
	<jsp:attribute name="body">
	
		<h3>Cadastro de Editoras</h3>
	
		<div class="container">
			<div class="row clearfix">
			<div class="col-md-12 column">
				<form role="form" action="Editora!registerEditora">
				
					
					<div class="form-group">
						<input type="hidden" class="form-control" name="id" />
					</div>
					
					<div class="form-group">
						<label id="lblNome">Nome:</label>
						<input type="text" class="form-control" name="nomeEditora" />
					</div>
					
					<div class="form-group">
						<label id="lblSeguimento">Seguimento:</label>
						<input type="text" class="form-control" name="seguimento" />
					</div>
				
				
					<button type="submit" class="btn btn-sucess">Submit</button>
				</form>
			</div>
			</div>
		</div>
	</jsp:attribute>
</fatec:genericpage>
