# Recursion 

## Basic Things
You will have these three things
- A condition to stop the recursion
- Calling recursion with smaller/cut inputs for all possible branches
- Perform the analysis you have to do either b4 or after the recursion call based on your needs
- After if you want to build from bottom up
- Before if you want to drill top down

## Stopping the recursion
- You can have a base condition and return from there
- You can stop the recursion tree from propagating (by stopping the recursion calls with conditions)

## Branching the recursion
- If only one possible solution call only once
- If the possibilities are finite just call those recursively
- If it's variable, call the recursion from a loop for all combinations

## Return Statements
- for void, it just stops the recursion
- for other return types, it stops the recursion. but also, sends some data to the parent call

## Top Down
info is passed from top to bottom
- you populate the base case and send it along down and return that shit
- first process then do recursive call
- You always mostly return plain numbers for both edge case
- The output will be argument which is the accumulator
- if u want for multiple branches then just sum, max, or of left and right


## Bottom Up
Info is passed from bottom up
- you build the base case at the return and build that up to output
- do recursive call get the result and process on the result


## Passing values from top to bottom
- You have arguments
- If u need to pass a copy of things, use primitive
- If u need to pass global stuff then pass Objects, else u can keep it on class level too


## Passing values from bottom to top
- Returning with the method is the only way
- init the base on top
- write accumulator with calling the method
- then return that result;
- For the bottom case, it will return the base case 
- For every other case, it will work on the returned item
