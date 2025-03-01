# -*- coding: utf-8 -*-
"""
Created on Mon Aug  6 15:44:14 2018

@author: nas7ybruises
"""

def deterministicNumber():
    '''
    Deterministically generates and returns an even number between 9 and 21
    '''
    return 10

import random
def stochasticNumber():
    '''
    Stochastically generates and returns a uniformly distributed even number between 9 and 21
    '''
    return random.randrange(10, 21, 2)
