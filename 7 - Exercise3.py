# -*- coding: utf-8 -*-
"""
Created on Sun Aug 19 20:56:00 2018

@author: nas7ybruises
"""

def stdDevOfLengths(L):
    """
    L: a list of strings

    returns: float, the standard deviation of the lengths of the strings,
      or NaN if L is empty.
    """
    if len(L) == 0:
        return float('NaN')
    else:
        tot = 0
        for elt in L:
            tot += len(elt)
        mean = tot/len(L)
        tot = 0
        for elt in L:
            tot += (len(elt)-mean)**2
        stdd = (tot/len(L))**0.5
        return stdd

print(stdDevOfLengths(['a', 'z', 'p']))
print(stdDevOfLengths(['apples', 'oranges', 'kiwis', 'pineapples']))
print(stdDevOfLengths([]))
print(stdDevOfLengths(['cpm', 'fxekhiqwbk', 'j', 'pscdxeeypa', 'irzkdcremj']))

def getMeanAndStd(X):
    mean = sum(X)/float(len(X))
    tot = 0.0
    for x in X:
        tot += (x - mean)**2
    std = (tot/len(X))**0.5
    return mean, std

mean, std = getMeanAndStd([10, 4, 12, 15, 20, 5])
print(mean, std, std/mean)