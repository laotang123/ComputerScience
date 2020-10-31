#include <stdio.h>
#include <stdlib.h>
#include <time.h>	
#define IDENT 0
#define OP +

typedef long data_t;
/*Create abstract data type for vector*/
typedef struct
{
	long len;
	data_t *data;
} vec_rec,*vec_ptr;

/*Create new vector of specified length*/
vec_ptr new_vec(long len)
{
	/*Allocate header structure*/
	vec_ptr result = (vec_ptr) malloc(sizeof(vec_rec));
	data_t *data = NULL;
	if(!result)
		return NULL; /*Couldn't allocate storage*/
	result->len = len;

	/*Allocate array*/
	if (len > 0){
		data = (data_t *) calloc(len, sizeof(data_t));
		if(!data){
			free((void *) result);
			return NULL;
		}
	}

	result->data = data;
	return result;
}

int get_vec_element(vec_ptr v, long index, data_t *dest)
{
	if(index < 0 || index >= v->len)
		return 0;
	*dest = v->data[index];
	return 1;
}

long vec_length(vec_ptr v)
{
	return v->len;
}

data_t *get_vec_start(vec_ptr v)
{
	return v->data;
}
void combine3(vec_ptr v, data_t *dest)
{
	long i;
	long length	= vec_length(v);
	data_t *data = get_vec_start(v);

	*dest = IDENT;
	for (i = 0; i < length; i++)
	{
		data_t val;
		*dest = *dest OP data[i];
	}
}

int main(int argc, char const *argv[])
{
	/* code */
	long size = 1000000;
	vec_ptr v = new_vec(size);

	//赋值
	v->len = size;
	data_t *data = v->data;
	int i;
	for (i = 0; i < size; i++)
	{
		data[i] = 2;
	}

	printf("数组长度： %d\n", vec_length(v));
	printf("数组第一个元素： %d\n",*(v->data));


	//cycle call
	long cycle = 10000;
	int j;
	clock_t start,end;
	start = clock();//start time
	data_t dest;
	for (j = 0; j < cycle; j++)
	{
		// *dest = IDENT;
		combine3(v, &dest);
	}
	end = clock();

	clock_t waste = end - start;
	printf("循环周期： %ld, 总滴答数量：%ld，单次滴答次数：%f,累加值： %d\n",cycle,\
		waste, (double)waste/cycle, dest);
	return 0;
}