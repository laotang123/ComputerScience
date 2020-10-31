#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/*Rearrange two vectors so that for each i, b[i] >= a[i]*/
void minmax1(long a[], long b[], long n){
	long i;
	for(i = 0; i < n; i++){
		if(a[i] > b[i]){
			long t = a[i];
			a[i] = b[i];
			b[i] = t;	
		}//交换
	}
}
int main()
{
	//初始化
	long size = 10000;
	long a[size];
	long b[size];
	long i,j,k;	

	for(i = 0; i < size; i++){
		a[i] = i;
	}

	for(j = 0; j < size; j++){
		b[j] = -j;
	}

	time_t start, end;
	long cycle = 100;	
	start = time(NULL);
	long *tmp = a;
	for(k = 0; k < size; k++){
		minmax1(a, b, size);
		//交换数组地址
	}
	end = time(NULL);
	printf("运行周期：%d，运行总时间：%d\n", cycle, end - start);
	return 0;
}
