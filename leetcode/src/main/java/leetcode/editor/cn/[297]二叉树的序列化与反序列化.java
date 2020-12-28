//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 示例: 
//
// 你可以将以下二叉树：
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//序列化为 "[1,2,3,null,null,4,5]" 
//
// 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这
//个问题。 
//
// 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。 
// Related Topics 树 设计


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.<pre>
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }</pre>
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "[]";
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> maxHeightQueue = new LinkedList<>();

        queue.offer(root);
        maxHeightQueue.offer(root);
        List<Integer> result = new ArrayList<>();
        while (!maxHeightQueue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node != null ? node.val : null); // 完全二叉树
            queue.offer(node != null ? node.left : null);
            queue.offer(node != null ? node.right : null);

            if (node != null) {
                TreeNode node2 = maxHeightQueue.poll();
                if (node2.left != null) maxHeightQueue.offer(node2.left);
                if (node2.right != null) maxHeightQueue.offer(node2.right);
            }
        }
        return result.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("[]".equals(data)) return null;
        data = data.substring(1, data.length() - 1);
        String[] arr = data.split("\\s*,\\s*");

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < arr.length; i += 2) {
            TreeNode p = queue.poll();
            if (p == null) {
                queue.offer(null);
                queue.offer(null);
                continue;
            }
            if (!"null".equals(arr[i])) {
                p.left = new TreeNode(Integer.parseInt(arr[i]));
            }
            queue.offer(p.left);
            if (i < arr.length - 1 && !"null".equals(arr[i + 1])) {
                p.right = new TreeNode(Integer.parseInt(arr[i + 1]));
            }
            queue.offer(p.right);
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}