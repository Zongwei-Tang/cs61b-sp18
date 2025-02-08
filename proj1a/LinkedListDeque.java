public class LinkedListDeque <T>{
    private class Node {
        public T item;           // 存储的数据
        public Node prev;        // 指向前一个节点
        public Node next;  // 指向后一个节点
        private Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;       // 哨兵节点
    private int size;

    public LinkedListDeque() {
        sentinel=new Node(null,null,null);
        size=0;
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
    }

    public void addFirst(T item) {
        // 在前端添加元素
        // 更新相关链接
        // 增加size
        Node newNode= new Node(item,sentinel,sentinel.next);
        sentinel.next.prev=newNode;
        sentinel.next=newNode;
        size += 1;
    }

    public void addLast(T item){
        Node newnode=new Node(item,sentinel.prev,sentinel);
        sentinel.prev.next=newnode;
        sentinel.prev=newnode;
        size++;
    }

    public T removeFirst() {
        if (this.size==0){
            return null;
        }
        T a=sentinel.next.item;
        sentinel.next.next.prev=sentinel;
        sentinel.next=sentinel.next.next;
        size--;
        return a;
    }

    public T get(int index) {
        Node current=sentinel;
        for (int i=0;i<=index;i++){
            current=current.next;
        }
        return current.item;
    }

    public T removeLast(){
        if (this.size==0){
            return null;
        }
        T a=sentinel.prev.item;
        sentinel.prev.prev.next=sentinel;
        sentinel.prev=sentinel.prev.prev;
        size--;
        return a;
    }

    public T getRecursive(int index) {
        return helper(sentinel.next,0,index);
    }

    private T helper(Node n,int i,int index){
        if (i==index){
            return n.item;
        }
        else{
            return helper(n.next,i+1,index);
        }
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
        if (isEmpty()){
            System.out.print("");
        }
        else {
            Node current = sentinel.next;
            while (current.next != sentinel) {
                System.out.print(current.item + " ");
                current = current.next;
            }
            System.out.print(current.item);
        }
    }

}
