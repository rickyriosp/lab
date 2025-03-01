/*------------------------------------------------*/
//Uva Problem No: 11764
//Problem Name  : Jumping Mario
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, n, now, next, high, low, c = 0;
    scanf("%d", &TC);
    while(TC--){
        c++;
        low = 0;
        high = 0;
        scanf("%d", &n);
        scanf("%d", &now);
        n--;
        while(n--){
            scanf("%d", &next);
            if(next > now) high++;
            if(next < now) low++;
            now = next;
        }
        printf("Case %d: %d %d\n", c, high, low);
    }
}
