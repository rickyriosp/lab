/*------------------------------------------------*/
//Uva Problem No: 11172
//Problem Name  : Relational Operators
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, a, b;
    scanf("%d", &TC);
    while(TC--){
        scanf("%d %d", &a, &b);
        if(a<b) printf("<\n");
        else if(a>b) printf(">\n");
        else printf("=\n");
    }
}
