<%@ taglib prefix="fatec" tagdir="/WEB-INF/tags"%>
<fatec:genericpage title="Cadastro de Emprestimo">
	<jsp:attribute name="body">
	
		<h3>Registro Emprestimo</h3>
	
		<div class="container">
			<div class="row clearfix">
			<div class="col-md-12 column">
				<form role="form" action="Emprestimo!registerEmprestimo">
				
					
					<div class="form-group">
						<input type="hidden" class="form-control" name="id"  />
					</div>
					
					<div class="form-group">
						<!-- Aluno -->
						<select name="empresa">
                        {% for e in lista_empresa %}
                        <option value="{{ e.key.id() }}"> {{ e.nome }}</option>
                        {% endfor %}
                    </select>
					</div>
					
					<div class="form-group">
						<!-- Professor -->
						<select name="empresa">
                        {% for e in lista_empresa %}
                        <option value="{{ e.key.id() }}"> {{ e.nome }}</option>
                        {% endfor %}
                    </select>
					</div>
					
					<div class="form-group">
						<label id="lblRa">Data de Emprestimo:</label>
						<input type="date" class="form-control" name="dataEmprestimo" />
					</div>
					
					<div class="form-group">
						<label id="lblRa">Data de Entrega:</label>
						<input type="date" class="form-control" name="dataEntrega" />
					</div>
				
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
			</div>
		</div>
	</jsp:attribute>
</fatec:genericpage>
