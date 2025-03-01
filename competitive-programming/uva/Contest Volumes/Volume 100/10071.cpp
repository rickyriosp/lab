/*------------------------------------------------*/
//Uva Problem No: 10071
//Problem Name  : Back to High School Physics
//Type          : Ad hoc, physics
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int v, t;
    while(scanf("%d %d", &v, &t) != EOF){
        if(t == 0) printf("0\n");
        else printf("%d\n", 2*v*t);
    }
}
