// class contains method to check if input char has been used before
// and method to replace missing letters with input if they are found in word

// ultimately, in the context of the Hangman program, a method will be called to
// return a String which is the user's input.

//The letter's already used will be passed
// into it as a parameter.

/*
 The method then does the following:

 while loop is initiated that will go until a char is returned.

    within loop, call a method to get the letter as a type String

            within this method, TextIO is used to get a char
            the char is converted to a string and made lowercase (all words used are lower case, so it controls for that)
            the String letter that was input is then returned back out to while loop


        in while loop a control structure is used to check two things:
                                                                      1) the user input is actually a letter a-z
                                                                      2) the letter has not been typed by user already

                      each of these is done by a separate method that returns a boolean

                      if both are true, the letter is returned as a String

                      if either is false, the user is told what problem is, and they are then prompted to enter a new letter



Going through Vy's code motivated me to write this out, as it incorporates here ideas

but also fixes some problems with her program like:

the case of the user input matters (uppercase is treated different from lowercase)

her program will read in and use non-alphabet inputs like numbers, which would mess up the game



Additionally, this is only focused on one goal: getting a letter from the user.

I think this is important, because I think the following pipeline is a clean way to code the hangman game:

  1) Get a letter from User using a single method (which calls many other methods)

  2) Check whether letter is in word and then rebuild the hidden word (like **** for "cats") with letters that are in the word

Vy's code does this (though we haven't dont part 2), but it sort of blends a lot of things together.

If you compare this to it, it uses the same ideas she used and in the same order, but I think it has a tighter organization.

This code is essentially focused on 1) only of the above pipeline.


Try it out, all the elements needed are already there (note lives variable is irrelevant, just added so it's not infinite loop)
*/



public class LetterHandling{
  public static void main(String[] args) {
    int lives = 9; // in the actual method, lives WOULD NOT be passed into the method. Only used letters would be passed in
    String word = "Poop"; // test word
    String lettersUsed = "";  // this would be passed in in actual program
    while(lives > 0){   // this would be set to true (goes until letter is finally returned)
      System.out.println("Lives left: " + lives);
      String letter = inputChar();  // method to get letter from user
      if (isLetter(letter) && isUnique(letter, lettersUsed)){     // whatever char is returned is checked to make sure its not in string of letters used and is a letter a-z vs a number or a symbol
        lettersUsed = lettersUsed + letter;   // valid input (a letter that has not been used yet) ,  is stored in string of used letters
        System.out.println(lettersUsed);
        lives--;
      }
      else {
        continue;   // if input char is not a letter or has been used, restarts from beginning of while loop.
      }
    }
  }

  // get a single char from user input
  public static String inputChar(){
    System.out.println();
    System.out.print("Enter a letter: ");
    char input = TextIO.getlnChar();    // input is gotten as char
      input = Character.toLowerCase(input);  // converted to lowercase (every word in game is lowercase if you look at .txt file)
      String letter = "" + input;  // convert char to string (makes later steps of method easier)
      return letter;
    }

// check input char is a letter
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

// check input char has not been input before
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

}
