
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class HuffmanComparator implements Comparator{
    
//    Compares its two arguments for order.  Returns a negative integer,
//     * zero, or a positive integer as the first argument is less than, equal
//     * to, or greater than the second.<p>

    public int compare(HuffmanNode node1, HuffmanNode node2) {
        if (node1.getObject().getFrequency() < node2.getObject().getFrequency()) {
            return -1;
        } else if (node1.getObject().getFrequency() == node2.getObject().getFrequency()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
