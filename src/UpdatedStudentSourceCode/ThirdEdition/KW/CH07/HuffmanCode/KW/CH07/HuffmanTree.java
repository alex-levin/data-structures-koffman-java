/*<listing chapter="7" section="6">*/
package KW.CH07;

import KW.CH06.BinaryTree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent and build a Huffman tree
 * @author Koffman and Wolfgang */
public class HuffmanTree extends KW.CH06.HuffmanTree {

    /** The map between Characters and their coding */
    private Map<Character, BitString> codeMap;

    // Methods

    /*<listing chapter="7" number="12">*/
    public static HuffData[] buildFreqTable(BufferedReader ins) {
        // Map of frequencies.
        Map<Character, Integer> frequencies = new HashMap<>();  
        try {
            int nextChar;   // For storing the next character as an int
            while ((nextChar = ins.read()) != -1) {  // Test for more data
                Character next = (char)nextChar;
                // Get the current count and increment it.
                Integer count = frequencies.get(next);
                if (count == null) {
                    count = 1;   // First occurrence.
                } else {
                    count++;
                }
                // Store updated count.
                frequencies.put(next, count);
            }
            ins.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Copy Map entries to a HuffData[] array.
        HuffData[] freqTable = new HuffData[frequencies.size()];
        int i = 0;     // Start at beginning of array.
        // Get each map entry and store it in the array 
        // as a weight-symbol pair.
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            freqTable[i] =
                    new HuffData(entry.getValue().doubleValue(),
                    entry.getKey());
            i++;
        }
        return freqTable;    // Return the array.
    }
    /*</listing>*/

    /**
     * Starter method to build the code table.
     * @post The table is built.
     */
    public void buildCodeTable() {
        // Initialzie the code map
        codeMap = new HashMap<>();
        // Call recursive method with empty bit string for code so far.
        buildCodeTable(huffTree, new BitStringBuilder());
    }

    /**
     * Recursive method to perform breadth-first traversal
     * of the Huffman tree and build the code table.
     * @param tree The current tree root
     * @param code The code string so far
     */
    private void buildCodeTable(BinaryTree<HuffData> tree, BitStringBuilder code) {
        // Get data at local root.
        HuffData datum = tree.getData();
        if (datum.getSymbol() != '\u0000') {  // Test for leaf node.
            // Found a symbol, insert its code in the map.
            codeMap.put(datum.getSymbol(), code.toBitString());
        } else {
            // Append 0 to code so far and traverse left. 
            BitStringBuilder leftCode = new BitStringBuilder(code).append(false);
            buildCodeTable(tree.getLeftSubtree(), leftCode);
            // Append 1 to code so far and traverse right.
            BitStringBuilder rightCode = new BitStringBuilder(code).append(true);
            buildCodeTable(tree.getRightSubtree(), rightCode);
        }
    }

    /**
     * Encodes a data file by writing it in compressed bit string form.
     * @param ins The input stream
     * @param outs The output stream
     */
    public void encode(BufferedReader ins,
            DataOutputStream outs) {
        BitStringBuilder result = new BitStringBuilder();  // The complete bit string.
        try {
            int nextChar;
            while ((nextChar = ins.read()) != -1) {  // More data?
                Character next = (char) nextChar;

                // Get bit string corresponding to symbol nextChar.
                BitString nextChunk = codeMap.get(next);
                result = result.append(nextChunk);   // Append to result string.
            }
            // Write result to output file and close files.
            result.toBitString().write(outs);
            ins.close();
            outs.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Method to write a HuffmanTree to a DataOutputStream.
     * @param outs The DataOutputStream
     * @throws IOException if an IO error occurs
     */
    public void write(DataOutputStream outs) throws IOException {
        BitStringBuilder bsb = new BitStringBuilder();
        encodeHuffmanTree(huffTree, bsb);
        bsb.toBitString().write(outs);
    }
    
    /**
     * Method to encode a HuffmanTree
     * @param tree The local tree root
     * @param bsb The BitStringBuilder containing the encoding
     */
    public void encodeHuffmanTree(BinaryTree<HuffData> tree, BitStringBuilder bsb) {
        if (!tree.isLeaf()) {
            bsb.append(false);
            encodeHuffmanTree(tree.getLeftSubtree(), bsb);
            encodeHuffmanTree(tree.getRightSubtree(), bsb);
        } else {
            bsb.append(true);
            bsb.append(tree.getData().getSymbol());
        }
    }
    
    /**
     * Method to decode a HuffmanTree
     * @param bitString A BitString containing the encoding of the tree
     * @return The decoded HuffmanTree
     */
    public static HuffmanTree decodeHuffmanTree(BitString bitString) {
        BitString.BitStringIterator itr = bitString.iterator();
        BinaryTree<HuffData> root = decodeHuffmanTree(itr);
        HuffmanTree tree = new HuffmanTree();
        tree.huffTree = root;
        return tree;
    }
    
    /**
     * Method to decode a HuffmanTree
     * @param itr A BitStringIterator iterating through the encoding
     * @return A local root
     */
    private static BinaryTree<HuffData> decodeHuffmanTree(BitString.BitStringIterator itr) {
        if (!itr.next()) {
            BinaryTree<HuffData> left = decodeHuffmanTree(itr);
            BinaryTree<HuffData> right = decodeHuffmanTree(itr);
            HuffData sum = new HuffData(0, '\u0000');
            return new BinaryTree<>(sum, left, right);
        } else {
            char c = '\u0000';
            for (int i = 0; i < 16; i++) {
                c <<= 1;
                if (itr.next()) {
                    c |= 1;
                }
            }
            HuffData leaf = new HuffData(0, c);
            return new BinaryTree<>(leaf, null, null);
        }
    }

    /**
     * Decode a message from an input file
     * @param ins The input stream containing the message
     * @param out The BufferedWriter to write the decoded message
     */
    public void decode(DataInputStream ins, BufferedWriter out) {
		// Programming project 1, Chapter 7
    }
}
/*</listing>*/
