public class ArrayDeque <T> {
    private T[] items;
    private int size;
    public ArrayDeque(){
        items=(T[]) new Object[8];
        size=0;
    }
    public void addFirst(T item){
        if (size==items.length){
            resize(size+1);
        }
        T [] b=(T[]) new Object[size+1];
        b[0]=item;
        System.arraycopy(items,0,b,1,size);
        items=b;
    }

    public void addLast(T item){
        if (size==items.length){
            resize(size+1);
        }
        items[size]=item;
        size++;
    }

    public T get(int index){
        return items[index];
    }

    public boolean isEmpty(){
        if (size==0){
            return true;
        }
        else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (int i=0;i<size;i++){
            System.out.print(items[i]+" ");
        }
    }

    public T removeFirst(){
        T d=items[0];
        T[] c=(T[]) new Object[size-1];
        System.arraycopy(items,1,c,0,size-1);
        items=c;
        return d;
    }

    public T removeLast(){
        T a=items[size-1];
        T[] c=(T[]) new Object[size-1];
        System.arraycopy(items,0,c,0,size-1);
        items=c;
        return a;
    }

    public void resize(int capacity){
        T[] a=(T[]) new Object[capacity];
        System.arraycopy(items,0,a,0,size);
        items=a;
    }
}
