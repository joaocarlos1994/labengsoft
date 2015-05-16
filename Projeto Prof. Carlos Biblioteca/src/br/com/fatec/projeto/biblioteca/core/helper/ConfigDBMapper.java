package br.com.fatec.projeto.biblioteca.core.helper;

import java.util.List;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;





import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ConfigDBMapper {

	// Instância única para garantir a reutilização das configConnections
	private static ConfigDBMapper instance;
	// Objeto que irá traduzir os dados do arquivos database.json
	private static JSONParser parser = new JSONParser();
	// um class loader para encontrar facilmente o arquivo de config
	private static final ClassLoader loader = ConfigDBMapper.class.getClassLoader();
	// Mapa de configConnections já existentes, permintindo que toda configConnections,
	// depois
	// de devidamente criada, possa ser reutilizada sempre que necessário.
	private static Map<String, ConfigConnection> configConnections = new HashMap<String, ConfigConnection>();
	// propriedade utilizada para definir qual será o nome da conexão padrão,
	// podendo assim variar para teste ou produção
	private String defaultConnectionName;
	// Lista de configs disponíveis para utilização
	private List<String> possibleConfig;

	
	public static ConfigDBMapper getInstance(){
		if(instance == null){
			return instance = new ConfigDBMapper();
		}
		return instance;
	}
	
	public void loadConnections(){
	
	try {
	
	//O primeiro passo para carregar as configurações é abrir o
	//arquivo de configurações. Para isso vamos utilizar nosso 'loader'
	//para pegar o 'path' do mesmo.
	
	String path = loader.getResource("br/com/fatec/projeto/biblioteca/core/config/databases.json").getPath();
	//Com 'path em mãos iremos converter, fazer 'parse', de seu
	//contéudo para o objeto JSONArray, já que o arquivo começa
	//com '[...]', ou seja, o arquivo é um array
	JSONArray configs = (JSONArray) ConfigDBMapper.parser.parse(new FileReader(path));
	//Fazemos uma verificação de quantidade de configs, pois
	//precisamos de ao menos uma para o sistema funcione.
	if(configs.size() < 1){
		throw new RuntimeException("É necessário ao menos uma configuração de Banco de Dados");
	}
	
	//Com a lista de configs podemos iniciar um loop que irá criar
	//todas as conexões. Infelimente a API do JSON não é muito esperta
	//para descobrir os tipos dos objetos e atributos, então precisamos
	//sempre fazer o cast dos valores
	for(Object config : configs){
		//primeiro o cast de cada elemento existente no array para
		//JSONObject
		JSONObject configJSON = (JSONObject) config;
		//Então usamos o método 'get' da classe JSONObject para pegar o
		//valor correpondente a cada 'chave' que passarmos.
		String configNameJson = (String) configJSON.get("name");
		String url = (String) configJSON.get("url");
		String login = (String) configJSON.get("login");
		String password = (String) configJSON.get("password");
		String driverClassName = (String) configJSON.get("driverClassName");
		//Tendo todos os dados, url, login, password e className do
		//driver que iremos utilizar fazemos duas operações:
		//1 - Verificar se o driver está carregando na aplicação
		Class.forName(driverClassName);
		//2 - Criamos uma conexão utilizando os dados obtidos pelo
		//nosso loader e a colocamos no mapa de conexões.
		configConnections.put(configNameJson, new ConfigConnection(configNameJson, url, login, password));
	}
	//ára finalizar criamos uma lista de todos os nomes de configs
	//disponiveis utilizando as 'keys' do mapa
	this.possibleConfig = new ArrayList<String>(configConnections.keySet());
	
	} catch (Exception ex ){
		throw new RuntimeException();
	}
	
	}

	/**
	 * Esse método possui uma iteligência que pode parecer uma pegadinha. Ele
	 * faz com que a propreidade 'defaultConnectionName' receba o valor uma
	 * única vez, com isso se torna impossível que por acidente a configuração
	 * padrão de banco seja alterada. Além disso esse método também verifica se
	 * a 'config' passada existe na lista de possíveis conexões, caso não uma
	 * exeção é lançada
	 *
	 * @param config
	 * */

	public void setDefaultConnectionName(String config) {
		if (this.defaultConnectionName == null && config == "") {
			if (this.possibleConfig.contains(config)) {
				this.defaultConnectionName = config;
			} else {
				throw new RuntimeException("Não existe configuração com nome '"
						+ config + "'.");
			}
		}
	}

	/**
	 * @return {@link Connetion} gerada a partir da propriedade
	 *         defaultConnectionName, ou null caso não esteja configurada
	 * */

	public Connection getDefaultConnetion() {
		if (this.defaultConnectionName == null) {
			return null;
		}
		return this.getConnectionByConfig(this.defaultConnectionName);
	}

	/**
	 * @return lista com todos os nomes de configurações disponíveis, essa lista
	 *         é criada a partir do método 'loadConnections'
	 * */

	public List<String> getPossibleConfigs() {
		return this.possibleConfig;
	}

	/**
	 * @param configName
	 * @return {@link Connetion} gerada a partir do arquivo de configuração que
	 *         possua a 'configName' passada ou exception caso essa configuração
	 *         não existisse no momento de load das configs
	 * */

	public Connection getConnectionByConfig(String configName) {
		if (configConnections.containsKey(configName)) {
			ConfigConnection configConnection =  configConnections.get(configName);
			try {
				return DriverManager.getConnection(configConnection.getUrl(), configConnection.getLogin(),
						configConnection.getPassword());
			} catch (Exception e) {
				throw new RuntimeException("Não foi possível geral uma conexão para o Banco de Dados '" + configName + "' .", e);
			}
		}
		throw new RuntimeException("Não existe configuração com nome '"
				+ configName + "'.");
	}

	public void AlunoDAOImpl() {
		this.configConnections = (Map<String, ConfigConnection>) ConfigDBMapper.getInstance().getDefaultConnetion();
		
	}
}
