/*------------------------------------------------*/
//Uva Problem No: 12372
//Problem Name  : Packing for Holidays
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, l, w, h, c=0;
    scanf("%d", &TC);
    while(TC--){
        c++;
        scanf("%d %d %d", &l, &w, &h);
        if(l<=20 && w<=20 && h<=20) printf("Case %d: good\n", c);
        else printf("Case %d: bad\n", c);
    }
}
