/*------------------------------------------------*/
//Uva Problem No: 11799
//Problem Name  : Horror Dash
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, n, s, ans;
    scanf("%d", &TC);
    for(int i=1; i<=TC; i++){
        printf("Case %d: ", i);
        scanf("%d", &n);
        ans = 1;
        while(n--){scanf("%d", &s); if(s > ans) ans = s;}
        printf("%d\n", ans);
    }
}
