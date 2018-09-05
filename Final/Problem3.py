# -*- coding: utf-8 -*-
"""
Created on Wed Sep  5 11:21:42 2018

@author: nas7ybruises
"""

# Problem 3
# 20.0/20.0 points (graded)
# You have a bucket with 4 red balls and 4 green balls.
# You draw 3 balls out of the bucket. Assume that once you draw a ball
# out of the bucket, you don't replace it. You draw 3 balls.

# Write a Monte Carlo simulation that meets the specifications below.
# Feel free to write a helper function if you wish.

# Paste your entire function (including the definition) in the box.

# Restrictions:
# - Do not import or use functions or methods from pylab, numpy, or matplotlib.
# - Do not leave any debugging print statements when you paste your code in the box.

import random

def drawing_without_replacement_sim(numTrials):
    '''
    Runs numTrials trials of a Monte Carlo simulation
    of drawing 3 balls out of a bucket containing
    4 red and 4 green balls. Balls are not replaced once
    drawn. Returns a float - the fraction of times 3 
    balls of the same color were drawn in the first 3 draws.
    '''
    total = 0
    balls = ['r','r','r','r','g','g','g','g']
    for trial in range(numTrials):
        selected = random.sample(balls, 3)
        if selected.count(selected[0]) == 3:
            total += 1
    return total / numTrials


random.seed(0)
print(drawing_without_replacement_sim(100))