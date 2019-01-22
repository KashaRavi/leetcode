package polymorphism;

/**
 * @author rkasha
 */
public class P2 extends P1 {

    public static void m(){
        System.out.println("m() in P2");
    }

    public void p() {
        System.out.println("p() in P2");
    }

    //uncomment q() method to learn that a instance method cannot override a static method q() from P1
    //whereas adding static keyword will let u compile the code.
    // Note: adding static keyword does not mean that we are overriding q() in P1 with q() in this class
    /*
    public void q() {
        System.out.println("q() in P1");
    }
    */

}
