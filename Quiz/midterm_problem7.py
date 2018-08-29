# -*- coding: utf-8 -*-
"""
Created on Sun Aug 12 13:09:53 2018

@author: nas7ybruises
"""

def solveit(test):
    """ 
    test, a function that takes an int parameter and returns a Boolean
    Assumes there exists an int, x, such that test(x) is True
    Returns an int, x, with the smallest absolute value such that test(x) is True 
    In case of ties, return any one of them. 
    """
    x = 0
    while test(x) == False:
        x += 1
        if test(x) == True:
            return x
        elif test(-x) == True:
            return -x
    return x

#### This test case prints 49 ####
def f(x):
    return (x+15)**0.5 + x**0.5 == 15
print(solveit(f))

#### This test case prints 0 ####
def f(x):
    return x == 0
print(solveit(f))