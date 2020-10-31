#include <stdio.h>
#include <time.h>
#include <unistd.h>

int main()
{
	time_t start,end;
	start = time(NULL);
	sleep(10);
	end = time(NULL);
	
	printf("睡眠时间 %ld \n", end-start);
	return 0;
}
