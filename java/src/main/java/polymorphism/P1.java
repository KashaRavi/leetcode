package polymorphism;

/**
 * @author rkasha
 */
public class P1 {
    
    public void decision1(){
        decision2();
    }

    private static void decision2(){
        System.out.println("P1 Class");

    }

    public static void main(String[] args){
        P1 p1 = new P2();
        p1.decision2();
        decision2();
    }
}
