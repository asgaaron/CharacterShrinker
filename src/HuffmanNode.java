
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class HuffmanNode implements Comparable<HuffmanNode>{

    private HuffmanNode parent, lChild, rChild;
    private HuffmanData object;

    HuffmanNode() {
        parent = null;
        lChild = null;
        rChild = null;
        object = null;
    }

    public HuffmanNode(HuffmanNode parent, HuffmanNode lChild, HuffmanNode rChild, HuffmanData object) {
        this.parent = parent;
        this.lChild = lChild;
        this.rChild = rChild;
        this.object = object;
    }
    
    private boolean isLeaf() {
        assert ((lChild == null) && (rChild == null)) || ((lChild != null) && (rChild != null));
        return (lChild == null) && (rChild == null);
    }

    /**
     * @return the parent
     */
    public HuffmanNode getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    /**
     * @return the lChild
     */
    public HuffmanNode getlChild() {
        return lChild;
    }

    /**
     * @param lChild the lChild to set
     */
    public void setlChild(HuffmanNode lChild) {
        this.lChild = lChild;
    }

    /**
     * @return the rChild
     */
    public HuffmanNode getrChild() {
        return rChild;
    }

    /**
     * @param rChild the rChild to set
     */
    public void setrChild(HuffmanNode rChild) {
        this.rChild = rChild;
    }

    /**
     * @return the object
     */
    public HuffmanData getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(HuffmanData object) {
        this.object = object;
    }

    /**
     * Function to check whether the node is a leaf
     */
    public boolean checkLeaf() {
        return !(lChild == null);
    }

    @Override
    public int compareTo(HuffmanNode node) {
        if (this.object.getFrequency() < node.getObject().getFrequency()) return -1;
        else if (this.object.getFrequency() == node.getObject().getFrequency()) return 0;
        else if (this.object.getFrequency() > node.getObject().getFrequency()) return 1;
        else try {
            throw new Exception("Can't compare these nodes!");
        } catch (Exception ex) {
            Logger.getLogger(HuffmanNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
