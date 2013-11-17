/**
 * @author Fayang Pan
 * Oct 22, 2013
 * 
 * Test class for algo
 */

package com.example.algo;
import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<String> testStrings = new ArrayList<String>();
		StringBuilder sbTest = new StringBuilder();
		for (int i = 32;i<127;i++){
			sbTest.append((char)i);
		}
		testStrings.add(sbTest.toString());
//		testStrings.add('e');
//		testStrings.add(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~");
//		String tempKey = "0,31|28,10|90,15|62,55|99,65|63,34|49,88|4,62|18,16|41,34";
//		KeyParser keyparser = new KeyParser();
//		System.out.println(keyparser.parseToKey(tempKey).get(9).toString()+"what???");
		ArrayList<Key> testKey = new ArrayList<Key>();
		for (int start = 0; start<10; start++){
			for (int twist = 0; twist<95; twist++){
				testKey.add(new Key(start,twist));
			}
		}
		
		ArrayList<Key> testKey2 = new ArrayList<Key>();
		testKey2.add(new Key(18,6));
	
		Encrypt testEn = new Encrypt();
		Decrypt testDe = new Decrypt();
		for (int testChar= 32; testChar<127; testChar++){
			for (int twist = 1; twist<95; twist++){
			
				char inputText = (char)testChar;
				char encryptedChar = testEn.encryptChar((char)testChar, twist);
				
				char inputText2 = encryptedChar;
				char decryptedChar = testDe.decryptChar(encryptedChar,twist);
//				System.out.print("\n");
				if (inputText != decryptedChar || encryptedChar != inputText2){
					System.out.println(Character.toString(inputText) + Character.toString(encryptedChar) + Character.toString(inputText2) + Character.toString(decryptedChar) + Integer.toString(twist));
					
				}
				
			}
			
		}
		System.out.println("Test for individual characters has passed");
		
		for (String testString: testStrings){
			for (int twist = 0; twist<95; twist++){
				String encryptedText = testEn.encryptString(testString, twist);
				String decryptedText = testDe.decryptString(encryptedText, twist);
				if (!testString.equals(decryptedText)){
					System.out.println(testString  + "\n" + decryptedText + "\n" +Integer.toString(twist));
				}
			}
		}
		System.out.println("Test for strings has passed");
		
		for (String testString: testStrings){
			String encryptedText = testEn.encrypt(testString, testKey2);
			String decryptedText = testDe.decrypt(encryptedText, testKey2);
			System.out.println(testString  + "\n" + encryptedText);
			if (!testString.equals(decryptedText)){
//				System.out.println(testString  + "\n" + decryptedText);
			}
		}
		System.out.println("Test for multiple keys is done");

	}

}
