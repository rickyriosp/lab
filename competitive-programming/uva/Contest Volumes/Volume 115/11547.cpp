/*------------------------------------------------*/
//Uva Problem No: 11547
//Problem Name  : Automatic Answer
//Type          : Ad hoc, simulation, simple math
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, n;
    scanf("%d", &TC);
    while(TC--){
        scanf("%d", &n);
        n = (((n*567)/9+7492)*235/47)-498;
        n = (n/10)%10;
        if(n >= 0) printf("%d\n", n);
        else printf("%d\n", -n);
    }
}
