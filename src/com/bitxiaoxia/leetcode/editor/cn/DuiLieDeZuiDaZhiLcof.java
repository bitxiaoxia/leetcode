package com.bitxiaoxia.leetcode.editor.cn;

//请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都
//是O(1)。 
//
// 若队列为空，pop_front 和 max_value 需要返回 -1 
//
// 示例 1： 
//
// 输入: 
//["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
//[[],[1],[2],[],[],[]]
//输出: [null,null,null,2,1,2]
// 
//
// 示例 2： 
//
// 输入: 
//["MaxQueue","pop_front","max_value"]
//[[],[],[]]
//输出: [null,-1,-1]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= push_back,pop_front,max_value的总操作数 <= 10000 
// 1 <= value <= 10^5 
// 
// Related Topics 栈 Sliding Window 
// 👍 147 👎 0

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class DuiLieDeZuiDaZhiLcof{
    public static void main(String[] args) {
        MaxQueue solution = new DuiLieDeZuiDaZhiLcof().new MaxQueue();

        solution.push_back(3);
        solution.push_back(1);
        solution.push_back(2);
        solution.max_value();
        solution.pop_front();
        solution.max_value();

    }

//leetcode submit region begin(Prohibit modification and deletion)
class MaxQueue {
    //单调递减队列，先进先出。
    //每次将新数字加入到队列中，并删除在他之后的数字。
    Deque<Integer> maxQueue;
    Deque<Integer> queue;
    public MaxQueue() {
        maxQueue = new ArrayDeque<>();
        queue = new ArrayDeque<>();
    }

    public int max_value() {
        if(maxQueue.size() != 0){
            return maxQueue.peek();
        }else{
            return -1;
        }
    }

    public void push_back(int value) {
        if(maxQueue.size() != 0 ){
            if(value > maxQueue.peek()){
                maxQueue.clear();
            }else{
                //把maxQueue中小于当前值的数字删掉
                while(maxQueue.size()!=0 && maxQueue.peekLast() < value) {
                    maxQueue.pollLast();
                }
            }
        }
        maxQueue.add(value);
        queue.add(value);
    }

    public int pop_front() {
        if(queue.size()!=0){
            int val = queue.poll();
            if(val == maxQueue.peek()){
                maxQueue.poll();
            }
            return val;
        }else{
            return -1;
        }
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
//leetcode submit region end(Prohibit modification and deletion)

}