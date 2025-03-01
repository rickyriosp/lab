/*------------------------------------------------*/
//Uva Problem No: 11332
//Problem Name  : Summing Digits
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <stdio.h>

using namespace std;

int main()
{
    long n, sum;
    scanf("%ld", &n);
    while(n!=0){
        sum = 0;
        if(n < 10) sum = n;
        else while(n>=10){
                sum = 0;
                while(n>0){
                    sum += (n%10);
                    n /= 10;
                }
                n = sum;
        }
        printf("%ld\n", sum);
        scanf("%ld", &n);
    }
}
