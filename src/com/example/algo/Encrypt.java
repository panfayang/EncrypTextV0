/**
 * @author Fayang Pan
 * Oct 22, 2013
 * 
 * This class handles encryption request, takes in a String and an ArrayList of keys, 
 * and output is the encrypted String.
 */

package com.example.algo;
import java.util.ArrayList;


public class Encrypt {
	
	public Encrypt(){}
	
	public char encryptChar(char c, int twist){
		int temp = (int)c;
		return (char)((temp-32+twist)%95+32);
	}
	
	
	public String encryptString(String string, int twist){
		StringBuilder sb = new StringBuilder();
		for (char i: string.toCharArray()){
			if (((int)i)<127 && ((int)i) > 31){
				sb.append(encryptChar(i,twist));
			}
			else{
				sb.append(i);
			}
		}
		return sb.toString();
	}
	
	
	public String encrypt(String string, ArrayList<Key> keys){
		StringBuilder sb = new StringBuilder();
		for (char i: string.toCharArray()){
			sb.append(i);
		}
		for (int i = 0; i<keys.size(); i++){
			int start = keys.get(i).getStart();
			int twist = keys.get(i).getTwist();
			if (start<sb.length()){
				String sub = string.substring(start);
				sb.replace(start, sb.length(), this.encryptString(sub, twist)); 					
				}
//			System.out.println(sb.toString());
			}
		
		return sb.toString();
	}
	

}
