/*------------------------------------------------*/
//Uva Problem No: 11044
//Problem Name  : Searching for Nessy
//Type          : Ad hoc,simple math
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, n, m, ans;
    scanf("%d", &TC);
    while(TC--){
        scanf("%d %d", &n, &m);
        ans = 0;

        n /= 3;
        m /= 3;
        ans = n*m;
        printf("%d\n", ans);
    }
}
