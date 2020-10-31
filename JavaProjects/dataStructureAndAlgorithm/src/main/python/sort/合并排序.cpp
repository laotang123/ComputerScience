#include<iostream>

using namespace std;


//��С��Ԫ���еĺϲ�
void Merge(int arr[],int low,int mid, int high)
{
	//1.������arr�ȳ�������B
	int *B = new int[high - low + 1];
	int i = low, j = mid + 1, k = 0;
	//2.��arr����һ��Ϊ������������Ĺ���Ԫ�����ηŵ�B������
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
	//3.��arr���������δ����Ĳ��ָ��Ƶ�B�������Ҳ�
	while (i <= mid){ B[k++] = arr[i++]; }
	while (j <= high){ B[k++] = arr[j++]; }
	//4.��B���鸴�Ƶ�arr���飬�ͷ�B
	for (i = low, k = 0; i <= high; i++)
	{
		arr[i] = B[k++];
	}
	delete []B;
}

//�ϲ�����
void MergeSort(int arr[],int low,int high)
{
	//�ݹ�ϲ�����
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
