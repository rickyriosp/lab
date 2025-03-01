# -*- coding: utf-8 -*
"""
Created on Sun Aug 12 12:19:31 2018

@author: nas7ybruises
"""

# generate all combinations of contiguous N items
def powerSet(items):
    """
    Power set generator: get all possible contiguus combinations of a list’s elements
    Input: items is a list
    Output: returns contiguous combination lists one at a time using a generator
    Reference: edx.org 6.00.2x Lecture 2 - Decision Trees and dynamic programming
    """
    N = len(items)
    # enumerate all the possible contiguous combinations of elements
    for i in range(N+1):
        # enumerate the combinations with i contiguous elements
        for j in range(N-i+1):
            combo = []
            # take element in position j+k
            for k in range(i):
                combo.append(items[j+k])
            yield combo

def max_contig_sum(L):
    """
    L, a list of integers, at least one positive
    Returns the maximum sum of a contiguous subsequence in L
    """
    maxSum = 0
    for elt in powerSet(L):
        if sum(elt) > maxSum:
            maxSum = sum(elt)
    return maxSum

# Test
#print(max_contig_sum([10, -5, 2]))
#print(max_contig_sum([1]))
# 