#include <stdio.h>
#include <float.h>

int main(){
	printf("float 最大字节：%lu \n", sizeof(float));
	printf("float 最小值：%E \n", FLT_MIN);
	printf("float 最大值：%E \n", FLT_MAX);
	printf("精度值：%d\n", FLT_DIG);

	return 0;
}
