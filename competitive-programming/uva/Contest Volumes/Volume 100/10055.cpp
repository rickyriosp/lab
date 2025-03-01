/*------------------------------------------------*/
//Uva Problem No: 10055
//Problem Name  : Hashmat the Brave Warrior
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    unsigned long long a, b;
    while(scanf("%llu %llu", &a, &b) != EOF){
        if(a > b) printf("%llu\n", a-b);
        else printf("%llu\n", b-a);
    }
}
