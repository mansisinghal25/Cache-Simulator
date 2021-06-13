package CO;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
//Mansi Singhal - 2019370
// This codes implements Cache using Direct Mapping Technique
public class DirectMapping {
	public static int  PowerofTwo(int n) 
    { int ans=0;
        while (n != 1) { 
            if (n % 2 != 0) {
                return -1;}
            ans +=1;
            n = n / 2; 
        } 
        return ans; 
    } 
	public static void printCache(String[][] cache,int CI) {
		for (int i = 0; i < cache.length; i++) {
			String binString = Integer.toBinaryString(i);
			while (binString.length() < CI) {   
		        binString = "0" + binString;
		  }
			System.out.println("Cache line-" + binString +": " + Arrays.toString(cache[i]));
	}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("PLease provide the following:");
		System.out.println("1)The bits of the physical address N");
		System.out.println("2)The number of cache lines CL");
		System.out.println("3)The block size B");
		int N = in.nextInt();
		int CL = in.nextInt();
		int B = in.nextInt(); // block size 
		int S = CL*B;
		int block_bits = PowerofTwo(B);
		int CA_bits = PowerofTwo(S); 
		int BI = N- block_bits;
		int CI_bits = CA_bits - block_bits;
		int tag_bits = BI - CI_bits;
		if (PowerofTwo(B)== -1 || PowerofTwo(CL)== -1) {
			System.out.println("Invalid input dimensions for Cache"); //error handling
		}
		String[] tags = new String[CL]; //contains the tag lines
		String[][] cache = new String[CL][B]; //contains the data of the loaded blocks
		HashMap<String,String[]> blocks = new HashMap<>();
		for (int i=0; i< tags.length;i++) {
			tags[i] = "empty";
		}
		String address;
		address = in.nextLine();
		while (address != null) {
			address = in.nextLine();
			String[] fun = address.split(" ");
			String ad1 = fun[0];
			String CI = ad1.substring(tag_bits, tag_bits + CI_bits);
			String tag_string = ad1.substring(0, tag_bits);
			String wordn= ad1.substring(BI);
			String block = tag_string + CI ; //the wanted block	
			if (fun.length==1) {
				if (tag_string.contentEquals(tags[Integer.parseInt(CI, 2)])) {
					System.out.println("This is a hit");
				}
				else if (tags[Integer.parseInt(CI, 2)].contentEquals("empty")) {
					System.out.println("This is a miss");
					System.out.println("The cache line-" + Integer.parseInt(CI, 2) + " is empty and will now be loaded with the required block");
					tags[Integer.parseInt(CI, 2)] = tag_string;
					if (!blocks.containsKey(block)) {
						blocks.put(block, new String[B]);}
						cache[Integer.parseInt(CI, 2)] = blocks.get(block);
				}
				else {
					System.out.println("This is a miss");
					System.out.println("The cache line-"+ Integer.parseInt(CI, 2) +"will now be loaded with the required block"  );
					tags[Integer.parseInt(CI, 2)] = tag_string;
					if (!blocks.containsKey(block)) {
						blocks.put(block, new String[B]);}
						cache[Integer.parseInt(CI, 2)] = blocks.get(block);
				
				}
			}
			else if (fun.length==2){
				String data = fun[1];
				if (tag_string.contentEquals(tags[Integer.parseInt(CI, 2)])) {
					System.out.println("This is a hit");
					blocks.get(block)[Integer.parseInt(wordn, 2)] = data;
					cache[Integer.parseInt(CI, 2)] = blocks.get(block);
				}
				else if (tags[Integer.parseInt(CI, 2)].contentEquals("empty")) {
					System.out.println("This is a miss");
					System.out.println("The cache line-" + Integer.parseInt(CI, 2) + " is empty and will now be loaded with the required block");
					tags[Integer.parseInt(CI, 2)] = tag_string;
					if (!blocks.containsKey(block)) {
						blocks.put(block, new String[B]);}
						blocks.get(block)[Integer.parseInt(wordn, 2)] = data;
						cache[Integer.parseInt(CI, 2)] = blocks.get(block);
				}
				else  {
					System.out.println("This is a miss");
					System.out.println("The cache line-"+ Integer.parseInt(CI, 2) +"will now be loaded with the required block"  );
					tags[Integer.parseInt(CI, 2)] = tag_string;
					if (!blocks.containsKey(block)) {
						blocks.put(block, new String[B]);}
						blocks.get(block)[Integer.parseInt(wordn, 2)] = data;
						cache[Integer.parseInt(CI, 2)] = blocks.get(block);
				}
			}
			else {
				System.out.println("Invalid Statement error"); //error handling
			}
			printCache(cache,CI_bits);
		}
		

	}

}
