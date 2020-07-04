#include<iostream>
#include<windows.h>
#include<stack>


using namespace std;
const int N = 100;
const int INF = 1e7;
int map[N][N], dist[N], p[N], n, m;
bool flag[N];
void Dijkstra(int u)
{
	//2.��ʼ����ǰ�����飬����ͼ���
	for (size_t i = 1; i <= n; i++)
	{
		dist[i] = map[u][i];//��ʼ��Դ�㵽������ľ���
		flag[i] = false;
		if (dist[i] == INF)//���Դ�㵽���������·����ǰ������ΪԴ��u
		{
			p[i] = -1;
		}
		else
		{
			p[i] = u;
		}
	}
	dist[u] = 0;
	flag[u] = true;//��ʼ��S������һ��Ԫ�أ�Դ��u

	//3.����С���ڼ���V-S���ҵ�����Դ��u����Ķ���t������ҵ���t���뵽����S�С���������ѭ��
	for (size_t i = 1; i <= n; i++)//��ʼ��V-S������n-1��Ԫ��
	{
		int temp = INF, t = u;
		for (size_t j = 1; j <= n; j++)
		{
			if (!flag[j] && dist[j] < temp)
			{
				t = j;
				temp = dist[j];//��V-S�������ҵ���̾���
			}
		}
		if (t == u) { return; }
		flag[t] = true;
		//����V-S��������t�ڽӵĶ���
		for (size_t j = 1; j <= n; j++)
			if (!flag[j] && map[t][j] < INF)
				//�������t�㣬·�����̡����¾����ǰ��
				if (dist[j] > (dist[t] + map[t][j]))
				{
					//4.�趫�磬�����е�t���㣬���Դ�㾭��t�����ڽӵ�j��·�����̡����¾����j��ǰ��Ϊt
					dist[j] = dist[t] + map[t][j];
					p[j] = t;
				}
	}
}

int main()
{
	int u, v, w, st;
	system("color 0d");
	cout << "��������еĸ�����" << endl; cin >> n;
	cout << "���������֮���·�ߵĸ���:" << endl; cin >> m;
	cout << "���������֮���·���Լ����룺" << endl;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
		{
			map[i][j] = INF;//��ʼ���ڽӾ���Ϊ�����
		}
	while (m--)
	{
		cin >> u >> v >> w;
		map[u][v] = min(map[u][v], w); //�ڽӾ��󴢴棬������С�ľ���
	}
	cout << "������С�����ڵ�λ��:" << endl; ;
	cin >> st;
	Dijkstra(st);
	cout << "С�����ڵ�λ��:" << st << endl;
	for (int i = 1; i <= n; i++)
	{
		cout << "С��:" << st << " - " << "Ҫȥ��λ��:" << i;
		if (dist[i] == INF)
			cout << "sorry,��·�ɴ�" << endl;
		else
			cout << " ��̾���Ϊ:" << dist[i] << endl;
	}

	//findpath(st);
	return 0;
}

