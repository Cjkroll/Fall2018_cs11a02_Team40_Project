public class PlayHangman{
  public static void main(String[] args) {

  }

  /**
  Method prompting user to enter integer for lives in current hangman game.

  If custom number is desired, private method customDifficulty is called allowing
  custom input by user.

  @return int representing attempts user will have in current hangman game.
  */

  public static int difficultySelection(){
    int lives = 0;
    System.out.println();
    System.out.println("Select a difficulty entering 1-4. Your choices are:");
    System.out.println();
    System.out.println("1. You're an innocent tenderfoot. (9 lives)");
    System.out.println("2. You got a bad lawyer. (6 lives)");
    System.out.println("3. Cripplingly hard. (3 lives)");
    System.out.println("4. Be your own jury. (custom)");
    System.out.println();
    while(lives == 0){
      int selection = TextIO.getlnInt();
      switch(selection){
        case(1): lives = 9; break;
        case(2): lives = 6; break;
        case(3): lives = 3; break;
        case(4): lives = customDifficulty(); break;
        default: System.out.println("Please enter a number between 1 and 4 to choose your difficulty."); break;
      }
    }
    return lives;
  }

  /**
  Allows for custom number of lives to be input by user.

  @return custom int representing attempts user will have in current hangman game.
  */

  private static int customDifficulty(){
    System.out.println();
    System.out.println("Well well. How many lives do you wanna have?");
    while(true){
      int customLives = TextIO.getlnInt();
      if(customLives > 0){
        System.out.println();
        return customLives;
      }
      else{
        System.out.println("Please enter a positive integer.");
      }
    }
  }

  /**
  A method returning a unique index in the range of word array.
  This index is to be used to extract a word from loaded word array
  to be used in a game of hagman.

  Once an index has been selected, that index in a boolean array wordUsed is
  set to true to indicate the word has been used and should not be used again.

  @see HangManDemoCOPY#wordUsed()

  Before an index is selected, the total games played is compared to the total words
  loaded into the hangman program (totalWords). If the games played is divisble by the total words,
  a private method clearWords() is called to reset all elements of wordUsed[] to false.

  @param totalWords an int for total words that have been loaded into the game of hangman.
  */

  public static int getWordIndex(int totalWords){
    if(HangManDemoCOPY.gameCount%totalWords == 0 && HangManDemoCOPY.gameCount != 0){
      clearWords(totalWords);
    }
    while(true){
      int randomIndex = (int)(Math.random()*totalWords);
      if(HangManDemoCOPY.wordUsed[randomIndex] == false){
        HangManDemoCOPY.wordUsed[randomIndex] = true;
        return randomIndex;
      }
    }
  }

/**
A void method that sets all elements of wordsUsed to false.

This is necessary, as in the event that all elements of wordUsed are true,
getWordIndex() will loop infinitely unless this method is called.

@param totalWords an int for total words that have been loaded into the game of hangman.
*/

  private static void clearWords(int totalWords){
    System.out.println("Warning: All words have been used.");
    System.out.println("Game is being reset.");
    for(int i = 0; i < totalWords; i++){
      HangManDemoCOPY.wordUsed[i] = false;
    }
    System.out.println();
    System.out.println("Game has been reset.");
  }


/**
A method that creates hidden version of randomly chosen word.

Hidden word is displayed to user. As user inputs letters in the word,
those letters will be revealed.

@param word the String object for the word user will guess in current hangman game.
@return StringBuilder object which is same length as word but hides letter and can be transformed by user input.
*/
public static StringBuilder buildHiddenString(String word){
  String hiddenWordString = "";
  for(int i = 0; i < word.length(); i++){
    hiddenWordString = hiddenWordString + "*";
  }
  StringBuilder hiddenWordStringBuilder = new StringBuilder(hiddenWordString);
  return hiddenWordStringBuilder;
}


public static boolean playAgain(){
  boolean newGame;
  System.out.println();
  System.out.println("The game's over! Want to play again?");
  System.out.print("Enter 'yes' or 'no' ");
  newGame = TextIO.getlnBoolean();
  return newGame;
}


/**
This method is the framework for getting a valid letter input from user.

Method inputChar() is called to get user input, and then that input is checked for validity.
Consists of additional private methods isLetter() and isUnique() as control for letter input.

An inavlid input will prompt a response and the current state of the game UI will be printed again.

@param hiddenWord passed in as a parameter to be printed out in case of invalid input.
@param lives passed in as a parameter to be printed out in case of invalid input.
@param lettersUsed used in isUnique() control method and is also printed in case of invalid input.
@return String representing a unqiue and valid input by user.
*/

public static String getLetter(StringBuilder hiddenWord, int lives, String lettersUsed){
  while(true){
    String letter = inputChar();
    if (isLetter(letter) && isUnique(letter, lettersUsed)){
      return letter;
    }
    else {
      System.out.println();
      PlayHangman.printUI(hiddenWord, lives, lettersUsed);
      continue;
    }
  }
}

/**
Called by getLetter().

Prompts user to input a single character.

If multiple characters are entered on one line, only first character is read.

@return String of the input.
*/
      private static String inputChar(){
        System.out.println();
        System.out.print("Enter a letter: ");
        char input = TextIO.getlnChar();
          input = Character.toLowerCase(input);
          String letter = "" + input;
          return letter;
        }

/**
Called by getLetter().

Checks that letter input by user has not been entered before in current hangman game.

@param letter is letter user has just input.
@param lettersUsed is a String containing all letters previously entered by user.
@return boolean TRUE if lettersUsed does not contain letter.
*/
    private static boolean isUnique(String letter, String lettersUsed){
      boolean isUnique;
      if(lettersUsed.contains(letter)) {
        isUnique = false;
        System.out.println();
        System.out.println("You already used that letter!");
        return isUnique;
      }
      else{
        isUnique = true;
        return isUnique;
      }
    }


/**
Called by getLetter().

Checks that letter input by user is alphabetical (A-Z) regardless of capitalization.

@param letter user has just input.
@return boolean TRUE if the input is a letter.
*/
      private static boolean isLetter(String letter){
        boolean isLetter;
        if(letter.matches("[a-zA-Z]")){
          isLetter = true;
        }
        else{
          System.out.println();
          System.out.println("Please enter letters only.");
          isLetter = false;
        }
        return isLetter;
      }





// print UI Method

        public static void printUI(StringBuilder hiddenWord , int lives, String lettersUsed){
          System.out.printf("%-20s %20s %20s%n", "Word: " + hiddenWord, "Lives remaining: " + lives, "Letters used: " + lettersUsed);
        }
 //Print Stat
  public static void winRate(int gameCount, int wins) {
    System.out.printf("You have played %d games and get %d wins%n", gameCount, wins);
    double stats= (wins*100)/(gameCount);
    System.out.printf("Your percents of winning so far is %.2f%% %n", stats);
}
  
}
