package algos;

public class Backtrack {
    public static void main(String[] args) {
        int[] a = new int[100];

        Backtrack backtrack = new Backtrack();
        backtrack.backtrack(a, 0, 3);
    }

    private void backtrack(int[] a, int k, int input) {
        int[] c = new int[100];
        if (isASolution(a, k, input)) {
            processSolution(a, k);
        } else {
            k = k + 1;
            int candidates = constructCandidates(a, k, input, c);
            for (int i = 0; i < candidates; i++) {
                a[k] = c[i];
                backtrack(a, k, input);
            }
        }
    }

    private int constructCandidates(int[] a, int k, int input, int[] c) {
        c[0] = 0;
        c[1] = 1;
        return 2;
    }

    private boolean isASolution(int[] a, int k, int n) {
        return k == n;
    }

    private void processSolution(int[] a, int k) {
        int i;

        System.out.print("{");
        for (i = 1; i <= k; i++) {
            if (a[i] == 0) {
                System.out.print(" " + i);
            }
        }
        System.out.println(" }");
    }
}
