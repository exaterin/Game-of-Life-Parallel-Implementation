# Game of Life - Java Parallel Implementation

This program implements the Game of Life, which is a cellular automaton devised by the British mathematician John Horton Conway in 1970. The program takes the size of a square array of cells and the number of steps to perform as input, reads the initial state of the cells from the standard input, and computes the state of the cells after the specified number of steps.

# Input Format

The input is read from the standard input and is written to the standard output. The first line of input contains two positive integers. The first number, N, represents both the width and height of the square array of cells. The second number, separated by a space, is the number of steps the program should perform. On the following lines, at each line, there are N characters (plus \n), each one representing either a dead cell - '_', or a live cell 'X'. Each line (except for the first line) contains exactly N characters plus the line end character '\n', the input contains N + 1 rows.
