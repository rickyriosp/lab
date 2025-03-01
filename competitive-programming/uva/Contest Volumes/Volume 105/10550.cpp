/*------------------------------------------------*/
//Uva Problem No: 10550
//Problem Name  : Combination Lock
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

#define deg 360/40

using namespace std;

int main()
{
    int start, a, b, c, ans;
    scanf("%d %d %d %d", &start, &a, &b, &c);
    while((start!=0)||(a!=0)||(b!=0)||(c!=0)){
        ans = 0;
        ans += 720;     //Two full right turns
        if(start >= a) ans += (start-a)*deg;
        else { ans += 360-(a-start)*deg;}
        ans += 360;     //One full left turn
        if(b > a) ans += (b-a)*deg;
        else { ans += 360-(a-b)*deg;}
        if(b >= c) ans += (b-c)*deg;
        else { ans += 360-(c-b)*deg;}
        printf("%d\n", ans);

        scanf("%d %d %d %d", &start, &a, &b, &c);
    }
}
