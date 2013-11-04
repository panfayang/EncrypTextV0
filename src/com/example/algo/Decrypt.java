package com.example.algo;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Fayang Pan
 * Oct 22, 2013
 * CCTry
 */
public class Decrypt {
	public Decrypt(){}
	
	public char decryptChar(char c, int twist){
		int temp = (int)c;
		return (char)((temp-32-twist+95)%95+32);
	}
	
	public String decryptString(String string, int twist){
		StringBuilder sb = new StringBuilder();
		for (char i: string.toCharArray()){
			if (((int)i)<127 && ((int)i) > 31){
				sb.append(decryptChar(i,twist));
			}
			else{
				sb.append(i);
			}
		}
		return sb.toString();
	}
	
	public String decrypt(String string, ArrayList<Key> keys){
		StringBuilder sb = new StringBuilder();
		for (char i: string.toCharArray()){
			sb.append(i);
		}
		for (int i = 0; i<keys.size(); i++){
			int start = keys.get(i).getStart();
			int twist = keys.get(i).getTwist();
			if (start<sb.length()){
				String sub = string.substring(start);
				sb.replace(start, sb.length(), this.decryptString(sub, twist)); 					
				}
			}
		
		return sb.toString();
	}

}
