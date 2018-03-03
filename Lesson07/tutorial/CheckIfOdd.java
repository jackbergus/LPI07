/*Come risolvere un 


Caso Base: Numero è composto da una cifra;
-> Il problema si riduce a capire se il numero è dispari o meno.

Caso induttivo: Il numero è composto da più di una cifra;
Posso applicare l'induzione scandendo il numero da destra o da sinistra.

1) Mi chiedo se la prima meno significativa è dispari. Se è dispari, incremento il contatore e proseguo con la successiva 
    --> Si riduce a risolvere il problema "Il numero è dispari" effettuando successive divisioni per 10, tramite le quali rimuovo la cifra meno significativa

2) Mi chiedo se l'ultima cifra è dispari. Se è dispari, incremento il contatore. Rimuovo questa cifra, e proseguo induttivamente sulla parte che rimane.


A questo punto, tale contatore si può implementare in due modi:
   a) Combino il valore valutato nel caso corrente con quello valutato nei casi successivi. Implementazione più immediata.
   b) Utilizzare un accumulatore tramite il quale si effettua la "tail recursion" (più efficiente)
      https://en.wikipedia.org/wiki/Tail_call*/

import java.util.Random;

public class CheckIfOdd {

	public static int countOdd(int integer)  {
		if (integer < 0) return countOdd(-integer);
		else {
			if ((0 <= integer)&&(integer <=9)) 
				return (integer % 2 == 0 ? 0 : 1);
			else 
				return (integer % 2 == 0 ? 0 : 1)+countOdd(integer/10);
		}
	}

	public static void main(String args[]) {
		int maxIntLen = 7;
		Random r = new Random();
		for (int i = 0; i<100000; i++) {
			int oddDigit = 0;
			int num = 0;
			for (int log = 0; log < maxIntLen; log++) {
				int digit = r.nextInt(10);
				oddDigit += (digit % 2 == 0 ? 0 : 1);
				num = (num + digit)*10;
			}
			num = num / 10;
			if (r.nextBoolean()) num = -num;
			if (countOdd(num) != oddDigit)
			System.out.println(countOdd(num) == oddDigit ? (num + " has "+ oddDigit +" odd digits!") : "Error");
		}
	}		

}
