/*------------------------------------------------*/
//Uva Problem No: 10300
//Problem Name  : Ecological Premium
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, f, area, num, coef;
    long ans;
    scanf("%d", &TC);
    while(TC--){
        scanf("%d", &f);
        ans = 0;
        while(f--){
            scanf("%d %d %d", &area, &num, &coef);
            ans += (area*coef);
        }
        printf("%ld\n", ans);
    }
}
