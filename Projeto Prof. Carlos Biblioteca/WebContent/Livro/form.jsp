<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<fatec:genericpage title="Cadastro de Alunos">
	<jsp:attribute name="body">
	
		<h3>Registro de Alunos</h3>
	
		<div class="container">
			<div class="row clearfix">
			<div class="col-md-12 column">
				<form role="form" formaction="Livro!registerLivro">
				
					
					<div class="form-group">
						<input type="hidden" class="form-control" name="id" />
					</div>
					
					<div class="form-group">
						<label id="lblNome">Título:</label>
						<input type="text" class="form-control" name="titulo" />
					</div>
					
					<div class="form-group">
						<label id="lblRg">Edição:</label>
						<input type="number" class="form-control" name="edicao" />
					</div>
					
					<div class="form-group">
						<label id="lblRa">Data de Pulblicação:</label>
						<input type="date" class="form-control" name="anoPublicacao" />
					</div>
					
					<div class="form-group">
						<label id="lblRg">Editora</label>
						<input type="number" class="form-control" name="editora" />
					</div>

				
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
			</div>
		</div>
	</jsp:attribute>
</fatec:genericpage>
