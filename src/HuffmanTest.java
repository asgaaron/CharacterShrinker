
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

        int array[] = null;
        for (int i = 0; i < 256; i++) {
            array[i] = 0;
        }
         
       while (bufferedReader.ready()) {
           int value = bufferedReader.read();
           if (value == -1)
               break;
           array[value]++;
        }
       
       HuffmanTreePQ queue = new HuffmanTreePQ();
       for (int i = 0; i < 256; i++) {
           HuffmanData tmp = new HuffmanData((char)i, array[i]);
           HuffmanNode tmp1 = new HuffmanNode(null, null, null, tmp);
           queue.add(tmp1);
       }
    }

}
