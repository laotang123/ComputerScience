#include<iostream>

using namespace std;
//获取基准元素所在的位置 
int GetMid(int arr[], int low, int high)
{
	int i = low, j = high, pivot = arr[low];
	while (i < j)//交换后继续扫描 
	{
		while (i<j&&arr[j]>pivot) j--;//右侧开始查找比基准元素更小的值 
		while (i < j&&arr[i] <= pivot) i++;//左侧查找比基准元素大于等于的值 
		if (i < j)
		{
			swap(arr[i++], arr[j--]);//先交换后赋值 
		}
	}
	if (arr[i] > pivot)
	{
		swap(arr[i - 1], arr[low]);//在代码中提供的示例中，在遇到第一次扫描时交换i和j都指向37。如果不加最后的判断会出现错误。 
		return i - 1;
	}
	swap(arr[i], arr[low]);
	return i;
}
//快速排序函数
void QuickSort(int arr[], int low, int high)
{
	int mid;
	if (low < high)
	{
		//递归快排
		mid = GetMid(arr, low, high);
		QuickSort(arr, mid + 1, high);//右区间快排
		QuickSort(arr, low, mid - 1);//左区间快排
	}
}

int main()
{
	int N = 9;
	int arr[9] = { 30,24,5,58,37,36,12,42,39 };
	for (int i = 0; i < N; i++)
	{
		cout << arr[i] << ",";
	}
	cout<<endl; 
	QuickSort(arr, 0, N-1);
	for (int i = 0; i < N; i++)
	{
		cout << arr[i] << ",";
	}
	cout << endl;
}



