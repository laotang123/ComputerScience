# include<iostream>

using namespace std; 
void BubbleSort()
{
	int list[10] = { 6,3,4,8,9,1,2,3,5,9 };
	int len = 10;
	int temp;
	for (size_t i = 0; i < len-1; i++)//n��Ԫ����Ҫ�Ƚ�n-1�Σ�n-1�ˣ�
	{
		//ÿһ�˶���ѡ��һ�����ֵ�ŵ������Ҳ�,�Ѿ�����õĲ���Ҫ�ټ���
		for (size_t j = 0; j < len-i-1; j++)
		{
			if (list[j] > list[j + 1])
			{
				//����
				temp = list[j];
				list[j] = list[j+1];
				list[j+1] = temp;
			}
		}
	}
	for (int i = 0; i < len; i++)
	{
		cout << list[i] << ",";
	}
	cout << endl;
}
int main()
{
	BubbleSort();
	return 0;
}
  
