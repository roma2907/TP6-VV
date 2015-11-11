package example;


public class A  {

    public static void main(String[] args) {


		vv.spoon.call.TreeCall.add(new vv.spoon.call.Node("A.void main(java.lang.String[])"));
        System.out.println("A.main(String[] args)");

        A a = new A();
        a.mth1(Integer.parseInt(args[0]));

		vv.spoon.call.TreeCall.up();

    }

    public void mth1(int count) {

		vv.spoon.call.TreeCall.add(new vv.spoon.call.Node("A.void mth1(int)"));
        System.out.println("A.mth1(int count)");

        B b = new B();
        for(int i = 0; i < count; i++) {
            try {
                b.mth1(i);
                b.mth2();
            } catch (Exception e) {

		vv.spoon.call.TreeCall.up("A.void mth1(int)");
                System.err.println("error in A.mth1(int count)");
            }
        }

		vv.spoon.call.TreeCall.up();

    }

}
