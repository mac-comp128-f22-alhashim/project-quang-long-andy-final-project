public class recurseATree {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(0);
        node.recurseGenerate(node);
        System.out.println(node.left.data);
    }
}

class TreeNode {
    private Integer depth;
    public TreeNode left; public TreeNode right;
    public int data;
    public TreeNode(int nodeData) {
        this.data = nodeData;
    }

    public void isEven() {
        if(data%2==0) {
            System.out.println("EVEN");
        }
        else {
            System.out.println("ODD");
        }
    }
    public void recurseGenerate(TreeNode node) {
        if (node.data == 4) {
            return;
        }
        System.out.println(node.data);
        int childData = node.data+1;
        node.isEven();
        node.left = new TreeNode(childData);
        node.right = new TreeNode(childData);
        recurseGenerate(node.left);
        recurseGenerate(node.right);
    }
}
