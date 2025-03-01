# -*- coding: utf-8 -*-
"""
Created on Sun Aug 12 11:47:12 2018

@author: nas7ybruises
"""

def greedySum(L, s):
    """ input: s, positive integer, what the sum should add up to
               L, list of unique positive integers sorted in descending order
        Use the greedy approach where you find the largest multiplier for 
        the largest value in L then for the second largest, and so on to 
        solve the equation s = L[0]*m_0 + L[1]*m_1 + ... + L[n-1]*m_(n-1)
        return: the sum of the multipliers or "no solution" if greedy approach does 
                not yield a set of multipliers such that the equation sums to 's'
    """
    maxCost = s
    m = [0] * len(L)
    for i in range(len(L)):
        m_n = 1
        while m_n * L[i] <= maxCost:
            m_n += 1
        m[i] = m_n-1
        maxCost -= (m_n-1) * L[i]
    return sum(m) if maxCost == 0 else 'no solution'

# Test
#print(greedySum([10, 5, 1], 14))
