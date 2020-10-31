#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/*Write to dest, read from src*/
void write_read(long *src, long *dest, long n)
{
	long cnt = n;
	long val = 0;

	while(cnt){
		*dest = val;
		val = (*src) + 1;
		cnt--;
	}
}

int main(int argc, char const *argv[])
{
	/* code */
	//初始化
	long size = 1000000;
	long a[size];
	long i,j;
	

	for(i = 0; i < size; i++){
		a[i] = i - 10;
	}

	long cycle = 100000000;

	clock_t start = clock();
	for(j = 0; j < cycle; j++){
		write_read(&a[0], &a[1], 3);
	}
	clock_t end = clock();
	clock_t waste = end - start;
	printf("运行周期：%d，运行总时间：%d, 单次运行的时钟周期：%f\n", cycle, waste,\
		waste/(double)cycle);

	return 0;
}