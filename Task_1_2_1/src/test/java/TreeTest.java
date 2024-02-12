import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.example.Tree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * test class.
 */
public class TreeTest {

    @Test
    void testDfsIterator() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();

        List<String> DfsArray = new ArrayList<>();
        Iterator iter = tree.iteratorDfs();
        while (iter.hasNext()) {
            DfsArray.add(iter.next().toString());
        }
        
        String[] arr = new String[]{"R1", "R2", "D", "C", "A"};
        if (arr.length != DfsArray.size()) {
            Assertions.fail();
        }

        for (int i = 0; i < arr.length; i++) {
            if (DfsArray.get(i) != arr[i]) {
                Assertions.fail();
            }
        }
    }

    @Test
    void testBfsIterator() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.remove();

        List<String> BfsArray = new ArrayList<>();
        Iterator iter = tree.iteratorBfs();
        while (iter.hasNext()) {
            BfsArray.add(iter.next().toString());
        }
        String[] arr = new String[]{"R1", "A", "R2", "C", "D"};
        if (arr.length != BfsArray.size()) {
            Assertions.fail();
        }

        for (int i = 0; i < arr.length; i++) {
            if (BfsArray.get(i) != arr[i]) {
                Assertions.fail();
            }
        }
    }

    @Test
    void testEquality(){
        Tree<String> tree1 = new Tree<>("R1");
        tree1.addChild("A");
        tree1.addChild("B");

        Tree<String> tree2 = new Tree<>("R1");
        tree2.addChild("A");
        tree2.addChild("B");
        var a = tree1.addChild("C");
        a.remove();
        Assertions.assertEquals(tree1, tree2);
    }
}