// Students, please implement this class
import structure5.*;
import java.util.Random;

/**
* A Table holds a collection of strings, each of which has an
* associated FrequencyList
*/
public class Table {
  // tableVector stores associations between strings and FrequencyList's
  protected Vector<Association<String, FrequencyList>> tableVector;

  /** Construct an empty table */
  public Table() {
    tableVector = new Vector<Association<String, FrequencyList>>();
  }

  /**
  * access instance variable tableVector
  */
  public Vector<Association<String, FrequencyList>> getTableVector() {
    return tableVector;
  }

  /**
  * Updates the table as follows
  * If key already exists in the table, update its FrequencyList
  * by adding value to it
  * Otherwise, create a FrequencyList for key and add value to it
  * @param key is the desired k-letter sequence
  * @param value is the character to add to the FrequencyList of key
  */
  public void add(String key, char value) {
    // build an association <key, FrequencyList(value, 1)>
    Association<String, FrequencyList> strInfo = new Association<String, FrequencyList>(key, new FrequencyList());

    // search for str in tableVector
    // if found, update FrequencyList
    // otherwise, add strInfo to tableVector
    int loc = tableVector.indexOf(strInfo);
    if (loc > -1) {
      // update FrequencyList
      FrequencyList freqList = tableVector.get(loc).getValue();
      freqList.add(String.valueOf(value));
    } else {
      tableVector.add(strInfo);
      FrequencyList freqList = strInfo.getValue();
      freqList.add(String.valueOf(value));
    }
  }

  /**
  * If key is in the table, return one of the characters from
  * its FrequencyList with probability equal to its relative frequency
  * Otherwise, determine a reasonable value to return
  * @param key is the k-letter sequence whose frequencies we want to use
  * @return a character selected from the corresponding FrequencyList
  */
  public String choose(String key) {
    // build an association <key, FrequencyList()>
    Association<String, FrequencyList> strInfo = new Association<String, FrequencyList>(key, new FrequencyList());
    // search for str in tableVector
    int loc = tableVector.indexOf(strInfo);

    // if key is in tableVector, return character from FrequencyList
    if (loc > -1) {
      FrequencyList freqList = tableVector.get(loc).getValue();
      String ch = freqList.choose();
      return ch;

    // otherwise, generate random key from tableVector
    } else {
      Random rand = new Random();
      int i = rand.nextInt(tableVector.size());
      return tableVector.elementAt(i).getKey();
    }
  }

  /** Produce a string representation of the Table
  * @return a String representing this Table
  */
  public String toString() {
    return tableVector.toString();
  }

  // Use main to test your Table class
  public static void main(String[] args) {
    Table testTable = new Table();
    String testString = "the theater is their thing";
    int k = 2;

    // for each k-letter string in testString except for the last
    for (int i = 0; i < testString.length() - k; i++) {
      // read in key and character
      String key = testString.substring(i, i + k);
      //System.out.println(key);
      char value = testString.charAt(i + k);
      testTable.add(key, value);
    }

    // print out accumulated character frequencies
    //System.out.println(testTable);

    // choose character
    String testOutput = testString.substring(0, k);
    for (int i = 0; i < testString.length() - k; i++) {
      String testKey = testOutput.substring(testOutput.length() - k);
      String chGen = testTable.choose(testKey);
      //System.out.print(testKey);
      testOutput += chGen;
    }
    //System.out.println(testOutput);
  }

}
