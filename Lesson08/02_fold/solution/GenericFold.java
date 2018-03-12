import java.util.*;
import java.util.function.*;

public class GenericFold {

	private static <T> T fold(ArrayList<T> list, BiFunction<T,T,T> fun, T acc, int pos) {
		return (pos >= list.size()) ? acc : fold(list,fun,fun.apply(list.get(pos),acc),pos+1);
	}

	public static <T> T fold(ArrayList<T> list, BiFunction<T,T,T> fun, T acc) {
		return fold(list,fun,acc,0);
	}

	public static void main(String[] args) {
		// Space-delimited string append
		ArrayList<String> testing = new ArrayList<>();
		testing.add("test");
		testing.add("di");
		testing.add("verifica");
		System.out.println("Space-delimited string append: "+fold(testing,(x,y)->x+" "+y,""));

		// Comma delimited space append
		System.out.println("Comma delimited space append: " + fold(testing,(x,y)->x+","+y,""));

		// Summation
		ArrayList<Integer> testingInt = new ArrayList<>();
		testingInt.add(1);
		testingInt.add(23);
		testingInt.add(45);
		System.out.println("Summation: " + fold(testingInt,(x,y)->x+y,0));

		// Product
		System.out.println("Product: " + fold(testingInt,(x,y)->x*y,1));

		// Disjunction
		ArrayList<Boolean> testingBool = new ArrayList<>();
		testingBool.add(true);
		testingBool.add(false);
		testingBool.add(true);
		System.out.println("Disjunction: " + fold(testingBool,(x,y)->x||y,false));

		// Conjunction
		System.out.println("Conjunction: " + fold(testingBool,(x,y)->x&&y,true));
	}
		

}
