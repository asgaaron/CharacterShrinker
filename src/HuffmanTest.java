
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aaron
 */
public class HuffmanTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader();
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int array[] = makeArray();

        parseInput(bufferedReader, array);

        HuffmanTreePQ queue = new HuffmanTreePQ();
        formPQ(array, queue);

        formTree(queue);
    }

    private static int[] makeArray() {
        int array[] = null;
        for (int i = 0; i < 256; i++) {
            array[i] = 0;
        }
        return array;
    }

    private static void formPQ(int[] array, HuffmanTreePQ queue) {
        //create huffman data objects, store each in a node
        //enqueue nodes in a PQ, a min heap
        for (int i = 0; i < 256; i++) {
            HuffmanData tmp = new HuffmanData((char) i, array[i]);
            HuffmanNode tmp1 = new HuffmanNode(null, null, null, tmp);
            queue.queue.add(tmp1);
        }
    }

    private static void parseInput(BufferedReader bufferedReader, int[] array) throws IOException {
        //parse the input file
        while (bufferedReader.ready()) {
            int value = bufferedReader.read();
            if (value == -1) {
                break;
            }
            array[value]++;
        }
    }

    private static void formTree(HuffmanTreePQ queue) {
        while (queue.queue.size() > 1) {
            HuffmanNode hN1 = queue.queue.remove();
            HuffmanNode hN2 = queue.queue.remove();
            HuffmanData weightObject = new HuffmanData((hN1.getObject().getFrequency() + hN2.getObject().getFrequency()));
            HuffmanNode hNCombined = new HuffmanNode(null, hN1, hN2, weightObject);
            queue.queue.add(hNCombined);
        }
    }

}
