<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<fatec:genericpage title="Cadastro de Exemplar">
	<jsp:attribute name="body">
	
		<h3>Registro de Exemplar</h3>
	
		<div class="container">
			<div class="row clearfix">
			<div class="col-md-12 column">
				<form role="form" action="Livro!registerLivro">
				
					
					<div class="form-group">
						<input type="hidden" class="form-control" name="id" />
					</div>
					
					<div class="form-group">
						<label id="lblNome">T�tulo:</label>
						<input type="text" class="form-control" name="titulo" />
					</div>
					
					<div class="form-group">
						<label id="lblRg">Edi��o:</label>
						<input type="number" class="form-control" name="edicao" />
					</div>
					
					<div class="form-group">
						<label id="lblRa">Data de Pulblica��o:</label>
						<input type="date" class="form-control" name="anoPublicacao" />
					</div>
					
					<div class="form-group">
						<select name="empresa">
                        {% for e in lista_empresa %}
                        <option value="{{ e.key.id() }}"> {{ e.nome }}</option>
                        {% endfor %}
                    </select>
					</div>

				
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
			</div>
		</div>
	</jsp:attribute>
</fatec:genericpage>
