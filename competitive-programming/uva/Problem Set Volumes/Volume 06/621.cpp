/*------------------------------------------------*/
//Uva Problem No: 621
//Problem Name  : Secret Research
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>
#include <string.h>

using namespace std;

int main()
{
    int TC;
    char str[255];
    scanf("%d", &TC);
    while(TC--){
        scanf("%s", str);
        if(strcmp(str, "1")==0||strcmp(str, "4")==0||strcmp(str, "78")==0) printf("+\n");
        else if((str[strlen(str)-1]=='5')&&(str[strlen(str)-2]=='3')) printf("-\n");
        else if((str[0]=='9')&&(str[strlen(str)-1]=='4')) printf("*\n");
        else if((str[0]=='1')&&(str[1]=='9')&&(str[2]=='0')) printf("?\n");
        else printf("?\n");
        // This last case when it doesn't match with anything is not
        // clear in the problem statement, it just works. Problem
        // statement is very poor.
    }
}
