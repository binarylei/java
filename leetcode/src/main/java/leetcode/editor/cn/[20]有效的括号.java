//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;

        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');

        Stack<Character> stack = new Stack<>();
        Character c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (map.containsKey(c)) {   // 左
                if (stack.isEmpty()) return false;
                if (map.get(c) != stack.pop()) return false;
            } else                     // 右
                stack.push(c);
        }
        return stack.isEmpty();

    }
}


//class Solution {
//    public boolean isValid(String s) {
//        if (s == null || s.length() == 0) return true;
//
//        Map<Character, Character> map = new HashMap<>();
//        map.put('}', '{');
//        map.put(']', '[');
//        map.put(')', '(');
//        map.put('{', '}');
//        map.put('[', ']');
//        map.put('(', ')');
//
//        Stack<Character> allStack = new Stack<>();
//        Stack<Character> endStack = new Stack<>();
//        for (int i = 0; i < s.length(); i++) {
//            allStack.push(s.charAt(i));
//        }
//
//        Character end;
//        Character start;
//        while (!allStack.isEmpty()) {
//            end = allStack.pop();
//            if (end == '}' || end == ']' || end == ')') {
//                endStack.push(end);
//            } else {
//                if (endStack.isEmpty()) return false;
//
//                start = endStack.pop();
//                if (map.get(end) != start) return false;
//            }
//        }
//        return endStack.isEmpty();
//
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
