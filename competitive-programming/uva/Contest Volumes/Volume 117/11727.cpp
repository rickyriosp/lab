/*------------------------------------------------*/
//Uva Problem No: 11727
//Problem Name  : Cost Cutting
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, a, b, c;
    scanf("%d", &TC);
    for(int i=1; i<=TC; i++){
        scanf("%d %d %d", &a, &b, &c);
        printf("Case %d: ", i);
        if(!((a<b)&&(a<c))&&!((a>b)&&(a>c))) printf("%d\n", a);
        else if(!((b<a)&&(b<c))&&!((b>a)&&(b>c))) printf("%d\n", b);
        else if(!((c<a)&&(c<b))&&!((c>a)&&(c>b))) printf("%d\n", c);
    }
}
