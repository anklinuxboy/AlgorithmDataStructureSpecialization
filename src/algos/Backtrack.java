package algos;

public class Backtrack {
    public static void main(String[] args) {
        int[] a = new int[1000];

        Backtrack backtrack = new Backtrack();
        backtrack.backtrack(a, 0, 3);
    }

    private void backtrack(int[] a, int k, int input) {
        int[] c = new int[1000];
        if (isASolution(a, k, input)) {
            processSolutionPerm(a, k);
        } else {
            k = k + 1;
            int candidates = constructCandidatesPerm(a, k, input, c);
            for (int i = 0; i < candidates; i++) {
                a[k] = c[i];
                backtrack(a, k, input);
            }
        }
    }

    private int constructCandidatesPerm(int[] a, int k, int input, int[] c) {
        boolean[] inPerm = new boolean[1000];

        for (int i = 1; i < 1000; i++) {
            inPerm[i] = false;
        }

        for (int i = 0; i < k; i++) {
            inPerm[a[i]] = true;
        }

        int nCandidates = 0;
        for (int j = 1; j <= input; j++) {
            if (!inPerm[j]) {
                c[nCandidates] = j;
                nCandidates += 1;
            }
        }

        return nCandidates;
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

    private void processSolutionPerm(int[] a, int k) {
        for (int i = 1; i <= k; i++) {
            System.out.print(" " + a[i]);
        }

        System.out.println();
    }
}
