/*
 * Name: Jose Chan
 * Email: jchanpineda@ucsd.edu
 * PID: A17530266
 * Sources used: None
 * 
 * This file is used to define a MyStringBuilder, which acts similarly
 * to Strings and StringBuilders. It allows you to create and store, 
 * and change characters.
 */

 /**
 * This class creates a sequence of Charnodes that link to eachother
 * out of a char, String, or anohter MyStringBuilder. The class also
 * allows you to append chars and strings, and convert it into a 
 * String
 * 
 * Instance variables: 
 * start - The first Charnode in MyStringBuilder
 * end - The last Charnode in MyStringBuilder
 * length - The length of the entire MyStringBuilder
 */
    
public class MyStringBuilder {

    /* Constants  */
    private static final int ONE = 1;
    private static final int ZERO = 0;

    /* Instance Variables */
    private CharNode start;
    private CharNode end;
    private int length;
    
    /**
     * Creates a MyStringBuilder out of a char
     * @param ch The char you want to convert into a 
     * MyStringBuilder
     */
    public MyStringBuilder(char ch) {
        CharNode obj = new CharNode(ch);
        start = obj;
        end = obj;
        length = ONE;
    }
    
    /**
     * Creates a MyStringBuilder out of a str
     * @param str The string you want to convert into a MSB
     */
    public MyStringBuilder(String str) {
        if (str == null){
            throw new NullPointerException();
        } else if (str.equals("") == false){
            // creates a new Charnode, makes it into start, and
            //creates new ones in a loop
            CharNode ref1 = new CharNode(str.charAt(ZERO));
            start = ref1;
            end = new CharNode(str.charAt(str.length() - ONE));
            CharNode ref2;
            for (int i = ONE; i < str.length(); i++){
                ref2 = new CharNode(str.charAt(i));
                ref1.setNext(ref2);
                ref1 = ref2;
            }
            end = ref1;
            length = str.length();
        }
    }

    /**
     * Creates a new MyStringBuilder out of another
     * MyStringBuilder
     * @param other The MyStringBuilder you want to make
     * a new MSB out of
     */
    public MyStringBuilder(MyStringBuilder other) {
        if (other == null){
            throw new NullPointerException();
        } else {
            //Converts other into a string using toString
            String str = other.toString();
            //Same method as previus constructor
            CharNode ref1 = new CharNode(str.charAt(ZERO));
            start = ref1;
            end = new CharNode(str.charAt(str.length() - ONE));
            end.setNext(null);
            CharNode ref2;
            for (int i = ONE; i < str.length(); i++){
            ref2 = new CharNode(str.charAt(i));
            ref1.setNext(ref2);
            ref1 = ref2;
        }
        end = ref1;
        length = str.length();
        }
    }

    /**
     * Returns the length of the MyStringBuilder
     * @return
     */
    public int length() {
        return length;
    }

    /**
     * Appends a char to the end of a MyStringBuilder
     * @param ch The char you want to append
     * @return
     */
    public MyStringBuilder append(char ch) {
        if(this.toString().equals("")){
            // If MyStringBuilder is empty, replicates char constructor
            CharNode obj = new CharNode(ch);
            start = obj;
            end = obj;
            length = ONE;
        } else {
            //sets new char equal to end, links to previous end, 
            // and increases length by 1
            CharNode newNode = new CharNode(ch);
            end.setNext(newNode);
            end = newNode;
            length ++;
        }
        return this;
    }

    /**
     * Appends a string to the end of MyStringBuilder
     * @param str The String you want to append
     * @return
     */
    public MyStringBuilder append(String str) {
        if (str.equals("")){
            return this;
        }
        //creates a new Charnode to start linking the new Charnodes
        CharNode ref1 = new CharNode(str.charAt(0));
        CharNode ref2;
        if (this.toString().equals("")){
            start = ref1;
            end = ref1;
        }
        end.setNext(ref1);
        //creates new charnodes in a loop, linking them together
        for (int i = ONE; i < str.length(); i++){
            ref2 = new CharNode (str.charAt(i));
            ref1.setNext(ref2);
            ref1 = ref2;
        }
        end = ref1;
        length += str.length();
        return this;    
    }

    /**
     * Converts a MyStringBuilder into a string
     */
    public String toString() {
        String returnString = "";
        CharNode currNode = start;
        if(start == null){
            return "";
        } else {
            for (int i = ZERO; i < length; i++){
                returnString += currNode.getData();
                currNode = currNode.getNext();
            }
            return returnString;
        }
    }

    /**
     * Returns a substring starting at startIdx
     * @param startIdx The starting index
     * @return
     */
    public String subString(int startIdx) {
        if (startIdx < ZERO || startIdx >= length){
            throw new IndexOutOfBoundsException();
        } else {
            String returnString = "";
            returnString = this.toString();
            returnString = returnString.substring(startIdx);
            return returnString;
        }
    }

    /**
     * returns a substring from startIdx to endIdx
     * @param startIdx The starting Index (inclusive)
     * @param endIdx The ending Index (exclusive)
     * @return
     */
    public String subString(int startIdx, int endIdx) {
        String returnString = "";
        if (startIdx == endIdx){
            return returnString;
        } else if (startIdx < 0 || startIdx >= length){
            throw new IndexOutOfBoundsException();
        } else if (endIdx > length || endIdx < startIdx){
            throw new IndexOutOfBoundsException();
        } else {
            returnString = this.toString();
            returnString = returnString.substring(startIdx, endIdx);
            return returnString;
        }
    }
}