public class HangManDemoCOPY{
  public static int gameCount = 0;
  public static int wins;
  public static boolean[] wordUsed = new boolean[26275];
public static void main(String[] args) {


// Loading the game program

// Method 1
String[] words = HangmanSetup.loadFile("cs11a02_hangman_words.txt", 26275);
String[] definitions = HangmanSetup.loadFile("cs11a02_hangman_definitions.txt", 26275);  // optional; not essential to playing game.



// Method 2
//int gameCount = 0;
//int wins = 0;

// Method 3
HangmanSetup.greetUser();
HangmanSetup.promptRules();

// Setting up and playing the specific game
do{
String lettersUsed = "";
int lives = PlayHangman.difficultySelection();
int wordIndex = PlayHangman.getWordIndex(26275);
String word = words[wordIndex];
StringBuilder hiddenWord = buildHiddenString(word);


// need to print UI which includes hidden word as StringBuilder object

while(lives > 0){
  System.out.println();
  printUI(hiddenWord, lives, lettersUsed); // UI for game loaded after each letter has been input
  String letter = getLetter(hiddenWord, lives, lettersUsed);  // this whole method based on code in WordHandling.java
  lettersUsed = lettersUsed + letter ;
  if(word.contains(letter)){
    for(int i = 0; i < word.length(); i++){
      if(word.substring(i, i+1).equals(letter)){
        hiddenWord = hiddenWord.replace(i, i+1, letter);
        }
      }
      if(hiddenWord.toString().equals(word)){
        System.out.println("You won!");
        wins++;
        break;
    }
  }
  else{
    lives--;
    if(lives == 0){
      System.out.println("You lost.");
    }
  }
}

System.out.printf("The word was %s.%n" , word);
//definition();
gameCount++;
//winrate();
} while(playAgain());









}




// print UI Method

public static void printUI(StringBuilder hiddenWord , int lives, String lettersUsed){
  System.out.printf("%-20s %20s %20s%n", "Word: " + hiddenWord, "Lives remaining: " + lives, "Letters used: " + lettersUsed);
}

// Method 6: Word Selection. once you get number, want to check if it's already set to true. if it is rerun to get another number until you get one that is still set to false

// think about how we could design this so that for a given difficulty a certain range of word lengths are required
// like easy = words between 3-5, med = 6-10, hard = 11+, and custom is everything
// can probably just pass lives as a parameter to the method
// then use a switch somehow

// partial solution in ContinueTest.java file. Shows how to check if word is appropriate length for a given difficulty

// also consider putting gameCount if else as first thing done, since if gamecount is maxed out, then there's no point
// generating any random number in first place, so move it first as an if, then everything else is done otherwise.


// building hidden word that will be modified by correct user input
public static StringBuilder buildHiddenString(String word){
  String hiddenWordString = "";
  for(int i = 0; i < word.length(); i++){
    hiddenWordString = hiddenWordString + "*";
  }
  StringBuilder hiddenWordStringBuilder = new StringBuilder(hiddenWordString);
  return hiddenWordStringBuilder;
}

// get each letter input by User

public static String getLetter(StringBuilder hiddenWord, int lives, String lettersUsed){
  while(true){
    String letter = inputChar();
    if (isLetter(letter) && isUnique(letter, lettersUsed)){
      return letter;
    }
    else {
      System.out.println();
      printUI(hiddenWord, lives, lettersUsed);
      continue;
    }
  }
}

// part of getLetter
public static String inputChar(){
  System.out.println();
  System.out.print("Enter a letter: ");
  char input = TextIO.getlnChar();
    input = Character.toLowerCase(input);
    String letter = "" + input;
    return letter;
  }

// part of getLetter
  public static boolean isLetter(String letter){
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

// part of getLetter
    public static boolean isUnique(String letter, String lettersUsed){
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

// this is good
public static boolean playAgain(){
  boolean newGame;
  System.out.println();
  System.out.println("The game's over! Want to play again?");
  System.out.print("Enter 'yes' or 'no' ");
  newGame = TextIO.getlnBoolean();
  return newGame;



}

}

/*
Program Classes:

1) HangMan setup: includes loadWords, loadDefinitions, greet, rules, difficultySelection (customDifficulty), getWordIndex,  and buildHiddenString
2) HangMan play: includes printUI, getLetter,  inputChar, isLetter, isUnique, letterReplace(made in main, but not as method)
3) HangMan end: include showDefinition (not made), showGameStats (not made), and playAgain


*/