import java.util.*;
import java.util.function.BiFunction;
import java.util.Random;
import java.util.stream.*;

public class FilterList {

	public static ArrayList<Integer> filter(ArrayList<Integer> ls, String op, int val, int pos) {
		if (ls == null || op == null || ls.size() <= pos)
			return ls;
		else {
			boolean test = false;
			int elem = ls.get(pos);
			if (op.equals("==")) test = elem == val;
			else  if (op.equals("<=")) test = elem <= val;
			else  if (op.equals(">=")) test = elem >= val;
			else  if (op.equals("!=")) test = elem != val;
			else  if (op.equals("<")) test = elem < val;
			else  if (op.equals(">")) test = elem > val;
			if (!test) {
				ls.remove(pos);
				return filter(ls,op,val,pos);
			} else return filter(ls, op,val,pos+1);
		}
	}

	public static ArrayList<Integer> filter(ArrayList<Integer> ls, String op, int val) {
		return filter(ls,op,val,0);
	}

	public static void main(String args[]) {
		String ops[] = new String[]{"==",">=","<=","!=","<",">"};
		ArrayList<BiFunction<Integer,Integer,Boolean>> opsReal = new ArrayList<>();
		double count = 0.0;
		opsReal.add((x,y)->x==y);
		opsReal.add((x,y)->x>=y);
		opsReal.add((x,y)->x<=y);
		opsReal.add((x,y)->x!=y);
		opsReal.add((x,y)->x<y);
		opsReal.add((x,y)->x>y);
		Random r = new Random();
		double max = 10000000.0;
		for (int i = 0; i<max; i++) {
			int pos = r.nextInt(ops.length);
			String op = ops[pos];
			BiFunction<Integer,Integer,Boolean> fun = opsReal.get(pos);
			int size = r.nextInt(3);
			ArrayList<Integer> ls = new ArrayList<>();
			int val = r.nextInt(21);
			for (int j = 0; j<size; j++) {
				ls.add(r.nextInt(20));
			}
			HashSet<Integer> expected = ls.stream().filter(y -> fun.apply(y, val)).collect(Collectors.toCollection(HashSet::new));
			HashSet<Integer> result = new HashSet<>(filter(ls,op,val));
			if (expected.equals(result)) {
				count++;
			}
		}
		System.out.println(count/max*30.0);
	}

}
