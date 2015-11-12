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

    private final char symbol;
    private final int weight;

    public HuffmanData(char symbol, int frequency) {
        this.symbol = symbol;
        this.weight = frequency;
    }

    public HuffmanData(int frequency) {
        weight = frequency;
        symbol = '\0';
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
        return weight;
    }
}
