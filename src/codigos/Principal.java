package codigos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;

public class Principal {
	
	static Comparator<ArrayList<String>> compararPorNome = new Comparator<ArrayList<String>>() {
	    @Override
	    public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	    	
	    	String s1 = o1.get(2).trim();
	    	String s2 = o2.get(2).trim();
	    	
	        return s1.compareToIgnoreCase(s2);
	    }
	};
	
	public static String mascarar_cpf(String cpf) {
		
		String novo_cpf = "";
		String novo_cpf2 = "";
		
		for(int i = 0; i<cpf.length(); i++) {
			if(cpf.charAt(i) != '.' && cpf.charAt(i) != '-' && cpf.charAt(i) != '"') {
				novo_cpf += cpf.charAt(i);
			}
		}
		
		for(int i = 0; i<novo_cpf.length(); i++) {
			if(i > 2 && i < 8) {
				novo_cpf2 += "*";
			} else {
				novo_cpf2 += novo_cpf.charAt(i);
			}
		}
		
		return "\"" + novo_cpf2.substring(0, 3) + "." + novo_cpf2.substring(3, 6) + "." + novo_cpf2.substring(6, 9) + "-" + novo_cpf2.substring(9, 11) + "\""; 
		
	}
	
	public static String mascarar_email(String email) {
		String novo_email = "";
		
		int i = 0;
		
		int contador_asterisco = 0;
		
		while(email.charAt(i) != '@') {
			if(i < 3) {
				novo_email += email.charAt(i);
			} else {
				if(contador_asterisco < 5) {
					novo_email += "*";
					contador_asterisco++;
				}
			}
			i++;
		}
		
		while(i < email.length()) {
			novo_email += email.charAt(i);
			i++;
		}
		
		return novo_email;
	}
	
	
	public static String getTitulo(Eventos evento) {
		ArrayList<String> titulos = new ArrayList<String>();
		
		titulos.add("Palestra: Business 4.0 (Gabriel Briganó - Tata Consultancy Services)");
		titulos.add("Palestra: Motivação: Alavanca Para a Realização Pessoal e Profissional (Henrique Benevenuto)");
		titulos.add("Relato de Experiência (Rafael Pancione)");
		titulos.add("Palestra: Internet das Coisas e Outras Coisas (Linnyer Beatryz)");
		titulos.add("Minicurso de Desenvolvimento WEB (Fabio Matsunaga)");
		titulos.add("Minicurso de Desenvolvimento de Games (Fernando Cruz)");
		titulos.add("Palestra: Empreendedorismo em TI (Ebner Bossa - Webner)");
		titulos.add("Torneio de Games (Street Fighter II)");
		titulos.add("Algoritmo de Busca Cega com Base na Busca em Profundidade e Custo Uniforme (Guilherme Henrique de Souza Nakahata)");
		titulos.add("Implementação de TDD em Diagrama de Caso de Uso (André Borghi)");
		titulos.add("Aplicação da Lógica Fuzzy no Índice de Qualidade da Água (Sara Saori Satake)");
		titulos.add("Metodologia Gamificada de Ensino e Aprendizagem de Derivadas (Benedito Odair da Fonseca Júnior)");
		titulos.add("Mineração de Dados Aplicada na Previsão de Evasão do Ensino Superior (Kevin Rossetti Fernandes)");
		titulos.add("Utilização de Redes Neurais Artificiais para a Classificação de Risco de Incêndios Florestais (Bruno Noleto de Sousa)");
		
		return titulos.get(evento.ordinal()-1);
	}
	
	public static void gerar(Map<String,Object> parameters, String xmlFile, String fileName, Eventos evento) throws JRException {
		//String xmlFile = "src/main/resources/report2.xml";
        JasperReport report = JasperCompileManager.compileReport(xmlFile);

        String[] columnNames = new String[] {"indice","nome","e-mail","cpf"};

//        String fileName = "src/main/resources/items.csv";
        JRCsvDataSource ds = new JRCsvDataSource(fileName);
        ds.setColumnNames(columnNames);

        JasperPrint jprint = JasperFillManager.fillReport(report, parameters, ds);

        JasperExportManager.exportReportToPdfFile(jprint, "inscritos_"+ evento.toString() + ".pdf");
	}

	public static void main(String[] args) {
		
		// dia 1
		
		String evento_tcs = "Palestra: Business 4.0 (Gabriel Briganó - Tata Consultancy Services)";
		
		String evento_benevenuto = "Palestra: Motivação: Alavanca Para a Realização Pessoal e Profissional (Henrique Benevenuto)";
		String evento_pancione = "Relato de Experiência (Rafael Pancione)";
		String evento_linnyer = "Palestra: Internet das Coisas e Outras Coisas (Linnyer Beatryz)";
		
		// dia 2
		String evento_fabio = "Minicurso de Desenvolvimento WEB (Fabio Matsunaga)";
		String evento_fernando = "Minicurso de Desenvolvimento de Games (Fernando Cruz)";
		
		String evento_ebner = "Palestra: Empreendedorismo em TI (Ebner Bossa - Webner)";
		String evento_torneio = "Torneio de Games (Street Fighter II)";
		
		// dia 3
		String tcc_1 = "Algoritmo de Busca Cega com Base na Busca em Profundidade e Custo Uniforme (Guilherme Henrique de Souza Nakahata)";
		String tcc_2 = "Implementação de TDD em Diagrama de Caso de Uso (André Borghi)";
		String tcc_3 = "Aplicação da Lógica Fuzzy no Índice de Qualidade da Água (Sara Saori Satake)";
		
		String tcc_4 = "Metodologia Gamificada de Ensino e Aprendizagem de Derivadas (Benedito Odair da Fonseca Júnior)";
		String tcc_5 = "Mineração de Dados Aplicada na Previsão de Evasão do Ensino Superior (Kevin Rossetti Fernandes)";
		String tcc_6 = "Utilização de Redes Neurais Artificiais para a Classificação de Risco de Incêndios Florestais (Bruno Noleto de Sousa)";
		
		int inicio_colunas = 5;
		
		int coluna_dia1manha = inicio_colunas + 0;
		int coluna_dia1tarde = inicio_colunas + 1;
		int coluna_dia2manha = inicio_colunas + 2;
		int coluna_dia2tarde = inicio_colunas + 3;
		int coluna_dia3manha = inicio_colunas + 4;
		int coluna_dia3tarde = inicio_colunas + 5;
		
		ArrayList<ArrayList<String>> tabela_entrada = FuncoesCSV.csv_to_ArrayList("Inscrição no evento.csv", 1);
		ArrayList<ArrayList<String>> tabela_saida = new ArrayList<ArrayList<String>>();
		
		
		
		for(ArrayList<String> participante : tabela_entrada) {
			
			ArrayList<String> participante_nova_tabela = new ArrayList<String>();
			participante_nova_tabela.add(participante.get(0)); // adicionará o timestamp
			participante_nova_tabela.add(participante.get(2)); // adicionará o nome
			participante_nova_tabela.add(mascarar_email(participante.get(1))); // adicionará o email
			participante_nova_tabela.add(mascarar_cpf(participante.get(3))); // adicionará o cpf
			
			// dia 1 - manhã
			if(participante.get(coluna_dia1manha).contains(evento_tcs)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			// dia 1 - tarde
			
			if(participante.get(coluna_dia1tarde).contains(evento_benevenuto)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			if(participante.get(coluna_dia1tarde).contains(evento_pancione)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			if(participante.get(coluna_dia1tarde).contains(evento_linnyer)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			// dia 2 - manhã
			
			if(participante.get(coluna_dia2manha).contains(evento_fabio)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			if(participante.get(coluna_dia2manha).contains(evento_fernando)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			// dia 2 - tarde
			
			if(participante.get(coluna_dia2tarde).contains(evento_ebner)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			if(participante.get(coluna_dia2tarde).contains(evento_torneio)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			// dia 3 - manhã
			
			if(participante.get(coluna_dia3manha).contains(tcc_1)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			if(participante.get(coluna_dia3manha).contains(tcc_2)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			if(participante.get(coluna_dia3manha).contains(tcc_3)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			// dia 3 - tarde
			
			if(participante.get(coluna_dia3tarde).contains(tcc_4)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			if(participante.get(coluna_dia3tarde).contains(tcc_5)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			if(participante.get(coluna_dia3tarde).contains(tcc_6)) {
				participante_nova_tabela.add("\"true\"");
			} else {
				participante_nova_tabela.add("\"false\"");
			}
			
			tabela_saida.add(participante_nova_tabela);
		}
		
		
		
		String header = "";
		header += "\"indice\",";
		header += "\"data_hora\",";
		header += "\"nome\",";
		header += "\"e-mail\",";
		header += "\"cpf\",";
		header += "\"evento_tcs\",";
		header += "\"evento_benevenuto\",";
		header += "\"evento_pancione\",";
		header += "\"evento_linnyer\",";
		header += "\"evento_fabio\",";
		header += "\"evento_fernando\",";
		header += "\"evento_ebner\",";
		header += "\"evento_torneio\",";
		header += "\"evento_tcc1\",";
		header += "\"evento_tcc2\",";
		header += "\"evento_tcc3\",";
		header += "\"evento_tcc4\",";
		header += "\"evento_tcc5\",";
		header += "\"evento_tcc6\",";
		
		Collections.sort(tabela_saida,compararPorNome);		
		
		Map<Eventos,ArrayList<ArrayList<String>>> eventos = new HashMap<Eventos, ArrayList<ArrayList<String>>>();
		
		for(int i = 1; i < Eventos.values().length; i++) {
			
			Eventos evento_atual = Eventos.values()[i];
			
			int indice_tabela_saida = i+3;
			
			ArrayList<ArrayList<String>> evento = new ArrayList<ArrayList<String>>();
			
			for(ArrayList<String> participante : tabela_saida) {

				if(participante.get(indice_tabela_saida).contains("true")){
					ArrayList<String> participante_novo = new ArrayList<String>();
					
					//participante_novo.add(participante.get(0));
					participante_novo.add(participante.get(1));
					participante_novo.add(participante.get(2));
					participante_novo.add(participante.get(3));
					evento.add(participante_novo);
					
				}
				
			}
			
			eventos.put(evento_atual, evento);
		}
		
		for(int i = 1; i< Eventos.values().length; i++) {
			
			
			Eventos evento_atual = Eventos.values()[i];
			
			ArrayList<ArrayList<String>> evento = eventos.get(evento_atual);
			
			String header2 = "";
			header2 += "\"indice\",";
			header2 += "\"nome\",";
			header2 += "\"e-mail\",";
			header2 += "\"cpf\"";
			
			for(int j = 0 ; j < evento.size(); j++) {
				
				evento.get(j).add(0, "\""+ String.valueOf(j+1) + "\"");
				
			}
			
			FuncoesCSV.salvarCSV("inscritos_"+ evento_atual.toString() +".csv", header2, evento);
			
			Map<String,Object> parametros = new HashMap<String,Object>();
			parametros.put("titulo_evento", getTitulo(evento_atual));
			
			
			try {
				gerar(parametros, "relatorio.jrxml", "inscritos_"+ evento_atual.toString() +".csv" , evento_atual);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FuncoesCSV.deletarArquivo("inscritos_"+ evento_atual.toString() +".csv");
			
		}
		
		
		
		
		//FuncoesCSV.deletarArquivo("Inscrição no evento.csv");
		
	}

}
