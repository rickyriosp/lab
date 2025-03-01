/*------------------------------------------------*/
//Uva Problem No: 11364
//Problem Name  : Parking
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, n, x, min, max;
    scanf("%d", &TC);
    while(TC--){
        scanf("%d", &n);
        min = 99;
        max = 0;
        while(n--){
            scanf("%d", &x);
            if(x < min) min = x;
            if(x > max) max = x;
        }
        printf("%d\n", (max-min)*2);
    }
}
