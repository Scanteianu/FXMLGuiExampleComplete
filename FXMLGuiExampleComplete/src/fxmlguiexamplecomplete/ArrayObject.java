/*
 * License: this is an example created by Daniel Scanteianu at Stony Brook University
 * Use, reuse, modify, etc. as you wish. 
 */
package fxmlguiexamplecomplete;

/**
 * This object holds some information including a name, and a value, and an index.
 * @author Dan
 */
public class ArrayObject implements Cloneable{
    /**
     * Constructor to initialize object with values
     * @param index the int for the index value
     * @param name the string for the name value
     * @param value the double to be stored in the value field
     */
    public ArrayObject(int index, String name, double value) {
        this.index = index;
        this.name = name;
        this.value = value;
    }
    /**
     * empty constructor so you can add values later.
     */
    public ArrayObject()
    {
        
    }
    private int index;
    private String name;
    private double value;
    /**
     * performs a deep copy of this Array Object. Doesn't modify original.
     * @return a new array object with the same values
     */
    public Object clone()
    {
        ArrayObject newObj = new ArrayObject();
        newObj.setIndex(index);
        newObj.setName(name);
        newObj.setValue(value);
        return newObj;
    }
    /**
     * compares this object with another
     * @param o the object to compare this object with
     * @return true if all the values inside are equal
     */
    public boolean equals(Object o){
        if(!(o instanceof ArrayObject))
            return false;
        ArrayObject ao =(ArrayObject) o;
        if(ao.getValue()!=value)
            return false;
        if(ao.getIndex()!=index)
            return false;
        if(!ao.getName().equals(name))
            return false;
        return true;
    }
    /**
     * returns the index
     * @return an int value 
     */
    public int getIndex() {
        return index;
    }
    /**
     * sets the index
     * @param index an int value
     */
    public void setIndex(int index) {
        this.index = index;
    }
    /**
     * gets the name of the object
     * @return a string
     */
    public String getName() {
        return name;
    }
    /**
     * sets the name string
     * @param name a string
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * returns the stored value
     * @return a double
     */
    public double getValue() {
        return value;
    }
    /**
     * sets the value in the object
     * @param value a double
     */
    public void setValue(double value) {
        this.value = value;
    
    }
    /**
     * Returns a formatted string with all the values of the fields
     * @return 
     */
    @Override
    public String toString(){
        return String.format("%-15s%-5d%7f",name, index, value);
    }
}
