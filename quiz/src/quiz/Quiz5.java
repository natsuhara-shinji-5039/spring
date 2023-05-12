package quiz;

public class Quiz5 {

	public static void main(String[] args) {
		int a = 10;
		int total = 0;
		
		for(int i = 1; i <= a; i++) {
			total += i;
			System.out.println(i);
		}
		System.out.println("合計:" + total);
	}

}
