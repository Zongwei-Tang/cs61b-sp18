import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void test1() {
        StudentArrayDeque<Integer> a = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> c=new ArrayDequeSolution<>();
        int i;
        StringBuilder message=new StringBuilder();
        for(i=0;i<100;i++){
            int b = StdRandom.uniform(4);
            int d=StdRandom.uniform(1000);
            if (c.size()==0){
                a.addFirst(d);
                c.addFirst(d);
                message.append(String.format("addFirst(%d)\n",d));
            }
            else {
                if (b == 0) {
                    a.addFirst(d);
                    c.addFirst(d);
                    message.append(String.format("addFirst(%d)\n",d));
                }
                if (b == 1) {
                    a.addLast(d);
                    c.addLast(d);
                    message.append(String.format("addLast(%d)\n",d));
                }
                if (b == 2) {
                    Integer e=a.removeLast();
                    Integer f=c.removeLast();
                    message.append("removeLast()\n");
                    assertEquals(message.toString(),f,e);
                }
                if (b == 3) {
                    Integer e=a.removeFirst();
                    Integer f=c.removeFirst();
                    message.append("removeFirst()\n");
                    assertEquals(message.toString(),f,e);
                }
            }
        }


    }

}
