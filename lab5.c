//Write the C code that returns the sum of all even numbers up to the number
//entered from the keyboard.
#include <stdio.h>

int main ()
{

int sum = 0;
int num;

printf("Enter a number :");
scanf("%d" , &num);

//calculate the sum of even numbers
for(int i = 0; i <= num ; i+=2){
    sum += i;
}

//Display the sum
printf("The sum of all even numbers up to %d is :%d\n" , num , sum);

    return 0;
}