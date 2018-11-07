public class Definitions{
public static void main(String[] args) {
  String fileDefinitions = "cs11a02_hangman_definitions.txt";
  String[] defs = new String[26552];
  TextIO.readFile(fileDefinitions);
  String fileWords = "cs11a02_hangman_words.txt";
  String[] words = new String[26552];
  int counter = 0;
  int counter2 = 0;
  int randomNum = (int)(Math.random()*26552);

  while(!TextIO.eof()){
    defs[counter] = TextIO.getln();
    counter++;

  }
  TextIO.readFile(fileWords);
  while(!TextIO.eof()){
    words[counter2] = TextIO.getln();
    counter2++;
  }
  System.out.println(words[0]);
  System.out.println(defs[0]);
  System.out.println();
  System.out.println(words[100]);
  System.out.println(defs[100]);
  System.out.println();
  System.out.println(words[1]);
  System.out.println(defs[1]);
  System.out.println();
  System.out.println(words[2]);
  System.out.println(defs[2]);
  System.out.println();
  System.out.println(words[26551]);
  System.out.println(defs[26551]);
  System.out.println();
  System.out.println(randomNum);
}

}
