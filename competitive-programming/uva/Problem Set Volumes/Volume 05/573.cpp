/*------------------------------------------------*/
//Uva Problem No: 573
//Problem Name  : The Snail
//Type          : Ad hoc, simulation, math
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    float H, U, D, F, h;
    int day;
    scanf("%f %f %f %f", &H, &U, &D, &F);
    while(H != 0){
        h = 0;
        day = 0;
        F = U*(F/100);
        if(H < U) printf("success on day 1\n");
        else while(1){
            day ++;
            if(U > 0) h += U;
            if(h > H) {printf("success on day %d\n", day); break;}
            h -= D;
            if(h < 0) {printf("failure on day %d\n", day); break;}
            U -= F;
        }
        scanf("%f %f %f %f", &H, &U, &D, &F);
    }
}
