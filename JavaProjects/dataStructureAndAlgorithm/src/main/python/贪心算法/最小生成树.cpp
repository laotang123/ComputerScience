#include <iostream>
using namespace std;

const int INF = 0x3fffffff;
const int N = 100;
bool s[N];
int closest[N];
int lowcost[N];
void Prim(int n, int u0, int c[N][N])
{    
	//如果s[i]=true,说明顶点i已加入最小生成树
	//的顶点集合U；否则顶点i属于集合V-U
	//将最后的相关的最小权值传递到数组lowcost
	s[u0] = true; 
	int i;
	int j;
	for (i = 1; i <= n; i++)
	{
		if (i != u0)
		{
			lowcost[i] = c[u0][i];
			closest[i] = u0;
			s[i] = false;
		}
		else
			lowcost[i] = 0;
	}
	//2.找到两个集合之间的最短边
	for (i = 1; i <= n; i++)
	{
		int temp = INF;
		int t = u0;
		for (j = 1; j <= n; j++)
		{
			if ((!s[j]) && (lowcost[j] < temp))
			{
				t = j;
				temp = lowcost[j];
			}
		}
		//3.加入到$U$集合
		if (t == u0)
			break;       //找不到t，跳出循环
		s[t] = true;   

		// 4.更新集合之间的边连接
		for (j = 1; j <= n; j++) //更新lowcost和closest
		{
			if ((!s[j]) && (c[t][j] < lowcost[j]))
			{
				lowcost[j] = c[t][j];
				closest[j] = t;
			}
		}
	}
}

int main()
{

	//1.初始化邻接矩阵，村庄之间的距离值
	int n, c[N][N], m, u, v, w;
	int u0;
	cout << "输入结点数n和边数m:" << endl;
	cin >> n >> m;
	int sumcost = 0;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			c[i][j] = INF;
	cout << "输入结点数u,v和边值w:" << endl;
	for (int i = 1; i <= m; i++)
	{
		cin >> u >> v >> w;
		c[u][v] = c[v][u] = w;
	}
	cout << "输入任一结点u0:" << endl;
	cin >> u0;
	//计算最后的lowcos的总和，即为最后要求的最小的费用之和
	Prim(n, u0, c);
	cout << "数组lowcost的内容为" << endl;
	for (int i = 1; i <= n; i++)
		cout << lowcost[i] << " ";
	cout << endl;
	for (int i = 1; i <= n; i++)
		sumcost += lowcost[i];
	cout << "最小的花费是：" << sumcost << endl;
	return 0;
}

