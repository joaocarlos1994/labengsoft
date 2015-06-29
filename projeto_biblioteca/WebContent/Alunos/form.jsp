<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<fatec:genericpage title="Cadastro de Alunos">
	<jsp:attribute name="body">
	
		<h3>Registro de Alunos</h3>
	
		<div class="container">
			<div class="row clearfix">
			<div class="col-md-12 column">
				<form role="form" action="Alunos!registerAluno">
						
					<div class="form-group">
						<input type="hidden" class="form-control" name="id" />
					</div>
					
					<div class="form-group">
						<label id="lblNome">Nome:</label>
						<input type="text" class="form-control" name="nome" />
					</div>
					
					<div class="form-group">
						<label id="lblRg">RG:</label>
						<input type="text" class="form-control" name="rg" />
					</div>
					
					<div class="form-group">
						<label id="lblRa">RA:</label>
						<input type="text" class="form-control" name="ra" />
					</div>

				
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
			</div>
		</div>
	</jsp:attribute>
</fatec:genericpage>
