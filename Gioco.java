import java.io.*;

class Gioco {
    public static Tappa[] tappe;
	public static void main(String[] args) throws Exception{
        System.out.println("Inserisci il nome del file da leggere:");
		try {
            //Inizializzo fileReader e bufferedReader per leggere il file testuale, di cui il nome è dato in ingresso
        	FileReader fileReader = new FileReader(Leggi.unoString());
		    BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Inizializzo le variabili relative alle informazioni generali delle tappe 
            int totaleTappe, numeroTappa, numeroRighe;
            int[] tappeRaggiungibili;
            String riga = "";

            //Dichiaro l'array di tappe con le dimensioni adatte a contenere tutte le tappe
            totaleTappe = Integer.parseInt(bufferedReader.readLine());
            tappe = new Tappa[totaleTappe];

            //Utilizzo un ciclo di tipo FOR per riempire l'array di tappe
            for(int i = 0; i < tappe.length; i++) {

                //Inizializzo delle variabili conteneti informazioni sulla singola tappa e utilizzando il parser otteniamo le informazioni dal file testuale
            	numeroTappa = Integer.parseInt(bufferedReader.readLine());
                numeroRighe = Integer.parseInt(bufferedReader.readLine());
                for(int j = 0; j < numeroRighe; j++) {
                    riga += bufferedReader.readLine() + "\n";
                }
                numeroRighe = Integer.parseInt(bufferedReader.readLine());
                tappeRaggiungibili = new int[numeroRighe];
                for(int j = 0; j < tappeRaggiungibili.length; j++) {
                    tappeRaggiungibili[j] = Integer.parseInt(bufferedReader.readLine());
                }

                //E' finalmente dichiarata ogni singola tappa
                tappe[i] = new Tappa(numeroTappa, riga, tappeRaggiungibili);

                riga = "";
            }
            //Chiusura del fileReader
            fileReader.close();

            for(int i = 0; i < tappe.length; i++) {
                System.out.println(tappe[i]);
            }
            Pannello pannello = new Pannello("Avventura");
            } catch (IOException e) {
            System.out.println("Errore in lettura del file");
        }

	}
}