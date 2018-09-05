# -*- coding: utf-8 -*-
"""
Created on Wed Sep  5 12:58:21 2018

@author: nas7ybruises
"""

# Problem 6
# 20.0/20.0 points (graded)
# Write a function that meets the specifications below. You do not have to use dynamic programming.

# Hint: You might want to use bin() on an int to get a string, get rid of the first two characters,
# add leading 0's as needed, and then convert it to a numpy array of ints. Type help(bin) in the console.

# For example,
# If choices = [1,2,2,3] and total = 4 you should return either [0 1 1 0] or [1 0 0 1]
# If choices = [1,1,3,5,3] and total = 5 you should return [0 0 0 1 0]
# If choices = [1,1,1,9] and total = 4 you should return [1 1 1 0]
# More specifically, write a function that meets the specifications below:

# Paste your entire function (including the definition) in the box.

# Note: If you want to use numpy arrays, you should add the following 3 lines before your code:

# import os
# os.environ["OPENBLAS_NUM_THREADS"] = "1"
# import numpy as np

# And use np.METHOD_NAME in your code. Unfortunately, pylab does not work with the grader.


import os
os.environ["OPENBLAS_NUM_THREADS"] = "1"
import numpy as np

def powerSet(items):
    """
    Power set generator: get all possible combinations of a list’s elements
    Input:
        items is a list
    Output:
        returns 2**n combination lists one at a time using a generator
    Reference: edx.org 6.00.2x Lecture 2 - Decision Trees and dynamic programming
    """
    N = len(items)
    # enumerate the 2**N possible combinations
    for i in range(2**N):
        combo = bin(i)[2:]
        yield combo


def find_combination(choices, total):
    """
    choices: a non-empty list of ints
    total: a positive int
 
    Returns result, a numpy.array of length len(choices) 
    such that
        * each element of result is 0 or 1
        * sum(result*choices) == total
        * sum(result) is as small as possible
    In case of ties, returns any result that works.
    If there is no result that gives the exact total, 
    pick the one that gives sum(result*choices) closest 
    to total without going over.
    """
    best_sum = 0
    best_combo = np.array([0]*len(choices))
    for combo in powerSet(choices):
        combo = combo.zfill(len(choices))
        combo = np.array([int(elt) for elt in combo])
        
        elt_sum = 0
        for i in range(len(choices)):
            if combo[i] != 0:
                elt_sum += choices[i]
        if elt_sum <= total:
            if elt_sum > best_sum:
                best_sum = elt_sum
                best_combo = combo
            elif elt_sum == best_sum:
                if sum(combo) < sum(best_combo):
                    best_sum = elt_sum
                    best_combo = combo
    return best_combo


print(find_combination([1,2,2,3], 4))
print(find_combination([1,1,3,5,3], 5))




