// Students, please implement this class
import java.util.Scanner;
import java.util.Random;

public class WordGen {
  // the level of analysis
  protected int k;
  // the k-letter word to update probability and choose next character
  protected String key;
  // Table object
  protected Table wordGenTable;
  // next character to add to table while reading the input text
  protected char nextCharAdd;
  // next character to generate
  protected String nextCharGen;
  // input text
  protected String inputText;
  // output text
  protected String outputText;

  /** construct WordGen object */
  public WordGen() {
    k = 0;
    key = "";
    wordGenTable = new Table();
    inputText = new String();
  }

  /**
  * reads input text, update inputText
  */
  public void readText() {
    Scanner in = new Scanner(System.in);
    StringBuffer textBuffer = new StringBuffer();
    while (in.hasNextLine()) {
      String line = in.nextLine();
      textBuffer.append(line);
      textBuffer.append("\n");
    }
    // inputText now contains the full contents of the input.
    inputText = textBuffer.toString();
  }

  /**
  * update table based on input text
  */
  public void updateTable() {
    // loop through k-letter strings in inputText to update wordGenTable
    for (int i = 0; i < inputText.length() - k - 1; i++) {
      key = inputText.substring(i, i + k);
      //System.out.println(key);
      nextCharAdd = inputText.charAt(i + k);
      //System.out.println(nextCharAdd);
      wordGenTable.add(key, nextCharAdd);
    }
  }

  /**
  * generate k-level analysis text
  * return outputText
  */
  public String genText() {
    // randomly generate first key from wordGenTable
    Random rand = new Random();
    int r = rand.nextInt(wordGenTable.getTableVector().size());
    outputText = wordGenTable.getTableVector().elementAt(r).getKey();

    // generate characters based on probability in wordGenTable
    // iterate (number of k-letter strings in inputText)-times
    for (int i = 0; i < inputText.length() - k - 1; i++) {
      key = outputText.substring(outputText.length() - k);
      //System.out.println(key);
      nextCharGen = String.valueOf(wordGenTable.choose(key));
      //System.out.println(nextCharGen);
      outputText += nextCharGen;
    }
    return outputText;
  }

  public static void main(String[] args) {
    WordGen testWordGen = new WordGen();
    if (args.length == 0) {
        // no args, so print usage line and do nothing else
        System.out.println("Usage: java WordGen ");
    } else {
        // convert first argument to k
        testWordGen.k = Integer.parseInt(args[0]);
        testWordGen.readText();
        testWordGen.updateTable();
        //System.out.println(testWordGen.wordGenTable);
        System.out.println(testWordGen.genText());
    }
  }
}
