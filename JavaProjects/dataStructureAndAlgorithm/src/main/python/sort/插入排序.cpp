# include<iostream>

using namespace std; 

void InsertSort()
{
	int list[10] = { 6,3,4,8,9,1,2,3,5,9 };
	int len = 10;
	int temp;
	for (size_t i = 1; i < len; i++)
	{
		//���Ҳ������ĵ�һ��Ԫ�����������õ�Ԫ�����ν��бȽ�,����j>0ȡ�����е������
		for (size_t j = i; j > 0; j--)
		{
			if (list[j] < list[j - 1])
			{
				temp = list[j];
				list[j] = list[j - 1];
				list[j - 1] = temp;
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
	InsertSort();
	return 0;
}
  
