# Polynomial interpolation 

Java written program that solves the problem of polynomial interpolation with multiple nodes.
Finds a polynomial function that fits the given data.

## Input specification
In the first line of standard input, user enters a number of rows of two-dimentsional array of data. Then we specify the number of 
columns. First, we insert successive integer values of $x_i$ into the data array, such that $0 ≤ i < m$, where $m$ is a number of columns
and $x_0 < x_1 < ... < x_i$. Then, user inputs integer values of the function $f^j(x_i)$, assuming that $0 ≤ j ≤n$, where $n + 2$ is a number of rows.
If there is no value, insert N.

## Output specification
The program simulates interpolation with multiple nodes and finds a polynomial function that fits the given data in Newton form 
$$H(x) = b_0 + b_1(x - t_0) + ... + b_m(x-t_0) * ... * (x - t_{m - 1})$$

## Example

| $x_i$  | 0 | 1 | 2 |
|-|-|-|-|
| $f(x_i)$  |0|1|2|
| $f'(x_i)$  |1|0||
| $f''(x_i)$ ||2||

### Input

4 </br>
3 </br>
0 </br>
1 </br>
2 </br>
0 </br>
1 </br>
2 </br>
1 </br>
0 </br>
N </br>
N </br>
2 </br>
N </br>

### Output

$H(x) = (0) + (1)(x - 0) + (-1)(x - 0)(x - 0)(x - 1) + (3)(x - 0)(x - 0)(x - 1)(x - 1) + (-2)(x - 0)(x - 0)(x - 1)(x - 1)(x - 1)$
