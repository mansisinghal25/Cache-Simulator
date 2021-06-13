    package CO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
//Mansi Singhal - 2019370
public class AssociativeMapping {
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
		int BI = N- block_bits; 
		if (PowerofTwo(B)== -1 || PowerofTwo(CL)== -1) {
			System.out.println("Invalid input dimensions for Cache");
		}
		ArrayList<String> tags = new ArrayList<String>();
		
		String[][] cache = new String[CL][B]; //contains the data of the loaded block
		HashMap<String, Integer> order_store  = new HashMap<>(); //which block is stored at which location
		HashMap<String,String[]> blocks = new HashMap<>();
		String address;
		address = in.nextLine();
		while (address != null) {
			address = in.nextLine();
			String[] fun = address.split(" ");
			String ad1 = fun[0];
			String tag_string = ad1.substring(0, BI); //tag string and block will be same in this case 
			String wordn= ad1.substring(BI);
			if (fun.length==1) {
				if (tags.contains(tag_string)) {
					System.out.println("This is a hit");
					
				}
				else {
					System.out.println("This is a miss");
					if (tags.size()<CL) {
						tags.add(tag_string);
						order_store.put(tag_string, tags.indexOf(tag_string));
						if (!blocks.containsKey(tag_string)) {
							blocks.put(tag_string, new String[B]);}
							cache[order_store.get(tag_string)] = blocks.get(tag_string);
					}
					else {
						String first = tags.get(0);
						tags.remove(0);
						int position = order_store.get(first);
						order_store.remove(first);
						tags.add(tag_string);
						order_store.put(tag_string, position);
						order_store.put(tag_string, 0);
						if (!blocks.containsKey(tag_string)) {
							blocks.put(tag_string, new String[B]);}
							cache[order_store.get(tag_string)] = blocks.get(tag_string);
					}
					
				}
			}
			else if (fun.length ==2) {
				String data = fun[1];
				if (tags.contains(tag_string)) {
					System.out.println("This is a hit");
					blocks.get(tag_string)[Integer.parseInt(wordn, 2)] = data;
					cache[order_store.get(tag_string)] = blocks.get(tag_string);
					
				}
				else {
					System.out.println("This is a miss");
					if (tags.size()<CL) {
						tags.add(tag_string);
						order_store.put(tag_string, tags.indexOf(tag_string));
						if (!blocks.containsKey(tag_string)) {
						blocks.put(tag_string, new String[B]);}
						blocks.get(tag_string)[Integer.parseInt(wordn, 2)] = data;
						cache[order_store.get(tag_string)] = blocks.get(tag_string);
					}
					else {
						String first = tags.get(0);
						tags.remove(0);
						int position = order_store.get(first);
						order_store.remove(first);
						tags.add(tag_string);
						order_store.put(tag_string, position);
						if (!blocks.containsKey(tag_string)) {
							blocks.put(tag_string, new String[B]);}
							blocks.get(tag_string)[Integer.parseInt(wordn, 2)] = data;
							cache[order_store.get(tag_string)] = blocks.get(tag_string);
					}
					
				}
			}
			else {
				System.out.println("Invalid Statement error");
			}
			printCache(cache,PowerofTwo(CL));
			}
		
		
		
		}

}

