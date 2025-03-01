/*------------------------------------------------*/
//Uva Problem No: 12279
//Problem Name  : Emoogle Balance
//Type          : XXXX
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC = 0, n, event, ans;
    scanf("%d", &n);
    while(n != 0){
        TC++;
        ans = 0;
        while(n--){
            scanf("%d", &event);
            if(event > 0) ans++;
            else ans--;
        }
        printf("Case %d: %d\n", TC, ans);
        scanf("%d", &n);
    }
}
