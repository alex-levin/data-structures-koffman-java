/*<exercise chapter="8" type="programming-project" number="8">*/
package KW.CH08.dutchnationalflag;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingUtilities;

/** This program illustrates the problem of the
 *  Dutch National Flag as described by Dijkstra
 *  @author Koffman and Wolfgang */
public class DutchNationalFlag extends JFrame {

    /** The width of the flag */
    private static final int W = 323;
    /** The height of the flag */
    private static final int H = 198;
    /** The flag */
    private Flag theFlag;

    /** Construct the frame */
    private DutchNationalFlag() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        theFlag = new Flag(W, H);
        theFlag.setPreferredSize(new Dimension(W, H));
        getContentPane().add(theFlag, BorderLayout.CENTER);
        JButton sortButton = new JButton("SORT");
        sortButton.addActionListener(e -> theFlag.sort());
        getContentPane().add(sortButton, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    /** Main method
     *  @param args -- ignored */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DutchNationalFlag());
    }
}
/*</exercise>*/
