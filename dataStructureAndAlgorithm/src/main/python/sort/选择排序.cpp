#include<iostream>

using namespace std;
//��ȡ��׼Ԫ�����ڵ�λ�� 
int GetMid(int arr[], int low, int high)
{
	int i = low, j = high, pivot = arr[low];
	while (i < j)//���������ɨ�� 
	{
		while (i<j&&arr[j]>pivot) j--;//�Ҳ࿪ʼ���ұȻ�׼Ԫ�ظ�С��ֵ 
		while (i < j&&arr[i] <= pivot) i++;//�����ұȻ�׼Ԫ�ش��ڵ��ڵ�ֵ 
		if (i < j)
		{
			swap(arr[i++], arr[j--]);//�Ƚ�����ֵ 
		}
	}
	if (arr[i] > pivot)
	{
		swap(arr[i - 1], arr[low]);//�ڴ������ṩ��ʾ���У���������һ��ɨ��ʱ����i��j��ָ��37��������������жϻ���ִ��� 
		return i - 1;
	}
	swap(arr[i], arr[low]);
	return i;
}
//����������
void QuickSort(int arr[], int low, int high)
{
	int mid;
	if (low < high)
	{
		//�ݹ����
		mid = GetMid(arr, low, high);
		QuickSort(arr, mid + 1, high);//���������
		QuickSort(arr, low, mid - 1);//���������
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



