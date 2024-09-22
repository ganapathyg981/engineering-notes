# Arrays and Hashing
- Can store same type data in continuous memory
- Randomly access elements
## Intuitions
- Find duplicates -> use set
- Find frequency -> use map
## Problems
### Contains Duplicate
Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.
> Input: nums = [1, 2, 3, 3]  
Output: true
#### Intuition
- to find duplicates we have a set
#### Solution
- Have a set
- Loop through array and keep adding to set
- Also, before adding keep a check if element already exists in set
- If Exist return true, else return false at the end
### Valid Anagram
Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.

An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
Example 1:
> Input: s = "racecar", t = "carrace"  
Output: true

Example 2:
> Input: s = "jar", t = "jam"  
Output: false
#### Intuition
- You need freq of each char and have to equate
- You 
### Anagram Groups
### Two Sum
### Product of the element except itself
Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].
Each product is guaranteed to fit in a 32-bit integer.
Follow-up: Could you solve it in O(n) time without using the division operation?
Example 1:
> Input: nums = [1,2,4,6]
Output: [48,24,12,8]
#### Intuition
- keep multiplication of all elements on left
- then multiply that with every element on right in reverse
#### Solution
- input [1,2,4,6] 
- pre product array init [0,0,0,0]
- Fill preproduct from left first is 1. This is for anyindex, its product for all items to left
- [1,1,2,8]
- so u got the last elements solution
- Now, for proper solution you need preproduct * all the elements inright
- keep right accumulator as 1, start from end and pickup numbers
> [1 * (1 * 6 * 4 * 2), 1 * (1 * 6 * 4), 2 * (1 * 6), 8 * (1)]
- output -> [48,24,12,8]
### Find Most K occurring elements
### String Encode and Decode
### Longest Consecutive sequence
### Best time to buy and sell stocks