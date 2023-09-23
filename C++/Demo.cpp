#include <stdio.h>
void display(char cr,int lines,int width);
int main(void){
    int ch;
    int row,cols;
    while((ch = getchar())!= '\n')
    {
        scanf("%d %d",&row,&cols);
        display(ch,row,cols);
        printf("Enter a newline to quit.\n");
    }
    printf("bye.\n");

    return 0;
}

void display(char cr,int lines,int width)
{
    int row,col;
    for (row = 0; row <= lines ; row++) {
        for (col = 0; col <= width ; col++) {
            putchar(cr);
            putchar('\n');
        }
    }
}