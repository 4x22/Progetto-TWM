import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Pannello extends Frame { 
	static JTextArea testoTappa, segnaTappe;
	static JButton[] bottoniTappeRaggiungibili;
	static JPanel pannello;
	public Pannello (String titolo) { 
		super(titolo); 
		pannello = new JPanel(); 
		add(pannello, BorderLayout.SOUTH); 
		testoTappa = new JTextArea(Gioco.tappe[0].getTesto(),5,20); 
		testoTappa.setEditable(false); 
		add(testoTappa, BorderLayout.CENTER); 
		segnaTappe = new JTextArea("Fai la tua scelta!",5,20); 
		add(segnaTappe, BorderLayout.EAST);
		mettiPulsanti(Gioco.tappe[0]);
		setSize(640,360);
		setVisible(true); 
		addWindowListener( new WindowAdapter() { 
		 public void windowClosing(WindowEvent e){ 
		 System.out.println("Window Closing"); 
		 System.exit(0);  }});

	}  


	//Metodo per sovrascrivere l'attuale testo della tappa con quello della tappa selezionata con la variabile numeroTappa.
	public void cambiaTestoTappa(String testo){
		testoTappa.setText(testo);
	}

	//Metodo ascoltatore del bottone che richiama il metodo per effettuare il cambio della tappa.
	class Ascoltatore implements ActionListener { 
		public void actionPerformed(ActionEvent evento) { 
			String testoBottone = evento.getActionCommand();
			cambiaTappa(Gioco.tappe[Integer.parseInt(testoBottone)]);
			//"Appende" del testo riguardo la tappa selezionata alla JTextArea segnaTappe.
			segnaTappe.append("\nHai selezionato la tappa: " + testoBottone);
			rimuoviBottoni();
			mettiPulsanti(Gioco.tappe[Integer.parseInt(testoBottone)]);
		} 
	} 

	//Cambia il testo della tappa
	public void cambiaTappa(Tappa tappa){
		cambiaTestoTappa(tappa.getTesto());
	}
	//Elinare i pulsanti attuali
	public void rimuoviBottoni(){
		for (int i = 0; i < bottoniTappeRaggiungibili.length; i++) {
			pannello.remove(bottoniTappeRaggiungibili[i]);
		}
	}

	//Aggiornare i pulsanti
	public void mettiPulsanti(Tappa tappa){
		int[] tappeRaggiungibili = tappa.getTappeRaggiungibili();
		bottoniTappeRaggiungibili = new JButton[tappeRaggiungibili.length];
		for (int i = 0; i < bottoniTappeRaggiungibili.length; i++) {
			bottoniTappeRaggiungibili[i] = new JButton(Integer.toString(tappeRaggiungibili[i]));
			pannello.add(bottoniTappeRaggiungibili[i]);
			bottoniTappeRaggiungibili[i].addActionListener(new Ascoltatore());
		}
		pannello.validate();
	}
} 