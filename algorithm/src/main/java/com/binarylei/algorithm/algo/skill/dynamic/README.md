# 动态规划

## 递归代码模板

```java
public void recur(int level, int param) { 
  // 1. terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }

  // 2. process current logic 
  process(level, param); 

  // 3. drill down 
  recur( level: level + 1, newParam); 

  // 4. restore current status
}
```

## 分治代码模板

```java
private static int divide_conquer(Problem problem, ) {
  if (problem == NULL) {
    int res = process_last_result();
    return res;     
  }
  subProblems = split_problem(problem)
  
  res0 = divide_conquer(subProblems[0])
  res1 = divide_conquer(subProblems[1])
  
  result = process_result(res0, res1);
  
  return result;
}
```

## 动态规划定义

傻递归、傻分治、有条件的回溯

1. 动态规划和递归或者分治没有本质的区别(关键看有无最优的子结构)
2. 共性：找重复子问题
3. 差异性：最优子结构，中途可以淘汰次优解