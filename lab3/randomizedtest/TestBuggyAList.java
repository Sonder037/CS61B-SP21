package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int opt = StdRandom.uniform(0, 4);
            if (opt == 0) {
                int value = StdRandom.uniform(0, 100);
                correct.addLast(value);
                broken.addLast(value);
            } else if (opt == 1) {
                assertEquals(correct.size(), broken.size());
            } else if (correct.size() == 0) {
                continue;
            } else if (opt == 2) {
                assertEquals(correct.getLast(), broken.getLast());
            } else if (opt == 3) {
                assertEquals(correct.removeLast(), broken.removeLast());
            }
        }
    }
}
