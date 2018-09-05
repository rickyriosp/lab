# -*- coding: utf-8 -*-
"""
Created on Wed Sep  5 11:32:49 2018

@author: nas7ybruises
"""

# Problem 4

# Problem 4-1
# 0.0/10.0 points (graded)
# You are given the following function and class and function specifications 
# for the two coding problems on this page (also available in this file, die.py):

# Write a function called makeHistogram(values, numBins, xLabel, yLabel, title=None),
# with the following specification:

# Paste your entire function (including the definition) in the box.

# Restrictions:
# - Do not paste import pylab in the box.
# - You should only be using the pylab.hist, pylab.title, pylab.xlabel, pylab.ylabel, 
#   pylab.show functions from the pylab module.
# - Do not leave any debugging print statements when you paste your code in the box.

import pylab

def makeHistogram(values, numBins, xLabel, yLabel, title=None):
    """
      - values, a list of numbers
      - numBins, a positive int
      - xLabel, yLabel, title, are strings
      - Produces a histogram of values with numBins bins and the indicated labels
        for the x and y axes
      - If title is provided by caller, puts that title on the figure and otherwise
        does not title the figure
    """
    if title != None:
        pylab.title(title)
    pylab.xlabel(xLabel)
    pylab.ylabel(yLabel)
    pylab.hist(values, bins=numBins)
    pylab.show()


makeHistogram([1,3,4,2,1], 5, 'x axis', 'y axis')