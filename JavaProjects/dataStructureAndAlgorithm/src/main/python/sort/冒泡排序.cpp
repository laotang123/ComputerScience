# include<iostream>

using namespace std; 
void BubbleSort()
{
	int list[10] = { 6,3,4,8,9,1,2,3,5,9 };
	int len = 10;
	int temp;
	for (size_t i = 0; i < len-1; i++)//n个元素需要比较n-1次（n-1趟）
	{
		//每一趟都会选择一个最大值放到数组右侧,已经排序好的不需要再检索
		for (size_t j = 0; j < len-i-1; j++)
		{
			if (list[j] > list[j + 1])
			{
				//交换
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
  
