/*------------------------------------------------*/
//Uva Problem No: 10114
//Problem Name  : Loansome Car Buyer
//Type          : Ad hoc, simulation
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int duration, dep, month, current;
    float down, loan, owed, val, per, past_per;
    while(1){
        scanf("%d %f %f %d", &duration, &down, &loan, &dep);
        if(duration < 0) break;
        val = down + loan;
        owed = loan;

        scanf("%d %f", &month, &per);
        val -= val * per;
        past_per = per;
        current = month;

        while(--dep){
            scanf("%d %f", &month, &per);
            while((current+1 < month)&&(owed > val)){
                    val -= val * past_per;
                    owed -= loan/duration;
                    current++;
            }
            if(owed > val){
                val -= val * per;
                owed -= loan/duration;
                current++;
            }
            past_per = per;
        }

        while(current < duration){
            if(owed > val){
                val -= val * per;
                owed -= loan/duration;
                current++;
            } else break;
        }
        if(current != 1) printf("%d months\n", current);
        else printf("1 month\n");
    }
}
