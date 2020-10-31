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
	//2.初始化，前驱数组，距离和集合
	for (size_t i = 1; i <= n; i++)
	{
		dist[i] = map[u][i];//初始化源点到其他点的距离
		flag[i] = false;
		if (dist[i] == INF)//如果源点到其他点存在路径则将前驱设置为源点u
		{
			p[i] = -1;
		}
		else
		{
			p[i] = u;
		}
	}
	dist[u] = 0;
	flag[u] = true;//初始化S集合有一个元素：源点u

	//3.求最小，在集合V-S中找到距离源点u最近的顶点t，如果找到则将t加入到集合S中。否则跳出循环
	for (size_t i = 1; i <= n; i++)//初始化V-S集合有n-1个元素
	{
		int temp = INF, t = u;
		for (size_t j = 1; j <= n; j++)
		{
			if (!flag[j] && dist[j] < temp)
			{
				t = j;
				temp = dist[j];//在V-S集合中找到最短距离
			}
		}
		if (t == u) { return; }
		flag[t] = true;
		//更新V-S集合中与t邻接的顶点
		for (size_t j = 1; j <= n; j++)
			if (!flag[j] && map[t][j] < INF)
				//如果经过t点，路径更短。更新距离和前驱
				if (dist[j] > (dist[t] + map[t][j]))
				{
					//4.借东风，在已有的t顶点，如果源点经过t到达邻接点j的路径更短。更新矩阵和j的前驱为t
					dist[j] = dist[t] + map[t][j];
					p[j] = t;
				}
	}
}

int main()
{
	int u, v, w, st;
	system("color 0d");
	cout << "请输入城市的个数：" << endl; cin >> n;
	cout << "请输入城市之间的路线的个数:" << endl; cin >> m;
	cout << "请输入城市之间的路线以及距离：" << endl;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
		{
			map[i][j] = INF;//初始化邻接矩阵为无穷大
		}
	while (m--)
	{
		cin >> u >> v >> w;
		map[u][v] = min(map[u][v], w); //邻接矩阵储存，保留最小的距离
	}
	cout << "请输入小明所在的位置:" << endl; ;
	cin >> st;
	Dijkstra(st);
	cout << "小明所在的位置:" << st << endl;
	for (int i = 1; i <= n; i++)
	{
		cout << "小明:" << st << " - " << "要去的位置:" << i;
		if (dist[i] == INF)
			cout << "sorry,无路可达" << endl;
		else
			cout << " 最短距离为:" << dist[i] << endl;
	}

	//findpath(st);
	return 0;
}

