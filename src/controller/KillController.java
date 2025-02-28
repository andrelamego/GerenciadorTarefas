package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class KillController {
	
	public KillController(){
		super();
	}
	
	private String os() {
		String nome = System.getProperty("os.name");
		return nome;
	}
	
	public void listaProcessos() {
		String nomeOs = os();
		String proc = "";
		
		if(nomeOs.contains("Windows")) {
			proc = "TASKLIST /FO TABLE";
		}
		else {
			proc = "ps -ef";
		}
		String[] splitProc = proc.split(" ");
		
		try {
			
			Process p = Runtime.getRuntime().exec(splitProc);
			
			InputStream stream = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			
			String linha = buffer.readLine();
			
			while (linha != null) {				
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			reader.close();
			stream.close();
			
		} catch (Exception e) {
			String erro = e.getMessage();
			System.err.println(erro);
		}
		
	}
	
	public void mataPid(String valor) {
		String nomeOs = os();
		String proc = "";
		
		if(nomeOs.contains("Windows")) {
			proc = "TASKKILL /PID";
		}
		else {
			proc = "kill -9";
		}
		String[] splitProc = proc.split(" ");
		
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		try {
			pid = Integer.parseInt(valor);			
			
			buffer.append(proc);
			buffer.append(" ");
			buffer.append(pid);
			
			chamaProcesso(buffer.toString());
	
		} catch (NumberFormatException e) {
			String erro = e.getMessage();
			System.err.println(erro);
			JOptionPane.showMessageDialog(null, "Insira um PID existente");
		}
		
		
	}
	
	public void mataNome(String valor) {
		String nomeOs = os();
		String proc = "";
		
		if(nomeOs.contains("Windows")) {
			proc = "TASKKILL /IM";
		}
		else {
			proc = "pkill -f";
		}		
		StringBuffer buffer = new StringBuffer();
		
		try {		
			
			buffer.append(proc);
			buffer.append(" ");
			buffer.append(valor);		
			
			chamaProcesso(buffer.toString());
			
		} catch (IllegalArgumentException e) {
			String erro = e.getMessage();
			System.err.println(erro);
			JOptionPane.showMessageDialog(null, "Insira um processo existente");
		}
	}
	
	private void chamaProcesso(String proc) {
		String[] vetCmd = proc.split(" ");
		
		try {
			Runtime.getRuntime().exec(vetCmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
