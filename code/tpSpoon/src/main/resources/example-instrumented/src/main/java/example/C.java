package example;

public class C  {
    private int i;

    public C(int i) {
        System.out.println("C.C(int i)");
        this.i = i;
    }

    public int mth1() {

		vv.spoon.call.TreeCall.add(new vv.spoon.call.Node("C.int mth1()"));
       System.out.println("C.mth1()");

		vv.spoon.call.TreeCall.up();
        return 100/i;
    }
}
