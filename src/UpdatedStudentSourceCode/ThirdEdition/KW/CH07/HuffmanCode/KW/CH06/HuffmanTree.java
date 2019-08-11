/*<listing chapter="6" number="9">*/
package KW.CH06;

import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Class to represent and build a Huffman tree.
 *
 * @author Koffman and Wolfgang
 *
 */
public class HuffmanTree {

    // Nested Classes
    /**
     * A datum in the Huffman tree.
     */
    public static class HuffData {
        // Data Fields

        /**
         * The weight or probability assigned to this HuffData.
         */
        private final double weight;
        /**
         * The alphabet symbol if this is a leaf.
         */
        private final char symbol;

        public HuffData(double weight, char symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }
    }
    // Data Fields
    /**
     * A reference to the completed Huffman tree.
     */
    protected BinaryTree<HuffData> huffTree;

    /*<listing chapter="6" number="10">*/
    /**
     * Builds the Huffman tree using the given alphabet and weights.
     *
     * @post huffTree contains a reference to the Huffman tree.
     * @param symbols An array of HuffData objects
     */
    public void buildTree(HuffData[] symbols) {
        Queue<BinaryTree<HuffData>> theQueue
                = new PriorityQueue<>(symbols.length,
                        (lt, rt) -> Double.compare(lt.getData().weight, 
                                                   rt.getData().weight)
                );
        // Load the queue with the leaves.
        for (HuffData nextSymbol : symbols) {
            BinaryTree<HuffData> aBinaryTree
                    = new BinaryTree<>(nextSymbol, null, null);
            theQueue.offer(aBinaryTree);
        }

        // Build the tree.
        while (theQueue.size() > 1) {
            BinaryTree<HuffData> left = theQueue.poll();
            BinaryTree<HuffData> right = theQueue.poll();
            double wl = left.getData().weight;
            double wr = right.getData().weight;
            HuffData sum = new HuffData(wl + wr, '\u0000');
            BinaryTree<HuffData> newTree
                    = new BinaryTree<>(sum, left, right);
            theQueue.offer(newTree);
        }

        // The queue should now contain only one item.
        huffTree = theQueue.poll();
    }
    /*</listing>*/

    /**
     * Outputs the resulting code.
     *
     * @param out A PrintStream to write the output to
     * @param code The code up to this node
     * @param tree The current node in the tree
     */
    private void printCode(PrintStream out, String code,
            BinaryTree<HuffData> tree) {
        HuffData theData = tree.getData();
        if (theData.symbol != '\u0000') {
            if (theData.symbol == ' ') {
                out.println("space: " + code);
            } else {
                out.println(theData.symbol + ": " + code);
            }
        } else {
            printCode(out, code + "0", tree.getLeftSubtree());
            printCode(out, code + "1", tree.getRightSubtree());
        }
    }

    /**
     * Outputs the resulting code.
     *
     * @param out A PrintStream to write the output to
     */
    public void printCode(PrintStream out) {
        printCode(out, "", huffTree);
    }

    /*<listing chapter="6" number="11">*/
    /**
     * Method to decode a message that is input as a string of digit characters
     * '0' and '1'.
     *
     * @param codedMessage The input message as a String of zeros and ones.
     * @return The decoded message as a String
     */
    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree<HuffData> currentTree = huffTree;
        for (int i = 0; i < codedMessage.length(); i++) {
            if (codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree();
            } else {
                currentTree = currentTree.getLeftSubtree();
            }
            if (currentTree.isLeaf()) {
                HuffData theData = currentTree.getData();
                result.append(theData.symbol);
                currentTree = huffTree;
            }
        }
        return result.toString();
    }
    /*</listing>*/

    /**
     * A method encode for the HuffmanTree class that encodes a String of
     * letters that is passed as its first argument. Assume that a second
     * argument, codes (type String[]), contains the code strings (binary
     * digits) for the symbols (space at position 0, a at position 1, b at
     * position 2, and so on).
     *
     * @param str String to be encoded
     * @param codes Array of codes
     * @return Encoded string
     * @throws ArrayIndexOutOfBoundsException if str contains a character other
     * than a letter or space.
     */
    public static String encode(String str, String[] codes) {
		// Insert the solution to programming exercise 6.6
        return null; // Replace
    }

    // The following is needed to run and test the exercise.  Note that it
    // uses the Map class which is described in Chapter 7
    /**
     * Method to build the code array. Result is an ordered array of Strings
     * where the first item is the code for the smallest symbol in the array of
     * symbols.
     *
     * @return Array of codes
     */
    public String[] getCodes() {
        SortedMap<Character, String> map = new TreeMap<>();
        String currentCode = "";
        buildCode(map, currentCode, huffTree);
        List<String> codesList = new ArrayList<>();
        map.forEach((k, v) -> codesList.add(v));
        return codesList.toArray(new String[codesList.size()]);
    }

    private void buildCode(Map<Character, String> map,
            String code,
            BinaryTree<HuffData> tree) {
        HuffData theData = tree.getData();
        if (theData.symbol != '\u0000') {
            map.put(theData.symbol, code);
        } else {
            buildCode(map, code + "0", tree.getLeftSubtree());
            buildCode(map, code + "1", tree.getRightSubtree());
        }
    }
}
/*</listing>*/
