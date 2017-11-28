import java.util.Arrays;
import java.util.Scanner;

public class BufferCircolare {

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		System.out.println("Fornire la dimensione iniziale del buffer (maggiore di 0) = ");
		int n = Integer.valueOf(s.nextLine());
		if (n > 0) {
			String buffer[] = new String[n];
			// Inizialmente il primo elemento è anche il secondo e, di conseguenza, ciò significa che il buffer è vuoto.
			// Osserva però che questa situazione potrebbe succedere anche quando il buffer è pieno
			int indexFirst = 0;
			int indexLast = 0;
			/*
			 *  Count is required because there are two possible cases when i == j, that is either the buffer is
			 *  full, or it is empty. Hereby, I must distinguish the two states by using a variable counting the elements
			 */
			int count = 0;

			String command;
			while (!(command = s.nextLine()).equals("QUIT")) {
				// creare un comando che, dato "ADD s", aggiunge s al buffer solo se è presente ancora del posto
				if (command.startsWith("ADD ")) {
					if (count<n) {
						// Inserisco l'elemento sempre nell'ultima posizione, che è j
						buffer[indexLast] = command.substring(3).trim();
						indexLast = (indexLast+1) % n;
						count++;
					} else {
						System.err.println("ERROR: the buffer is empty");
					}
				} if (command.startsWith("ADDOROVERWRITE ")) {
					// creare un comando che, dato "ADDOROVERWRITE gg", aggunge s al buffer e, se questo è pieno,
					// semplicemente sovrascrive la prima stringa con quella corrente, ma non incrementa nè decrementa
					// la dimensione dell'elemento

					// Ottengo la sottostringa da inserire
					buffer[indexLast] = command.substring(14).trim();


					indexLast = (indexLast+1) % n;
					if (count == n) {
						// Sposto entrambi i cursori, perché la prima stringa è sovrascritta dall'ultima, e l'ultimo
						// elemento viene cancellato
						indexFirst = (indexFirst+1) % n;
					} else {
						// Aggiungo l'elemento solamente se il buffer non è pieno
						count++;
					}
				} else if (command.equals("REMOVE")) {
					// Rimuovo un elemento dal buffer, se questo non è vuoto. Prima di rimuovere l'elemento lo stampo
					if (count != 0) {
						System.out.println(buffer[indexFirst]);
						// Sposto avanti il cursore che punta al primo elemento di una unità
						indexFirst = (indexFirst+1) % n;
						count--;
					} else {
						System.err.println("ERROR: the buffer is empty");
					}
				} else if (command.equals("PRINT")) {
					int cur = indexFirst;
					/*
					Please note that we cannot check the statement with while (i!=j) because, otherwise, it would
					immediately stops when the buffer is full
					 */
					for (int k = 0; k<count; k++) {
						System.out.println("\t"+buffer[cur]);
						cur = (cur+1)%n;
					}
				}
			}
		}
	}

}