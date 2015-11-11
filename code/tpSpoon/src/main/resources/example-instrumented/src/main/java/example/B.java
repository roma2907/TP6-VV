package example;


public class B  {


    public void mth1(int i) {

		vv.spoon.call.TreeCall.add(new vv.spoon.call.Node("B.void mth1(int)"));
        System.out.println("B.mth1(int i)");

        C c = new C(i);
        int result = c.mth1();

        System.out.println("result = " + result);

		vv.spoon.call.TreeCall.up();
    }

    public void mth2() {

		vv.spoon.call.TreeCall.add(new vv.spoon.call.Node("B.void mth2()"));
        System.out.println("B.mth2()");

		vv.spoon.call.TreeCall.up();
    }

}
