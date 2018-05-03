/*------------------------------------------------*/
//Uva Problem No: 100
//Problem Name  : The 3n + 1 problem
//Type          : Ad hoc,Simulation.
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int i, j, n, cycle, max_cycle;
    while(scanf("%d %d", &i, &j) != EOF){
        max_cycle = 1;
        printf("%d %d ", i, j);
        if(i>j){
            int temp = i;
            i = j;
            j = temp;
        }
        for(; i<=j; i++){
            n = i;
            cycle = 1;
            while (n != 1){
                if (n%2 != 0){
                    n = (3*n) + 1;
                    n = n>>1;
                    cycle += 2;
                }
                else{
                    n = n>>1;
                    cycle++;
                }
            }
            if (cycle > max_cycle)
                max_cycle = cycle;
        }
        printf("%d\n", max_cycle);
    }
}
