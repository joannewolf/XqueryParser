import java.util.List;
import java.util.String;

public class TreeNode{
    TreeNode parent;
    List<TreeNode> child;
    int children;
    String path;
    public TreeNode(){
        parent = null;
        children = 0;
        path = "";
    }
}
