import java.util.Arrays;
public class TestUniqueExistencePosition {
  public static void main(String args[]) {
    String word = "Hello"; // sample word to test code
    int chance = 3; //equivalent to lives; sample for testing code

    // the below array is not needed, because the string is already an array, and string methods allow us to handle indices withint the string!
    //char[] word= wordArray(words); //char array of the word

    // this char array has a problem: if you guess a correct letter , it needs to be added to the array since you just used import junit.framework.TestCase;
    // what this means is that the length of this array is not neccessarily the numbers of chances you have for the game
    // since both correct and incorrect letters must go into this array, thus this array could be too short in a number of cases leading to problems

    //char[] enter= new char[chance]; //create an array for letter already enter, the size depends on the chance of guessing

    // alterative to char array is to make an emptt string holding all letters used as follows:

    String lettersUsed = "";

    // this can be used to add new letters w/o worrying about array length at all!


    // this if needs to be a loop. the if only goes through it once




    while(chance > 0){
      System.out.println("Enter your letter: "); ////enter a letter
      char inputletter= TextIO.getlnChar();


      // don't need this
      //int time=0; //number of time entering letter



      if (enteryet(lettersUsed, inputletter)) {
        System.out.println("You have guessed this letter already, try another one!");

        // need to add continue to reset loop so user can add in new letter

        continue;
      } else {

          // if letter is unique, first thing you should do is store it in array/string
          // doing it separately for each if/else is inefficient, just do it here First

          lettersUsed = lettersUsed + inputletter;
          System.out.println(lettersUsed);
        //time=time+1; don't need this
        if (existence(word, inputletter)) {
          int[] pos= position(word, inputletter);
          System.out.printf("Yes! the letter %c appears %d times in the word.%n", inputletter, pos.length); //print out congrats

          //letterReplace(); //appear the letter on the word; WORKING ON THIS


          //enter[time]=inputletter;//add the letter to enter array

        } else {
          System.out.printf("Sorry! There is no letter %c in the word.%n", inputletter); //apologize

          //enter[time]=inputletter; //add to the enter array


          chance= chance - 1; // remove a chance
        }
      }
    }
      System.out.println("You lost! You've used out all the chances.");

  }
  //create a method breaking each letter in the word into a char array

  // this method seems completely unneeded.

  /*
  public static char[] wordArray (String a) {
    char[] word= new char[a.length()];
    for (int i=0; i < a.length(); i++) {
    word[i]= a.charAt(i);
    }
    return word;
  }
  */
  //create a method checking whether the letter has been entered

  // since you can use word as a string, just use contains to check if letter is in string:
  public static boolean enteryet (String lettersUsed, char letter) {
/*
    your original code is below:

     int pos = Arrays.binarySearch(word,letter);
      if (pos<0) {
        return false;
      } else {
        return true;
      }
      */
      if(lettersUsed.contains(""+letter)){  // put in quotes to coerce char to String. If not done, error due to incompatibility of types
        return true;
      }
      else{
        return false;
      }

    }

    //create a method checking existence of letter in the word

    // looks good
    public static boolean existence(String word, char letter) {
      for (int i=0; i<word.length(); i++) {
        if (word.charAt(i)==letter) {
          return true;
        }
      }
          return false;
    }

    //create a method forming an array cointaining the position of letter in the word

    // looks good
    public static int[] position(String word, char letter) {
      int[] pos= new int[word.length()];
      int count=0;
      for (int i=0; i < word.length(); i++) {
        if (word.charAt(i) == letter) {
          pos[count]=i;
          count=count+1;
        }
      }
      // nice!
        int[] newpos= new int[count];
        for (int i=0; i <newpos.length; i++){
          newpos[i]= pos[i];
        }
      return newpos;
    }
}
  //create an array for letter already enter
  //input the letter
  // check whether the letter has been entered before
    //if yes
      //resubmit another letter
    //if no
      //check whether the letter is inside the word
        //if yes
          //how many times it appears and the positions
          //congrats
          //appear on the box
          //add to the enter array
          //count time of submit
        //if no
          //apologize
          //add to the enter array
          //remove chance of submit
