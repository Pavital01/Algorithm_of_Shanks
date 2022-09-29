import java.util.ArrayList;

public class Main {
    static int findPrimitiveElem(int x){
        ArrayList<Integer> verification = new ArrayList<>();
        int modulo;
        //from 2 to x
        for (int i = 2; i < x; i++) {

            //for degree (0 - x)
            for (int j = 1; j < x; j++) {
                modulo = (int)(Math.pow(i,j) % x);

                if (verification.indexOf(modulo) == -1) {
                    //System.out.println("i = " + i + " j = " + j + " modula = " + modulo + " | It isn't");
                    verification.add(modulo);
                }
                else {
                    //System.out.println("i = " + i + " j = " + j + " modula = " + modulo + " | It is");
                    verification.clear();
                    break;
                }
                if (j == x - 1) return i;
            }
        }
        return 0;
    }

    static int bmod(int b,int n){
        int n0 = n,
            b0 = b,
            t0 = 0,
            t  = 1,
            q  = n0/b0,
            r = n0 - q * b0,
            temp;
        //System.out.println("n0 = " + n0 + " b0 = " + b0 + " r = " + r + " t0 = " + t0 + " t = " + t + " q = " + q);
        while (r > 0){
            temp = t0 - q * t;
            if (temp >=0)
                temp = temp % n;
            else
                temp = n - (-temp) % n;
            //System.out.println("temp = " + temp);
            n0 = b0;
            b0 = r;
            t0 = t;
            t  = temp;
            q  = n0/b0;
            r = n0 - q * b0;
            //System.out.println("n0 = " + n0 + " b0 = " + b0 + " r = " + r + " t0 = " + t0 + " t = " + t + " q = " + q);
            if (b0 != 1);
            else return t;
        }
        return t;
    }

    static int algorithmShanks(int b,int p){
        int a = findPrimitiveElem(p);
        int m = (int)Math.round(Math.sqrt(p - 1));
        ArrayList<Integer> L1 = new ArrayList<>();
        for (int j = 0; j <= m - 1; j++) {
            L1.add(j);
            L1.add((int)(Math.pow(a,m * j) % p));
        }
        ArrayList<Integer> L2 = new ArrayList<>();
        for (int i = 0; i <= m - 1; i++) {
            L2.add(i);
            L2.add(((b * bmod( (int)Math.pow(a,i),p ) ) % p ));
        }
        for (int i = 1; i < L1.size(); i = i + 2) {
            for (int j = 1; j < L2.size(); j = j + 2) {
                if (L1.get(i) == L2.get(j)) return m * L1.get(j - 1) + L2.get(i - 1) % (p - 1);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Rezult is " + algorithmShanks(5,17));
    }
}
