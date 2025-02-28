package view;

import javax.swing.JOptionPane;

import controller.KillController;

public class Main {
	public static void main(String[] args) {
		KillController controller = new KillController();
		
		int select = 0;
		
		while(select != 9) {
			
			controller.listaProcessos();
			
			select = Integer.parseInt(JOptionPane.showInputDialog("1 - Matar PID\n2 - Matar Nome\n9 - Finalizar Programa"));
			switch (select) {
			case 1:
				controller.mataPid(JOptionPane.showInputDialog("Insira o PID do processo:"));
				break;
			case 2:
				controller.mataNome(JOptionPane.showInputDialog("Insira o nome do processo:"));
				break;
			case 9:
				System.out.println("\nPrograma Finalizado");
				break;

			default:
				break;
			}
		}
	}
}
