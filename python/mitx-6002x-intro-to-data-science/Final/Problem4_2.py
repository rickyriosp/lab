# -*- coding: utf-8 -*-
"""
Created on Wed Sep  5 11:54:10 2018

@author: nas7ybruises
"""

# Problem 4

# Problem 4-2
# 20.0/20.0 points (graded)
# Write a function called getAverage(die, numRolls, numTrials), with the following specification:

# A run of numbers counts the number of times the same dice value shows up in consecutive rolls. 
# For example:
# - a dice roll of 1 4 3 has a longest run of 1
# - a dice roll of 1 3 3 2 has a longest run of 2
# - a dice roll of 5 4 4 4 5 5 2 5 has a longest run of 3

# When this function is called with the test case given in the file, it will return 5.312.
# Your simulation may give slightly different values.

# Paste your entire function (including the definition) in the box.

# Restrictions:
# - Do not import or use functions or methods from pylab, numpy, or matplotlib.
# - Do not leave any debugging print statements when you paste your code in the box.
# - If you do not see the return value being printed when testing your function, close the histogram window.


def getAverage(die, numRolls, numTrials):
    """
      - die, a Die
      - numRolls, numTrials, are positive ints
      - Calculates the expected mean value of the longest run of a number
        over numTrials runs of numRolls rolls.
      - Calls makeHistogram to produce a histogram of the longest runs for all
        the trials. There should be 10 bins in the histogram
      - Choose appropriate labels for the x and y axes.
      - Returns the mean calculated to 3 decimal places
    """
    runs = []
    for trial in range(numTrials):
        rolls = [die.roll() for i in range(numRolls)]
        longest = 1
        current = 1
        for i in range(numRolls-1):
            if rolls[i] == rolls[i+1]:
                current += 1
            else:
                current = 1
            if current > longest:
                longest = current
        runs.append(longest)
    makeHistogram(runs, 10, 'Length of longest run', 'Frequency', 'Histogram of longest runs')
    mean, std = getMeanAndStd(runs)
    return mean

