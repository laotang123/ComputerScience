#include<iostream>

using namespace std;


//最小单元序列的合并
void Merge(int arr[],int low,int mid, int high)
{
	//1.申请与arr等长的数组B
	int *B = new int[high - low + 1];
	int i = low, j = mid + 1, k = 0;
	//2.将arr数组一分为二，按照升序的规则将元素依次放到B数组中
	while (i<=mid&&j<=high)
	{
		if (arr[i] <= arr[j])
		{
			B[k++] = arr[i++];
		}
		else
		{
			B[k++] = arr[j++];
		}
	}
	//3.将arr数组的升序未排序的部分复制到B，左侧和右侧
	while (i <= mid){ B[k++] = arr[i++]; }
	while (j <= high){ B[k++] = arr[j++]; }
	//4.将B数组复制到arr数组，释放B
	for (i = low, k = 0; i <= high; i++)
	{
		arr[i] = B[k++];
	}
	delete []B;
}

//合并排序
void MergeSort(int arr[],int low,int high)
{
	//递归合并排序
	if (low < high)
	{
		int mid = (low + high) / 2;
		MergeSort(arr, low, mid);
		MergeSort(arr, mid + 1, high);
		Merge(arr, low, mid, high);
	}
}


int main()
{
	int N = 8;
	int a[8] = { 42,15,20,6,8,38,50,12 };
	MergeSort(a, 0, N-1);
	for (size_t i = 0; i < N; i++)
	{
		cout << a[i] << ",";
	}
}
