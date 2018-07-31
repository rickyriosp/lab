# -*- coding: utf-8 -*-
"""
Created on Wed Jul 25 08:47:50 2018

@author: nas7ybruises
"""

# generate all combinations of N items
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
        combo = []
        for j in range(N):
            # test bit jth of integer i
            if (i >> j) % 2 == 1:   # i // 2**j
                combo.append(items[j])
        yield combo

# generate all combinations of N items
def yieldAllCombos(items):
    """
    Generates all combinations of N items into two bags, whereby each 
    item is in one or zero bags.

    Yields a tuple, (bag1, bag2), where each bag is represented as 
    a list of which item(s) are in each bag.
    """    
    N = len(items)
    # enumerate the 3**N possible combinations
    for i in range(3**N):
        bag1 = []
        bag2 = []
        for j in range(N):
            #print('right shift',i // (3 ** j))
            #print('remainder',(i // (3 ** j))%3)
            # test bit jth of integer i
            if (i // 3**j) % 3 == 1:    # shifting right with base 3
                bag1.append(items[j])
            elif (i // 3**j) % 3 == 2:
                bag2.append(items[j])
        yield (bag1, bag2)

test = [1, 2, 3]
