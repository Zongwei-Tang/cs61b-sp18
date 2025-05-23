package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{
    protected int fillCount;
    protected int capacity;
    public int capacity(){
        return capacity;
    }   // return size of the buffer
    public int fillCount(){
        return fillCount;
    }
}
