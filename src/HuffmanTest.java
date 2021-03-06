
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
        if (args.length != 4) {
            printUsage();
            return;
        }

        boolean encode = true;
        switch (args[0]) {
            case "-encode": {
                encode = true;
                break;
            }
            case "-decode": {
                encode = false;
                break;
            }
            default: {
                printUsage();
                return;
            }
        }

        FileReader fileReader = new FileReader(args[1]);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int array[] = makeArray();

        parseInput(bufferedReader, array);
        verifyHuffData(array);

        HuffmanTreePQ queue = new HuffmanTreePQ();
        formPQ(array, queue);

        HuffmanNode huffmanTree;
        huffmanTree = formTree(queue);

        //build code table
        String[] table = new String[256];
        buildTable(table, huffmanTree, "");
        
        verifyHuffCodes(table, array);

        if (encode) {
            String fileEncoded = encode(table, args[2]);
            FileWriter fw = new FileWriter(args[3]);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fileEncoded, 0, fileEncoded.length());
            bw.close();
            fw.close();
        } else {
            decode(table, args[2], args[3]);
        }
    }

    private static void verifyHuffCodes(String[] table, int[] array) {
        for (int i = 0; i < 256; i++) {
            if (array[i] > 0) {
                if (i == 9) {
                    System.out.println("\\t\t" + table[i]);
                } else if (i == 10) {
                    System.out.println("\\n\t" + table[i]);
                } else if (i == 32) {
                    System.out.println("space\t" + table[i]);
                } else {
                    System.out.println((char) i + "\t" + table[i]);
                }
            }
        }
    }
    
    private static void verifyHuffData(int[] array) {
        for (int i = 0; i < 256; i++) {
            if (array[i] > 0) {
                if (i == 9) {
                    System.out.println("\\t\t" + array[i]);
                } else if (i == 10) {
                    System.out.println("\\n\t" + array[i]);
                } else if (i == 32) {
                    System.out.println("space\t" + array[i]);
                } else {
                    System.out.println((char) i + "\t" + array[i]);
                }
            }
        }
    }

    private static HuffmanNode formTree(HuffmanTreePQ queue) {
        while (queue.queue.size() > 1) {
            HuffmanNode hN1 = queue.queue.remove();
            HuffmanNode hN2 = queue.queue.remove();
            HuffmanData weightObject = new HuffmanData((hN1.getObject().getFrequency() + hN2.getObject().getFrequency()));
            HuffmanNode hNCombined = new HuffmanNode(null, hN1, hN2, weightObject);
            hN1.setParent(hNCombined);
            hN2.setParent(hNCombined);
            queue.queue.add(hNCombined);
        }
        HuffmanNode huffmanTree = queue.queue.remove();
        return huffmanTree;
    }

    private static int[] makeArray() {
        int array[] = new int[256];
        for (int i = 0; i < 256; i++) {
            array[i] = 0;
        }
        return array;
    }

    private static void formPQ(int[] array, HuffmanTreePQ queue) {
        //create huffman data objects, store each in a node
        //enqueue nodes in a PQ, a min heap
        for (int i = 0; i < 256; i++) {
            if (array[i] > 0) {
                HuffmanData tmp = new HuffmanData((char) i, array[i]);
                HuffmanNode tmp1 = new HuffmanNode(null, null, null, tmp);
                queue.queue.add(tmp1);
            }
        }
        if (queue.queue.size() == 1) {
            if (array['\0'] == 0) {
                queue.queue.add(new HuffmanNode(null, null, null, new HuffmanData('\0', 0)));
            } else {
                queue.queue.add(new HuffmanNode(null, null, null, new HuffmanData('\1', 0)));
            }
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

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("-encode <fileBuildHuffman> <fileToEncode> <fileEncoded>");
        System.out.println("or");
        System.out.println("-decode <fileBuildHuffman> <fileEncoded> <fileDecoded>");
    }

    //making lookup table from encodings
    private static void buildTable(String[] table, HuffmanNode node, String s) {
        if (!node.isLeaf()) {
            buildTable(table, node.getlChild(), s + "0");
            buildTable(table, node.getrChild(), s + "1");
        } else {
            table[node.getObject().getSymbol()] = s;
        }
    }

    private static String encode(String[] table, String fileToEncode) throws FileNotFoundException, IOException {
        String fileEncoded = new String();
        FileReader fr = new FileReader(fileToEncode);
        BufferedReader br = new BufferedReader(fr);
        while (br.ready()) {
            fileEncoded += table[(int) br.read()];
        }
        return fileEncoded;
    }

    private static void decode(String[] table, String fileEncoded, String fileDecoded) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(fileEncoded);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(fileDecoded, false);
        BufferedWriter bw = new BufferedWriter(fw);

        String lookup = "";
        while (br.ready()) {
            lookup += (char)br.read();
            for (int i = 0; i < 256; i++) {
                if (table[i] != null) {
                    if (table[i].equals(lookup)) {
                        bw.write((char) i);
                        lookup = "";
                    }
                }
            }
        }
        br.close();
        fr.close();
        bw.close();
        fw.close();
    }
}
