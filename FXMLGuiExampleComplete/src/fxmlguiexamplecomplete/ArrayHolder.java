/*
 * License: this is an example created by Daniel Scanteianu at Stony Brook University
 * Use, reuse, modify, etc. as you wish. 
 */
package fxmlguiexamplecomplete;

/**
 * Does stuff with an array of arrayObjects. 
 * It automatically expands beyond its original capacity when objects get added.
 * This is is somewhat similar to the HW for 214
 * @author Dan
 */
public class ArrayHolder {
    /**
     * Builds an array holder with initial capacity maxSize
     * @param maxSize int number of objects to be held. will be increased to 2 if less than 2.
     */
    public ArrayHolder(int maxSize)
    {
        if(maxSize<2)
            maxSize=2;//minimum 2 for the add method to work
        array=new ArrayObject[maxSize];
    }
    /**
     * makes default array if no argument is present.
     */
    public ArrayHolder ()
    {
       this(2);//the add method fails with less than two
    }
    /**
     * adds an object to the end of the non-null section of the array
     * @param o 
     */
    public void addObject(ArrayObject o)
    {  
       this.addObject(o, numObjects);
    }
    /**
     * sets the cell of the array with the given index to the given object
     * @param index the index of the cell to set
     * @param o the object to set it to
     */
    public void set(int index, ArrayObject o)
    {
        array[index]=o;
    }
    /**
     * Returns the object at a certain index
     * @param index the index to check
     * @return an ArrayObject
     * @throws ArrayIndexOutOfBoundsException if the position specified doesn't exist.
     */
    public ArrayObject get(int index ) throws ArrayIndexOutOfBoundsException
    {
         if(index<0||index>=array.length)
           throw new ArrayIndexOutOfBoundsException();
         return array[index];
    }
    /**
     * adds an object at a given index, expanding the array if needed.
     * O(n).
     * @param o the object to add
     * @param index the index to add it at.
     * @throws ArrayIndexOutOfBoundsException if the position specified doesn't exist and isn't one greater than the last position that does.
     */
    public void addObject(ArrayObject o, int index)throws ArrayIndexOutOfBoundsException{
        if(index>array.length)
            throw new ArrayIndexOutOfBoundsException();
        ArrayObject last;
        if(index==array.length)
            last = o;
        else{
        last=array[array.length-1];
        for(int i=array.length-2; i>index; i--)
        {
            array[i+1]=array[i];
        }
        numObjects++;
        set(index, o);
        }
        if(last!=null)
        {
           ArrayObject newArray[]= new ArrayObject[array.length+1];
            for(int i=0; i<array.length; i++)
                newArray[i]=array[i];
            newArray[array.length]=last;
            array=newArray;
            
        }
        
    }
    /**
     * removes an object from the array, shifts everything above it down a cell
     * @param index
     * @throws ArrayIndexOutOfBoundsException if the specified index doesn't exist
     */
    public void removeObject(int index)   throws ArrayIndexOutOfBoundsException
     {
       if(index<0||index>=array.length)
           throw new ArrayIndexOutOfBoundsException();
        for(int i=index; i<array.length-1; i++)
        {
            array[i]=array[i+1];
        }
        array[array.length-1]=null;
        numObjects--;
    }
    /**
     * returns the first index where an object equaling O (in values) is found.
     * @param o the object to search for
     * @return -1 if not found, else, index.
     */
    public int find(ArrayObject o){
        for(int i=0; i<array.length; i++)
        {
            if(o.equals(array[i]))
                return i;
        }
        return -1;
    }
    /**
     * returns the number of objects the user has added
     * @return the number of objects added to the arrray minus the number removed
     */
    public int getNumObjects() {
        return numObjects;
    }
    /**
     * Returns a string with all of the values of the fields of the objects inside
     * @return a formatted string of all the values
     */
    @Override
    public String toString()
    {
        String s="";
        for(int i=0; i<numObjects; i++){
            if(array[i]!=null)
                s+=String.format("%3d. ",i)+array[i].toString()+"\n";
        }
        return s;
    }
    private ArrayObject[] array;
    
    private int numObjects;
}
