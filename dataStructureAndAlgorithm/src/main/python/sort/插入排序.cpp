# include<iostream>

using namespace std; 

void InsertSort()
{
	int list[10] = { 6,3,4,8,9,1,2,3,5,9 };
	int len = 10;
	int temp;
	for (size_t i = 1; i < len; i++)
	{
		//将右侧待排序的第一个元素与左侧排序好的元素依次进行比较,限制j>0取到序列的最左侧
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
  
