/*------------------------------------------------*/
//Uva Problem No: 11942
//Problem Name  : Lumberjack Sequencing
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC, current, next;
    bool up, ordered;
    scanf("%d", &TC);
    printf("Lumberjacks:\n");
    while(TC--){
        ordered = true;
        scanf("%d %d", &current, &next);
        if(current < next) up = true;
        else up = false;
        current = next;
        for(int i=0; i<8; i++){
            scanf("%d", &next);
            if(up){
                if(current > next) ordered = false;
            } else if(current < next) ordered = false;

            current = next;
        }
        if(ordered) printf("Ordered\n");
        else printf("Unordered\n");
    }
}
