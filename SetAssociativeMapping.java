package CO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
//Mansi Singhal - 2019370
public class SetAssociativeMapping {
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
	public static int index(String set,HashMap<String, Integer> order, String tag,int help) {
		String ans = set;
		int n =order.get(tag+set);
		String temp = Integer.toBinaryString(n);
		while (temp.length() < help) {   
	        temp = "0" + temp;
	  }
		String s = ans + temp;
		int aa = Integer.parseInt(s, 2);
		return aa;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("PLease provide the following:");
		System.out.println("1)The bits of the physical address N");
		System.out.println("2)The number of cache lines CL");
		System.out.println("3)The block size B");
		System.out.println("4)The number of ways for set associative mapping -n");
		int N = in.nextInt();
		int CL = in.nextInt();
		int B = in.nextInt(); // block size 
		int n = in.nextInt();
		int block_bits = PowerofTwo(B);
		int BI = N- block_bits; 
		int no_sets = CL/n;
		int set_bits = PowerofTwo(no_sets);
		int tag_bits = BI - set_bits;
		int help = PowerofTwo(CL)-set_bits;
		if (PowerofTwo(B)== -1 || PowerofTwo(n)== -1|| PowerofTwo(CL)== -1) {
			System.out.println("Invalid input dimensions for Cache");
		}
		HashMap<String,ArrayList<String>> set = new HashMap<>();
		String[][] cache = new String[CL][B]; //contains the data of the loaded block
		HashMap<String, Integer> order_store  = new HashMap<>(); //which block is stored at which location
		HashMap<String,String[]> blocks = new HashMap<>();
		String address;
		address = in.nextLine();
		while (address != null) {
			address = in.nextLine();
			String[] fun = address.split(" ");
			String ad1 = fun[0];
			String tag_string = ad1.substring(0, tag_bits);
			String set_string = ad1.substring(tag_bits, BI);
			String block = tag_string +set_string;
			String wordn= ad1.substring(BI);
			if (fun.length==1) {
				if (!set.containsKey(set_string)) {
					set.put(set_string, new ArrayList<String>());
				}
				if (set.get(set_string).contains(tag_string)) {
					System.out.println("This is a hit");
					int ind = index(set_string,order_store,tag_string,help);
					cache[ind] = blocks.get(block);
				}
				else {
					System.out.println("This is a miss");
					if (set.get(set_string).size()< n) {
						set.get(set_string).add(tag_string);
						order_store.put(block,set.get(set_string).indexOf(tag_string));
						if (!blocks.containsKey(block)) {
							blocks.put(block, new String[B]);}
						int ind = index(set_string,order_store,tag_string,help);
						cache[ind] = blocks.get(block);
					}
					else { 
						String first = set.get(set_string).get(0);
						set.get(set_string).remove(0);
						String blockr = first + set_string;
						int position = order_store.get(blockr);
						order_store.remove(blockr);
						set.get(set_string).add(tag_string);
						order_store.put(block, position);
						if (!blocks.containsKey(block)) {
							blocks.put(block, new String[B]);}
						int ind = index(set_string,order_store,tag_string,help);
						cache[ind] = blocks.get(block);
					}
					
					
				}
				
			}
			else if (fun.length==2){
				String data = fun[1];
				if (!set.containsKey(set_string)) {
					set.put(set_string, new ArrayList<String>());
				}
				if (set.get(set_string).contains(tag_string)) {
					System.out.println("This is a hit");
					blocks.get(tag_string)[Integer.parseInt(wordn, 2)] = data;
					int ind = index(set_string,order_store,tag_string,help);
					cache[ind] = blocks.get(block);
				}
				else {
					System.out.println("This is a miss");
					if (set.get(set_string).size()< n) {
						set.get(set_string).add(tag_string);
						order_store.put(block,set.get(set_string).indexOf(tag_string));
						if (!blocks.containsKey(block)) {
							blocks.put(block, new String[B]);}
						   blocks.get(block)[Integer.parseInt(wordn, 2)] = data;
						   int ind = index(set_string,order_store,tag_string,help);
							cache[ind] = blocks.get(block);
					}
					else { 
						String first = set.get(set_string).get(0);
						set.get(set_string).remove(0);
						String blockr = first + set_string;
						int position = order_store.get(blockr);
						order_store.remove(blockr);
						set.get(set_string).add(tag_string);
						order_store.put(block, position);
						if (!blocks.containsKey(block)) {
							blocks.put(block, new String[B]);}
							blocks.get(block)[Integer.parseInt(wordn, 2)] = data;
							int ind = index(set_string,order_store,tag_string,help);
							cache[ind] = blocks.get(block);					}
					
				}
			}
			else {
				System.out.println("Invalid Statement error");
			}
			printCache(cache,PowerofTwo(CL));
			}

	}

}
