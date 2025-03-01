/*------------------------------------------------*/
//Uva Problem No: 272
//Problem Name  : TeX Quotes
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    char c;
    while(scanf("%c", &c) != EOF){
        if(c == '"'){
            printf("``");
            while(scanf("%c", &c) != EOF){
                if(c == '"'){printf("''"); break;}
                else printf("%c", c);
            }
        }
        else printf("%c", c);
    }
}
