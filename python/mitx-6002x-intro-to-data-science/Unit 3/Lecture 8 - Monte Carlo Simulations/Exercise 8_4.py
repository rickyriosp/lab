# -*- coding: utf-8 -*-
"""
Created on Mon Aug 20 11:58:53 2018

@author: nas7ybruises
"""


import random
def noReplacementSimulation(numTrials):
    '''
    Runs numTrials trials of a Monte Carlo simulation
    of drawing 3 balls out of a bucket containing
    3 red and 3 green balls. Balls are not replaced once
    drawn. Returns the a decimal - the fraction of times 3 
    balls of the same color were drawn.
    '''
    tot = 0
    for trial in range(numTrials):
        balls = [0,0,0,1,1,1]
        draw = random.sample(balls, 3)
        if draw.count(draw[0]) == 3:
            tot += 1
    return tot/numTrials

random.seed(0)
print(noReplacementSimulation(10000))