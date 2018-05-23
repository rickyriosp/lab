/*------------------------------------------------*/
//Uva Problem No: 10141
//Problem Name  : Request for Proposal
//Type          : Ad hoc
//Author        : Ricky Rios
//University    : N/A
//E-mail        : N/A
/*------------------------------------------------*/
#include <iostream>
#include <string.h>

using namespace std;

int main()
{
    int n, p, met, met_temp, rfp = 0;
    string prop, prop_temp, temp;
    float price, price_temp;
    while(cin >> n >> p, n||p){
        rfp++;
        for(int i=0; i<n; i++){
            cin.ignore();         // get rid of the '\n' character
            getline(cin, temp);
        }

        price = 2000000000;
        met = -1;

        while(p--){
            getline(cin, prop_temp);
            cin >> price_temp >> met_temp;
            if(met_temp > met){
                met = met_temp;
                price = price_temp;
                prop = prop_temp;
            } else if(met_temp == met && price_temp < price){
                    met = met_temp;
                    price = price_temp;
                    prop = prop_temp;
                }
            for (int i = 0; i < met_temp; i++) {
                cin.ignore();
				getline(cin, temp);
			}
        }

        if(rfp > 1) cout << '\n';
        cout << "RFP #" << rfp << '\n';
        cout << prop << '\n';
    }
}
