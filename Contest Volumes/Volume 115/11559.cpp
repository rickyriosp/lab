/*------------------------------------------------*/
//Uva Problem No: 11559
//Problem Name  : Event Planning
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int n, b, h, w, p, a, ans;
    while(scanf("%d %d %d %d", &n, &b, &h, &w) != EOF){
        ans = b+1;
        while (h--){
            scanf("%d", &p);
            p = n*p;
            for(int i=1; i<=w; i++){
                scanf("%d", &a);
                if(a > n) if(p < ans) ans = p;
            }
        }
        if(ans > b) printf("stay home\n");
        else printf("%d\n", ans);
    }
}
