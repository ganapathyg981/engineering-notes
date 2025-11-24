# Prefix Array problems

Mostly when contiguous sub-array problem comes. we can use prefix/suffix compute to avoid O(n^2) computations

## Techniques
 - Balance between two quantities
 - Equal counts condition
 - Longest / first / maximum subarray with some constraint


## Problems

### Contiguous subarray
https://leetcode.com/problems/contiguous-array/description/

## Intuition
- You can brute force by doing i=0->n and  j=i->n 
- Do the logic, but thats O(n^2)
- Other way is to keep a count variable
- Count 1 as +1 and 0 as -1
- keep track of which count at which index
- If u recur a count again whihc was in track that means.. after all that 0s and 1s u went through, the sum is same. so they all cancelled out.
- Find that length diff and get the max length