/*------------------------------------------------*/
//Uva Problem No: 12250
//Problem Name  : Language Detection
//Type          : Ad hoc, strings
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>
#include <string.h>

using namespace std;

int main()
{
    char word[14];
    int c = 0;
    scanf("%s", word);
    while(strcmp(word,"#")!=0){
        c++;
        if(strcmp(word,"HELLO")==0) printf("Case %d: ENGLISH\n", c);
        else if(strcmp(word, "HOLA")==0) printf("Case %d: SPANISH\n", c);
        else if(strcmp(word,"HALLO")==0) printf("Case %d: GERMAN\n", c);
        else if(strcmp(word,"BONJOUR")==0) printf("Case %d: FRENCH\n", c);
        else if(strcmp(word,"CIAO")==0) printf("Case %d: ITALIAN\n", c);
        else if(strcmp(word,"ZDRAVSTVUJTE")==0) printf("Case %d: RUSSIAN\n", c);
        else printf("Case %d: UNKNOWN\n", c);
        scanf("%s", word);
    }
}
