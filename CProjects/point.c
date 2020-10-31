#include<stdio.h>

int fun(int x, int *p){
	return x + *p;
}
int main(void){
	int p = 4;
	int (*fp)(int ,int *);
	fp = fun;
	int y = fp(3, &p);
	printf("sizeof(fp) = %d \n",sizeof(fp));
	printf("y = %d \n",y);
	return 0;
}
