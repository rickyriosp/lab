/*------------------------------------------------*/
//Uva Problem No: 12289
//Problem Name  : One-Two-Three
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    int TC;
    char word[6];
    scanf("%d", &TC);
    while(TC--){
        word[3] = 0;
        word[4] = 0;
        scanf("%s", word);
        if(word[4]!=0) printf("3\n");
        else if((word[0]=='o')&&(word[1]=='n')) printf("1\n");
        else if((word[0]=='o')&&(word[2]=='e')) printf("1\n");
        else if((word[1]=='n')&&(word[2]=='e')) printf("1\n");
        else if((word[0]=='t')&&(word[1]=='w')) printf("2\n");
        else if((word[0]=='t')&&(word[2]=='o')) printf("2\n");
        else if((word[1]=='w')&&(word[2]=='o')) printf("2\n");
    }
}
