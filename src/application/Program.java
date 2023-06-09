package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Produtos;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		List<Produtos> listaDeVendas = new ArrayList<>();
		
		System.out.print("Digite o caminho do arquivo a ser lido: ");
		String caminho = sc.next();
		
		File caminhoArquivo = new File(caminho);
		
		String pasta = caminhoArquivo.getParent();
		
		boolean criarPasta = new File(pasta + "\\out").mkdir();
		
		String novoArquivo = pasta + "\\out\\summary.csv";
		
			
		try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))){
			
			String linha = br.readLine();
			
			while(linha != null) {
				
				String[] itens = linha.split(",");
				String nome = itens[0];
				double preco = Double.parseDouble(itens[1]);
				int quantidade = Integer.parseInt(itens[2]);
				
				listaDeVendas.add(new Produtos(nome, preco, quantidade));
				
				linha = br.readLine();
				
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(novoArquivo))){
				
				for(Produtos vendas: listaDeVendas) {
					
					bw.write(vendas.getName() + ", " + String.format("%.2f", vendas.totalPrice()));
					bw.newLine();
				}
				
				System.out.println("Criado: " + novoArquivo);
				
			}catch(IOException e) {
				
				System.out.println("Erro :" + e.getMessage());
				
			}	
		
		}catch(IOException e){
			
			System.out.println("Erro :" + e.getMessage());
			
		}
		
		sc.close();
		
	}
}
