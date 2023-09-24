#include <stdio.h>
long get_long(void);

int main(){

    int demo = get_long();
    printf("%d",demo);
}

long get_long(void){
    long input;
    char ch;
    while(scanf("%d",&input) != 1)
    {
        while((ch = getchar()) != '\n')
            putchar(ch);
        printf("is not an integer.");
    }
    return input;
}


