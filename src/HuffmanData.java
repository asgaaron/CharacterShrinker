/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class HuffmanData {

    private char symbol;
    private int frequency;

    public HuffmanData() {
    }

    public HuffmanData(char symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }

    /**
     * @return the symbol
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }
}
