package synthesizer;// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first=0;
        last=0;
        rb= (T[])new Object [capacity];
        fillCount=0;
        this.capacity=capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()){
            throw new RuntimeException();
        }
        else {
            rb[last] = x;
            last = (last + 1) % capacity;
            fillCount++;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        T a;
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()){
            throw new RuntimeException();
        }
        else {
            a=rb[first];
            rb[first]=null;
            first = (first + 1) % capacity;
            fillCount--;
        }
        return a;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    public class ArrayRingBufferIterator implements Iterator<T>{
        private int currentpos;
        private int remain;
        public ArrayRingBufferIterator(){
            currentpos=first;
            remain=fillCount();
        }
        public boolean hasNext(){
            return remain>0;
        }
        public T next(){
            if (hasNext()){
                T a;
                a=rb[currentpos];
                currentpos=(currentpos+1)%capacity;
                remain--;
                return a;
            }
            else{
                throw new RuntimeException();
            }
        }
    }


    }

    // TODO: When you get to part 5, implement the needed code to support iteration.

