import random
import pylab

# Global Variables
MAXRABBITPOP = 1000
CURRENTRABBITPOP = 500
CURRENTFOXPOP = 30

def rabbitGrowth():
    """ 
    rabbitGrowth is called once at the beginning of each time step.

    It makes use of the global variables: CURRENTRABBITPOP and MAXRABBITPOP.

    The global variable CURRENTRABBITPOP is modified by this procedure.

    For each rabbit, based on the probabilities in the problem set write-up, 
      a new rabbit may be born.
    Nothing is returned.
    """
    # you need this line for modifying global variables
    global CURRENTRABBITPOP
    
    for _ in range(CURRENTRABBITPOP):
        # rabbit reproducing
        if CURRENTRABBITPOP >= 10:
            if random.random() <= (1.0 - CURRENTRABBITPOP/MAXRABBITPOP):
                CURRENTRABBITPOP += 1

       
def foxGrowth():
    """ 
    foxGrowth is called once at the end of each time step.

    It makes use of the global variables: CURRENTFOXPOP and CURRENTRABBITPOP,
        and both may be modified by this procedure.

    Each fox, based on the probabilities in the problem statement, may eat 
      one rabbit (but only if there are more than 10 rabbits).

    If it eats a rabbit, then with a 1/3 prob it gives birth to a new fox.

    If it does not eat a rabbit, then with a 1/10 prob it dies.

    Nothing is returned.
    """
    # you need these lines for modifying global variables
    global CURRENTRABBITPOP
    global CURRENTFOXPOP

    for _ in range(CURRENTFOXPOP):
        # rabbit dying
        if CURRENTRABBITPOP > 10:
            if random.random() <= (CURRENTRABBITPOP/MAXRABBITPOP):        
                CURRENTRABBITPOP -= 1
                # fox reproducing
                if CURRENTFOXPOP >= 10:
                    if random.random() <= 1/3:
                        CURRENTFOXPOP += 1
        else:
            # fox dying
            if CURRENTFOXPOP > 10:                
                if random.random() <= 1/10:
                    CURRENTFOXPOP -= 1


def runSimulation(numSteps):
    """
    Runs the simulation for `numSteps` time steps.

    Returns a tuple of two lists: (rabbit_populations, fox_populations)
      where rabbit_populations is a record of the rabbit population at the 
      END of each time step, and fox_populations is a record of the fox population
      at the END of each time step.

    Both lists should be `numSteps` items long.
    """
    fox_pop = []
    rabbit_pop = []
    for step in range(numSteps):
        rabbitGrowth()
        foxGrowth()
        fox_pop.append(CURRENTFOXPOP)
        rabbit_pop.append(CURRENTRABBITPOP)
    return rabbit_pop, fox_pop


rabbits, foxes = runSimulation(200)
pylab.title('Rabbits vs Foxes population')
pylab.xlabel('Time step')
pylab.ylabel('Population')
pylab.plot(rabbits, 'b-', label='Rabbits')
pylab.plot(foxes, 'r-', label='Foxes')
pylab.legend(loc='best')


