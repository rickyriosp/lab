/*------------------------------------------------*/
//Uva Problem No: 11498
//Problem Name  : Division of Nlogonia
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, n, m, x, y;
    while(scanf("%d", &TC) == 1){
        scanf("%d %d", &n, &m);
        while(TC--){
            scanf("%d %d", &x, &y);
            if((x == n) || (y == m)) printf("divisa\n");
            else if(y > m){
                if(x < n) printf("NO\n");
                if(x > n) printf("NE\n");
            } else if(y < m){
                if(x < n) printf("SO\n");
                if(x > n) printf("SE\n");
            }
        }
    }
}
