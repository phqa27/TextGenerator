// Students, please implement this class

import structure5.*;
import java.util.Random;
import java.util.Scanner;

/**
* A FrequencyList stores a set of characters each of which has
* an associated integer frequency
*/

public class FrequencyList {
  // freqVector stores associations of characters and their frequencies
  protected Vector<Association<String, Integer>> freqVector;
  // charPool stores characters, their number = the number of times they appear
  protected Vector<String> charPool;

  /** Construct an empty FrequencyList */
  public FrequencyList() {
    freqVector = new Vector<Association<String, Integer>>();
    charPool = new Vector<String>();
  }

  /** get index of association with associated character
  * @param ch is the String whose association's index we want to return
  */
  protected int getIndexCh(String ch) {
    // build an association <ch, 1>
    Association<String, Integer> charInfo = new Association<String, Integer>(ch, 1);

    // search for ch in freqVector
    int loc = freqVector.indexOf(charInfo);
    return loc;
  }

  /** get frequency of associated char
  * if ch is in freqVector, return associated frequency
  * otherwise return 0
  * @param ch is the String whose frequency we want to return
  */
  protected int getFreq(String ch) {
    int loc = getIndexCh(ch);
    // if association with string ch exists in freqVector
    // return associated frequency
    // otherwise return 0
    if (loc > -1) {
      return freqVector.get(loc).getValue();
    } else {
      return 0;
    }
  }

  /** set frequency of associated char
  * if ch is in freqVector, set associated frequency
  * @param ch is the String whose frequency we want to set
  * @param newFreq is the new frequency value
  */
  protected void setFreq(String ch, int newFreq) {
    int loc = getIndexCh(ch);
    // if association with string ch exists in freqVector
    // set associated frequency to newFreq
    if (loc > -1) {
      freqVector.get(loc).setValue(newFreq);
    }
  }

  /** add(String ch)
  * If ch is in the FrequencyList, increment it's associated frequency
  * Otherwise add ch to FrequencyList with frequency 1
  * @param ch is the String to add to the FrequencyList
  */
  public void add(String ch) {
    // build an association <ch, 1>
    // give ch frequency 1 in case it's a new character
    Association<String, Integer> charInfo = new Association<String, Integer>(ch, 1);

    // search for ch in freqVector
    // if found, update frequency
    // otherwise, add charInfo to freqVector
    int loc = freqVector.indexOf(charInfo);
    if (loc > -1) {
      // update frequency
      setFreq(ch, getFreq(ch) + 1);
    } else {
      freqVector.add(charInfo);
    }
    // update vector freqPool
    charPool.add(ch);
  }

  /** Selects a character form this FrequencyList
  * @return a character from the FrequencyList with probabality equal to its relative frequency
  */
  public String choose() {
    Random rand = new Random();
    int i = rand.nextInt(charPool.size());
    return charPool.get(i);
  }

  /** Produce a string representation of the FrequencyList
   * @return a String representing the FrequencyList
   */
  public String toString() {
    return freqVector.toString();
  }

  // Use main to test your FrequencyList class
  public static void main(String[] args) {
    FrequencyList freqList = new FrequencyList();
    String testString = "the theater is their thing";

    // for each character in testString
    for (int i = 0; i < testString.length(); i++) {
      // read in and tally instance of character
      String ch = String.valueOf(testString.charAt(i));
      freqList.add(ch);
    }

    // print out accumulated character frequencies
    //System.out.println(freqList);
    //System.out.println(freqList.charPool);

    // choose character
    for (int i = 0; i < testString.length(); i++) {
      //System.out.print(freqList.choose());
    }
  }
}
