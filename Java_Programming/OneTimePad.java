import java.util.Scanner;
import java.lang.Character;
public class OneTimePad {



// So in order to do this the key value is actually how many it shifts over to the right you do, convert to ascii do the shift and convert back
    public static int charToInt(char l) {
		// Complete this method.
		// Convert a character to an integer, for example 'a' -> 0, 'b' -> 1
		// (int)l ==> will equal the ascii

		return(int)l;
		 //char test = l;
		/*switch (l){
			case 'a': return 0;
			case 'b': return 1;
			case 'c': return 2;
			case 'd': return 3;
			case 'e': return 4;
			case 'f': return 5;
			case 'g': return 6;
			case 'h': return 7;
			case 'i': return 8;
			case 'j': return 9;
			case 'k': return 10;
			case 'l': return 11;
			case 'm': return 12;
			case 'n': return 13;
			case 'o': return 14;
			case 'p': return 15;
			case 'q': return 16;
			case 'r': return 17;
			case 's': return 18;
			case 't': return 19;
			case 'u': return 20;
			case 'v': return 21;
			case 'w': return 22;
			case 'x': return 23;
			case 'y': return 24;
			case 'z': return 25;
			case ' ': return 26;
			default: throw new IllegalArgumentException("NOT OKAY");



		}
		//return 0;
	  */
    }

    public static char intToChar(int i) {
		// Complete this method.
		// Should convert an integer to a character, for example 0 -> 'a', b -> '1'
		// it should always return lower case char
    	return(char)i;
		/*switch (i){
			case 0: return 'a';
			case 1: return 'b';
			case 2: return 'c';
			case 3: return 'd';
			case 4: return 'e';
			case 5: return 'f';
			case 6: return 'g';
			case 7: return 'h';
			case 8: return 'i';
			case 9: return 'j';
			case 10: return 'k';
			case 11: return 'l';
			case 12: return 'm';
			case 13: return 'n';
			case 14: return 'o';
			case 15: return 'p';
			case 16: return 'q';
			case 17: return 'r';
			case 18: return 's';
			case 19: return 't';
			case 20: return 'u';
			case 21: return 'v';
			case 22: return 'w';
			case 23: return 'x';
			case 24: return 'y';
			case 25: return 'z';
			case 26: return ' ';
			default: throw new IllegalArgumentException("not right");
		}
		//return ' ';
		*/

    }

    public static boolean isAlpha(char c) {
		// This method may prove useful when you are testing your program.
		return true;
    }

    public static String encipher(String original, String onetimepad) {
    	//int key_key = Integer.parseInt(onetimepad);
    	int to_shift;
    	int ascii_of_original;
    	char to_grab;
    	int acscii_of_changed_original;
    	char returned_letter;
    	String returned_string = "";
    	//Turn string original into a number
    	for (int i = 0; i < original.length(); i++){
    		//convert the string to acii
    		ascii_of_original =  charToInt(original.charAt(i));
    		//convert key at index i to how many we want to shift to the right
    		to_grab = onetimepad.charAt(i);
    		to_shift = Character.getNumericValue(to_grab);
    		acscii_of_changed_original = ascii_of_original + to_shift;
    		returned_letter = intToChar(acscii_of_changed_original);
    		returned_string += String.valueOf(returned_letter);
    	}
    	//System.out.println(key_key);
    	return returned_string;
		// Complete this method.
    }
	
	public static String decipher(String cryptText, String onetimepad) {
		int to_shift;
    	int ascii_of_original;
    	char to_grab;
    	int acscii_of_changed_original;
    	char returned_letter;
    	String returned_string = "";
    	//Turn string original into a number
    	for (int i = 0; i < cryptText.length(); i++){
    		//convert the string to acii
    		ascii_of_original =  charToInt(cryptText.charAt(i));
    		//convert key at index i to how many we want to shift to the right
    		to_grab = onetimepad.charAt(i);
    		to_shift = Character.getNumericValue(to_grab);
    		acscii_of_changed_original = ascii_of_original - to_shift;
    		returned_letter = intToChar(acscii_of_changed_original);
    		returned_string += String.valueOf(returned_letter);
    	}
		return returned_string;
		// Complete this method.
	}


    public static void main(String[] args) {
		/* Modify the main so that it presents the user with a menu:
			1. Enter key
			2. Encrypt
			3. Decrypt
			4. quit
		   Once the key has been entered use it to encrypt or decrypt until the user enters another key.
		*/
		boolean quit = false;
		String key = "";
		String to_encrypt = "";
		String to_decrypt = "";
		int input;
		String ciphertext = "";
		while (quit != true){
			System.out.println(" 1. Enter key \n 2. Encrypt \n 3. Decrpyt \n 4. Quit");
			Scanner sc = new Scanner(System.in);
			input = sc.nextInt();

			//System.out.println("1. Enter key \n 2. Encrypt \n 3. Decrpyt \n 4. Quit");
			//Scanner sc = new Scanner(System.in);
			//int input = sc.nextInt();
			if (input == 1){
				System.out.println("ENTER YOUR KEY HERE: ");
				key = sc.next();
				key += sc.nextLine();
				
				//System.out.println(key);

				//System.out.println("1. Enter key \n 2. Encrypt \n 3. Decrpyt \n 4. Quit");

			}

			else if (input == 2){
				System.out.println("What would you like to encrypt?");
				to_encrypt = sc.next();
				to_encrypt += sc.nextLine();
				//System.out.println(encipher(to_encrypt, key));
				to_decrypt = encipher(to_encrypt, key);
				//System.out.println(to_decrypt);


			}

			else if (input == 3){
				System.out.println("Decryption key??");
				key = sc.next();
				key += sc.nextLine();
				ciphertext = decipher(to_decrypt, key);
				System.out.println(ciphertext);


			}
			else if (input == 4){
				quit = true;
			}
			else System.out.println("NOT VALID INPUT");


		}	
		//String ciphertext = encipher("this is my message", "this is my secret key");
		//System.out.print(ciphertext);
    }

}