
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Priority queue implemented using a min-heap
 */
/**
 *
 * @author Aaron
 */
public class HuffmanTreePQ {

    PriorityQueue<HuffmanNode> queue;
    HuffmanComparator comparator;

    public HuffmanTreePQ() {
        this(255);
    }

    public HuffmanTreePQ(int startSize) {
        this.comparator = new HuffmanComparator();
        if (startSize < 1) {
            throw new IllegalArgumentException();
        }
        queue = new PriorityQueue<>(255, (Comparator) comparator);
    }

}
