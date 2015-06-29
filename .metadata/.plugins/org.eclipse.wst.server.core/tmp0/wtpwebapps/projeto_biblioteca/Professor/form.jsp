<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<fatec:genericpage title="Cadastro de Professor">
	<jsp:attribute name="body">
	
		<h3>Registro de Professor</h3>
	
		<div class="container">
			<div class="row clearfix">
			<div class="col-md-12 column">
				<form role="form" action="Professor!registerProfessor">
				
					
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
						<label id="lblRegistro">Registro:</label>
						<input type="text" class="form-control" name="registro" />
					</div>

				
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
			</div>
		</div>
	</jsp:attribute>
</fatec:genericpage>
