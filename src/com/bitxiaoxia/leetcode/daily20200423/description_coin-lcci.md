### 硬币
硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
```
示例1:

 输入: n = 5
 输出：2
 解释: 有两种方式可以凑成总金额:
5=5
5=1+1+1+1+1
示例2:

 输入: n = 10
 输出：4
 解释: 有四种方式可以凑成总金额:
10=10
10=5+5
10=5+1+1+1+1+1
10=1+1+1+1+1+1+1+1+1+1

说明：
注意:
你可以假设：
0 <= n (总金额) <= 1000000
```

```
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/coin-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

### 解题思路
从大到小拆解。
先把特殊值列出来。
f(1) = 1;
f(5) = 2;//5*1,5*0
f(10) = 1+3;//10*1,10*0[5*2,5*1,5*0]
f(25) = 1+2+4+6;//25*1,10*2[5*1,5*0],10*1[5*4..0],5*5..0
最简单的方法超时了，然后用这个方法，算出来前100个数找规律吧。
