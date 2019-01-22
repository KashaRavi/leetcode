package polymorphism;

/**
 * @author rkasha
 */
public class P1 {
    
    public static void m(){
        System.out.println("m() in P1");
    }

    public static void n(){
        System.out.println("n() in P1");
    }

    public void p() {
        System.out.println("p() in P1");
    }

    public static void q() {
        System.out.println("q() in P1");
    }

    public static void main(String[] args){
        //accessing through class
        System.out.println("through class variable");
        P1.m();
        P2.m();

        P1.n();
        P2.n();

        //accessing through instance
        System.out.println("though instance variable");
        P1 p1 = null;

        System.out.println("static methods");
        p1 = new P1();
        p1.m();
        p1 = new P2();
        p1.m();

        System.out.println("instance methods");
        p1 = new P1();
        p1.p();
        p1 = new P2();
        p1.p();
    }
}
