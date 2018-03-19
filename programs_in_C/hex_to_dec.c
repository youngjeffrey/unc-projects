#include<stdio.h>
#include <string.h>

char input[22];     // To read in a string of up to 20 hexits plus newline and null

int h_to_i(char* str);

int main() {

  int value,a,y;

    for(a=0; a < 10000; a=a+1) {

        fgets(input, 22, stdin);

        if(input[0]=='0' && strlen(input)==2){
                break;
        }

        value = h_to_i(input);

        printf("%d\n", value);

  }
}


int h_to_i(char* str) {

int convert,i,b,c,d,e,f;
b = strlen(str);
c = b-2;
int hex;

int product = 1;

        for(i=0;i<b;i=i+1) {

        hex = 0;
        product=1;

        if(str[i]=='a') {
                hex = 10;
        }else if(str[i]=='b') {
                hex = 11;
              }else if(str[i]=='c') {
                      hex = 12;
              }else if(str[i]=='d') {
                      hex= 13;
              }else if(str[i]=='e') {
                      hex = 14;
              }else if(str[i]=='f') {
                      hex = 15;
              }else if(str[i]=='1') {
                      hex = 1;
              }else if(str[i]=='2') {
                      hex = 2;
              }else if(str[i]=='3') {
                      hex = 3;
              }else if(str[i]=='4') {
                      hex = 4;
              }else if(str[i]=='5') {
                      hex = 5;
        }else if(str[i]=='6') {
                      hex = 6;
        }else if(str[i]=='7') {
                      hex = 7;
        }else if(str[i]=='8') {
                hex = 8;
        }else if(str[i]=='9') {
                hex = 9;
        }else if(str[i]=='0') {
                hex = 0;
        }

        for(e=0;e<c;e=e+1) {
        product = product*16;
        }

        convert = convert + (product)*hex;
        c=c-1;
}

return convert;

}
