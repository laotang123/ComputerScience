#include<iostream>
#include<algorithm>
#include<cstring>

using namespace std;
//��̬�滮֮���鰲��

//����Ľṹ��
struct Meet
{
	int beg;//��ʼʱ��
	int end;//����ʱ��
	int num;//����ı��
}meet[1000];

//setMeet��
class setMeet {
public:
	void init();
	void solve();
private:
	int total, max_num;//�������������һ�찲�ŵ�����������
};
//setMeet init����
void setMeet::init()
//sort�����ȶԲ���
{
	int s, e;//��ʼ�ͽ���ʱ��
	cout << "�������������" << endl;
	cin >> total;
	cout << "�������Ŀ�ʼ�ͽ���ʱ�䣬�м�ʹ�ÿո����" << endl;
	for (size_t i = 0; i < total; i++)
	{
		cin >> s >> e;
		meet[i].beg = s;
		meet[i].end = e;
		meet[i].num = i + 1;//�����Ŵ�1��ʼ
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
//setMeet solve������̰���㷨���
void setMeet::solve()
{
	sort(meet, meet + total, cmp);
	cout << "������Ļ���ʱ������" << endl;
	cout << "������" << "��ʼʱ��" << "����ʱ��" << endl;
	for (size_t i = 0; i < total; i++)
	{
		cout << "  " << meet[i].num << "\t" << meet[i].beg << "\t" << meet[i].end << endl;
	}
	cout << "--------------------------------------" << endl;
	cout << "ѡ�����Ĺ��̣�" << endl;
	cout << "ѡ���" << meet[0].num << "������" << endl;
	max_num = 1;
	int last = meet[0].end;//��¼�ո�ѡ�л���Ľ���ʱ��
	for (size_t i = 0; i < total; i++)
	{
		if (meet[i].beg >= last)//���鿪ʼʱ������һ������Ľ���ʱ�䲻��ͻ
		{
			max_num += 1;
			last = meet[i].end;
			cout << "ѡ�е�" << meet[i].num << "������" << endl;
		}
	}
	cout << "������ѡ��" << max_num << "������" << endl;
}
//main����
int main()
{
	setMeet sm;
	sm.init();
	sm.solve();
	return 0;
}



