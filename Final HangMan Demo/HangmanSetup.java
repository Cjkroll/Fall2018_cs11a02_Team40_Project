import java.util.Scanner;
import java.io.File;

/**
@author Caleb Kroll
@author Vy Tran
@author Qian Sun
@author Tiana Murrieta

Setup hangman.
*/

public class HangmanSetup{
  public static void main(String[] args) {

  }

  /**
  Loads contents of file passed into it.

  Method is used to create array holding words for
  use in games of hangman.

  Method is designed assuming each line of file
  has one piece of information (word, definition, size, etc.),
  thus the file is read line-by-line.

  @param fileName String naming file to be read.
  @param lineCount int for total lines in file read.
  @return String array where each element holds contents of one line from file read.

  */

  public static String[] loadFile(String fileName, int lineCount){
    String[] fileContents = new String[lineCount];
    try{
      File file = new File(fileName);
      Scanner fileReader = new Scanner(file);
      for(int i = 0; i < lineCount; i++){
        fileContents[i] = fileReader.nextLine();
      }

      fileReader.close();

    } catch(Exception e){
      System.out.println("An invalid file was used to load hangman.");
    }
    return fileContents;
  }

  /**
  A void method notifying user of what this program is.
  */

  public static void greetUser(){
    System.out.println();
    System.out.println("Hello. This is the game of hangman with over 26,000 words to play with!");
  }

  /**
  A void method asking user whether they would like to see rules of the game.

  If user asks to see rules, private method showRules() is called to show rules.

  */

  public static void promptRules(){
    System.out.println();
    System.out.println("Would you like to here the rules?");
    System.out.println();
    System.out.print("Enter 'yes' or 'no': ");
    boolean seeRules = TextIO.getlnBoolean();
    System.out.println();
    if(seeRules == true){
      showRules();
    }
  }

  /**
  Prints rules of hangman to standard output.
  */
  private static void showRules(){
    System.out.println();
    System.out.println("A word will be randomly chosen and its length will be displayed.");
    System.out.println();
    System.out.println("You will enter a letter you think is in the word.");
    System.out.println();
    System.out.println("If you choose a letter in the word, that will be displayed.");
    System.out.println();
    System.out.println("If you choose incorrectly, you'll lose a life.");
    System.out.println();
    System.out.println("To win, correctly guess all the letters in the word before you run out of lives.");
    System.out.println();
    System.out.println("Run out of lives and it's game over.");
    System.out.println();
    System.out.println("Ready to play?");
  }
}
