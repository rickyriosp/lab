/*------------------------------------------------*/
//Uva Problem No: 12577
//Problem Name  : Hajj-e-Akbar
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int c = 0;
    char word[6];
    scanf("%s", word);
    while(word[0] != '*'){
        c++;
        if(word[0] == 'H') printf("Case %d: Hajj-e-Akbar\n", c);
        else printf("Case %d: Hajj-e-Asghar\n", c);
        scanf("%s", word);
    }
}
