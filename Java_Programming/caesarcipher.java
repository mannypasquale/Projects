import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
public class othertimepad {


    public static int charToInt(char l) {
		// Complete this method.
		// Convert a character to an integer, for example 'a' -> 0, 'b' -> 1
		int number = 0;
		switch (l) {
			case 'a': number = 0;break;
			case 'b': number = 1;break;
			case 'c': number = 2;break;
			case 'd': number = 3;break;
			case 'e': number = 4;break;
			case 'f': number = 5;break;
			case 'g': number = 6;break;
			case 'h': number = 7;break;
			case 'i': number = 8;break;
			case 'j': number = 9;break;
			case 'k': number = 10;break;
			case 'l': number = 11;break;
			case 'm': number = 12;break;
			case 'n': number = 13;break;
			case 'o': number = 14;break;
			case 'p': number = 15;break;
			case 'q': number = 16;break;
			case 'r': number = 17;break;
			case 's': number = 18;break;
			case 't': number = 19;break;
			case 'u': number = 20;break;
			case 'v': number = 21;break;
			case 'w': number = 22;break;
			case 'x': number = 23;break;
			case 'y': number = 24;break;
			case 'z': number = 25;break;
			case ' ': number = 26;break;
		}
	  return number;
    }

    public static char intToChar(int i) {
		// Complete this method.
		// Should convert an integer to a character, for example 0 -> 'a', b -> '1'
		// it should always return lower case char
		char character = 'a';
		int integer = i;
		switch (integer) {
			case 0: character = 'a';break;
			case 1: character = 'b';break;
			case 2: character = 'c';break;
			case 3: character = 'd';break;
			case 4: character = 'e';break;
			case 5: character = 'f';break;
			case 6: character = 'g';break;
			case 7: character = 'h';break;
			case 8: character = 'i';break;
			case 9: character = 'j';break;
			case 10: character = 'k';break;
			case 11: character = 'l';break;
			case 12: character = 'm';break;
			case 13: character = 'n';break;
			case 14: character = 'o';break;
			case 15: character = 'p';break;
			case 16: character = 'q';break;
			case 17: character = 'r';break;
			case 18: character = 's';break;
			case 19: character = 't';break;
			case 20: character = 'u';break;
			case 21: character = 'v';break;
			case 22: character = 'w';break;
			case 23: character = 'x';break;
			case 24: character = 'y';break;
			case 25: character = 'z';break;
			case 26: character = ' ';break;
		}
		return character;
    }

    public static boolean isAlpha(char c) {
		// This method may prove useful when you are testing your program.
		return true;
    }

    public static String encipher(String original, String keyy) {
		// Complete this method.
		boolean done = false;
		String original_text = original;
		String key = keyy;
		String encrypted_text = "";
		while (done == false){
			if (original_text.length() != key.length()){
				if (key.length() < original_text.length()){
					int diff = original_text.length() - key.length();
					for (int index = 0; index < diff; index++){
						key = key + key.charAt(index);

					}
				}
			}
			int count = 0;
			//where we start the conversion
			for (int i=0; i< original_text.length(); i++){
				char char_from_orig = original_text.charAt(i);
				char char_from_key = key.charAt(i);
				int unencryped_letter_asNumber = charToInt(char_from_orig); 
				int to_shift = charToInt(char_from_key);


				if (charToInt(char_from_orig) + to_shift > 26){
					int how_much_greater = (charToInt(char_from_orig) + to_shift) - 26;
					unencryped_letter_asNumber = 0;
					to_shift = how_much_greater;

					encrypted_text =  encrypted_text + intToChar(unencryped_letter_asNumber + to_shift - 1);
				}
				else{
					encrypted_text = encrypted_text + intToChar(unencryped_letter_asNumber + to_shift);
				}

			}
			done = true;

		}
		return encrypted_text;
    }
	
	public static String decipher(String cryptText, String keyy) {
		// Complete this method.
		boolean done = false;
		String crypted_text = cryptText;
		String key = keyy;
		String decrypted_text = "";
		while (done == false){
			if (crypted_text.length() != key.length()){
				if (key.length() < crypted_text.length()){
					int diff = crypted_text.length() - key.length();
					for (int index = 0; index < diff; index++){
						key = key + key.charAt(index);

					}
				}
			}
			for (int i = 0; i < crypted_text.length(); i++){
				char char_from_crypt = crypted_text.charAt(i);
				char char_from_key = key.charAt(i);
				int encryped_letter_asNumber = charToInt(char_from_crypt); 
				int to_shift = charToInt(char_from_key);
				if (charToInt(char_from_crypt) - to_shift < 0){
					int how_much_less = Math.abs((charToInt(char_from_crypt) - to_shift));
					encryped_letter_asNumber = 26;
					to_shift = how_much_less;

					decrypted_text =  decrypted_text + intToChar(encryped_letter_asNumber - to_shift + 1);

				}
				else{
					decrypted_text = decrypted_text + intToChar(encryped_letter_asNumber - to_shift);


				}
			}
			done = true;
		}
		return decrypted_text;
	}
	//public static String crack(String cryptText){
		//for (int i = 0; i < )
	//}

    public static void main(String[] args) throws IOException {
		/* Modify the main so that it presents the user with a menu:
			1. Enter key
			2. Encrypt
			3. Decrypt
			4. quit
		   Once the key has been entered use it to encrypt or decrypt until the user enters another key.
		*/
		   boolean quit = false;
		   String file_name = null;
		   String key_file = null;
		   String ciphertext_file = null;
		   String line = null;
		   while (quit != true){
		   		System.out.println(" 1. Vigenere encryption \n 2. Vigenere decryption \n 3. Vigenere cryptanalysis \n 4. Quit");
		   		Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();

				if (input == 1){
					System.out.println("enter your file name: to encrypt:");
					file_name = sc.next();
					file_name += sc.nextLine();
					System.out.println("enter the file name of your key");
					key_file = sc.next();
					key_file += sc.nextLine();
					System.out.println("Enter file name of output file: ");
					ciphertext_file = sc.next();
					ciphertext_file += sc.nextLine();
					
					try{
						BufferedReader br = new BufferedReader(new FileReader(file_name));
						BufferedWriter writer = new BufferedWriter(new FileWriter(ciphertext_file));
						BufferedReader br_key = new BufferedReader(new FileReader(key_file));
						String key = br_key.readLine();
						while ((line = br.readLine()) != null){
							line = encipher(line, key);
							writer.write(line);

						}
						writer.close();

					}catch(Exception e){
						System.out.println("file not found");
					}


				}
				if (input == 2){
					System.out.println("enter your file name: to decrpyt:");
					file_name = sc.next();
					file_name += sc.nextLine();
					System.out.println("enter the file name of your key");
					key_file = sc.next();
					key_file += sc.nextLine();
					System.out.println("Enter file name of output file: ");
					ciphertext_file = sc.next();
					ciphertext_file += sc.nextLine();
					
					try{
						BufferedReader br = new BufferedReader(new FileReader(file_name));
						BufferedWriter writer = new BufferedWriter(new FileWriter(ciphertext_file));
						BufferedReader br_key = new BufferedReader(new FileReader(key_file));
						String key = br_key.readLine();
						while ((line = br.readLine()) != null){
							line = decipher(line, key);
							writer.write(line);

						}
						writer.close();

					}catch(Exception e){
						System.out.println("file not found");

					}

				}
				if (input == 3){
					System.out.println("enter your ciphertext file name: ");
					String ciphertext = sc.next();
					ciphertext += sc.nextLine();

					int[] coincidence_array = new int[20];
					BufferedReader br = new BufferedReader(new FileReader(ciphertext));
					String orignal_super_text = "";
					String ciphertext_string = "";
					boolean first = true;
					while ((ciphertext_string = br.readLine()) != null){
						if (first == true){
							orignal_super_text = orignal_super_text + ciphertext_string;
							first = false;
						}else{
						orignal_super_text = orignal_super_text + " " + ciphertext_string;
						}
					}
					String shifted_string = orignal_super_text;
					//now i have the original ciphertext saved to a original_super_text
					//with that done i now need to take that text move it over one space and then compare
					for (int index = 1; index <= coincidence_array.length; index ++){
						int coincidence = 0;
						shifted_string = shifted_string.substring(1);
						for(int i = 0; i < shifted_string.length(); i++ ){
							if (orignal_super_text.charAt(i) == shifted_string.charAt(i) ){
								coincidence++;
							}
						}
						coincidence_array[index-1] = coincidence;




					}

					int max = coincidence_array[0];
					int coincidence_index = 0;
					for (int i = 1; i < coincidence_array.length; i++){
						if (coincidence_array[i] > max){
							max = coincidence_array[i];
							coincidence_index = i;
						}
					}
					System.out.println("ESTIMATED LENGTH OF KEY: " + (coincidence_index + 1));
					
				}
				if (input == 4){
					quit = true;
				}
			}


		//String ciphertext = encipher("this is my message", "this is my secret key");
		//System.out.print(ciphertext);
    }

}