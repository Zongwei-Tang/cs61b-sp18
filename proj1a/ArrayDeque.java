public class ArrayDeque <T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextlast;

    public ArrayDeque(){
        items=(T[]) new Object[8];
        size=0;
        nextFirst=0;
        nextlast=7;
    }

    public int minusone(int i){
        return (i-1+items.length)%items.length;
    }

    public int plusone(int i){
        return (i+1)%items.length;
    }

    public void addFirst(T item){
        if (size==items.length){
            resize(2*size);
        }
        items[nextFirst]=item;
        nextFirst=minusone(nextFirst);
        size++;
    }

    public void addLast(T item){
        if (size==items.length){
            resize(size*2);
        }
        items[nextlast]=item;
        nextlast=plusone(nextlast);
        size++;
    }

    public T get(int index){
        int a=nextFirst+1;
        for (int i=1;i<=index;i++){
            a=plusone(a);
        }
        return items[a];
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
            System.out.print(get(i)+" ");
        }
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        T d=get(0);
        items[plusone(nextFirst)]=null;
        nextFirst=plusone(nextFirst);
        size--;
        if (items.length >= 16 && size < items.length * 0.25) {
            resize(items.length / 2);
        }
        return d;
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        T a=get(items.length-1);
        items[minusone(nextlast)]=null;
        nextlast=minusone(nextlast);
        if (items.length>=16 && size<items.length*0.25){
            resize(items.length/2);
        }
        return a;
    }

    public void resize(int capacity){
        T[] a=(T[]) new Object[capacity];
        System.arraycopy(items,plusone(nextFirst),a,1,size);
        nextFirst=0;
        nextlast=plusone(size);
        items=a;
    }
}
