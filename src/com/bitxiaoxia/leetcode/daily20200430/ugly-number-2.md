### 264. 丑数 II
编写一个程序，找出第 n 个丑数。

丑数就是只包含质因数 2, 3, 5 的正整数。
```
示例:

输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
说明:  

1 是丑数。
n 不超过1690。
```
> 
### 解题思路：
1, 2, 3, 2*2, 5, 2*3, 2*2*2, 3*3, 2*5, 2*2*3, 3*5, 3*2*3, 2*2*5，2*3*2*3,5*5
除了暴力以外，看不出来规律。。

看了官方题解：
* 方法一: 对数组中的每个数字*2,*3,*5,并继续添加到当前数组。直到数组长度>=1690即可。
* 方法二：方法1中每次都需要去重，观察上面的数组，采用三个指针来记录当前2，3，5分别处理到已有数据中的哪个位置。</p>
之后下一个数字只要选最小的插入即可。

