#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char const *argv[])
{
	/* code */
	long i;
	time_t start, end;
	start = time(NULL);
	for (i = 0; i < 10000000; i++)
	{
		
	}
	end = time(NULL);
	printf("time: %d\n", end - start);
	printf("clocks per second: %d\n", CLOCKS_PER_SEC);
	return 0;
}