package codigos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FuncoesCSV {
	
	public static ArrayList<ArrayList<String>> csv_to_ArrayList (String arquivo, int saltar_linhas){
		
		String linha = "";
		
		ArrayList<ArrayList<String>> tabela = new ArrayList<ArrayList<String>>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {

			for(int i = 0; i < saltar_linhas; i++) {
				br.readLine();
			}
			
	        while ((linha = br.readLine()) != null) {
	        	
	        	ArrayList<String> lst_linha = new ArrayList<String>();
	        	
	            // use comma as separator
		        String[] coluna = linha.split(",");
		        
		        try {
		        	for (int i = 0; coluna[i] != null; i++) {
			        	lst_linha.add(coluna[i]);
			        }	
		        } catch(ArrayIndexOutOfBoundsException e) {
		        	
		        }

		        tabela.add(lst_linha);
	        }
		
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		return tabela;
	}
	
	public static void deletarArquivo(String arquivo) {
		File file = new File(arquivo);
		
		file.delete();
	}
	
	public static void salvarCSV(String arquivo, String header, ArrayList<ArrayList<String>> tabela) {
		
		try {
		
			File file = new File(arquivo);
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	      /*  
	        bw.write(header);
	        bw.newLine();*/
	        for(int i=0;i<tabela.size();i++){
	        	for(int j=0; j<tabela.get(i).size(); j++) {
	        		bw.write(tabela.get(i).get(j));
	        		if(j != tabela.get(i).size()-1) {
	        			bw.write(",");
	        		}
	        	}
	            bw.newLine();
	        }
	        bw.close();
	        fw.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
