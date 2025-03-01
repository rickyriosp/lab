/*------------------------------------------------*/
//Uva Problem No: 458
//Problem Name  : The Decoder
//Type          : Ad hoc, strings
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    char code[256];
    while(scanf("%s", code) != EOF){
        for(int i=0; code[i]!=0; i++)
            code[i] -= 7;
        printf("%s\n", code);
    }
}
