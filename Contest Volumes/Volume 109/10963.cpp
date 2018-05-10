/*------------------------------------------------*/
//Uva Problem No: 10963
//Problem Name  : The Swallowing Ground
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, W, a, b, min, max;
    scanf("%d", &TC);
    while(TC--){
        scanf("%d", &W);
        min = 200;
        max = 0;
        while(W--){
            scanf("%d %d", &a, &b);
            if(a-b<min) min = a-b;
            if(a-b>max) max = a-b;
        }
        if(min == max) printf("yes\n");
        else printf("no\n");
        if(TC>0) printf("\n");
    }
}
