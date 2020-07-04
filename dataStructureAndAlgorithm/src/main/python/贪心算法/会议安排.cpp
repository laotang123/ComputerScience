#include<iostream>
#include<algorithm>
#include<cstring>

using namespace std;
//动态规划之会议安排

//会议的结构体
struct Meet
{
	int beg;//开始时间
	int end;//结束时间
	int num;//会议的编号
}meet[1000];

//setMeet类
class setMeet {
public:
	void init();
	void solve();
private:
	int total, max_num;//会议的总数量，一天安排的最大会议数量
};
//setMeet init方法
void setMeet::init()
//sort函数比对策略
{
	int s, e;//开始和结束时间
	cout << "输入会议总数：" << endl;
	cin >> total;
	cout << "输入会议的开始和结束时间，中间使用空格隔开" << endl;
	for (size_t i = 0; i < total; i++)
	{
		cin >> s >> e;
		meet[i].beg = s;
		meet[i].end = e;
		meet[i].num = i + 1;//会议编号从1开始
	}
}
bool cmp(Meet x, Meet y)
{
	if (x.end == y.end)
	{
		return x.beg > y.beg;
	}
	return x.end < y.end;
}
//setMeet solve方法，贪心算法求解
void setMeet::solve()
{
	sort(meet, meet + total, cmp);
	cout << "排完序的会议时间如下" << endl;
	cout << "会议编号" << "开始时间" << "结束时间" << endl;
	for (size_t i = 0; i < total; i++)
	{
		cout << "  " << meet[i].num << "\t" << meet[i].beg << "\t" << meet[i].end << endl;
	}
	cout << "--------------------------------------" << endl;
	cout << "选择会议的过程：" << endl;
	cout << "选择第" << meet[0].num << "个会议" << endl;
	max_num = 1;
	int last = meet[0].end;//记录刚刚选中会议的结束时间
	for (size_t i = 0; i < total; i++)
	{
		if (meet[i].beg >= last)//会议开始时间与上一个会议的结束时间不冲突
		{
			max_num += 1;
			last = meet[i].end;
			cout << "选中第" << meet[i].num << "个会议" << endl;
		}
	}
	cout << "最多可以选择" << max_num << "个会议" << endl;
}
//main函数
int main()
{
	setMeet sm;
	sm.init();
	sm.solve();
	return 0;
}



