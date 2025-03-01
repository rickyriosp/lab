/*------------------------------------------------*/
//Uva Problem No: 12403
//Problem Name  : Save Setu
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, acc = 0, k;
    char order[7];
    scanf("%d", &TC);
    while(TC--){
        scanf("%s", order);
        if(order[0] == 'r') printf("%d\n", acc);
        else {
            scanf("%d", &k);
            acc += k;
        }
    }
}
