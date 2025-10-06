import java.text.DecimalFormat;
import java.util.Scanner;

public class TestAss3 {

	public static void main(String[] args) {
		
		int n;
		
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		if(n < 1000 || n > 9999) {
			System.out.print("NO");
		}
		
		DecimalFormat df = new DecimalFormat("0000");
		String c = df.format(n);   // 0009

		System.out.print(c);
	}

}
