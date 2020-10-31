# coding=utf-8

from queue import Queue

NAN = float("inf")
class Graph(object):
    def __init__(self,vertexes):
        """
        初始化顶点和邻接表
        :param vertexes:顶点数量 int
        """
        # TODO 针对顶点不是从0开始的数据类型，可以采用字典的形式代替入度列表和邻接表
        # TODO 尝试邻接矩阵完成搜索和拓扑排序
        self.vertexes = vertexes
        self.adjacency = [[] for _ in range(self.vertexes)]
        self.matrix = [[NAN]*self.vertexes for _ in range(self.vertexes)]
        self.in_degree = [0]*self.vertexes

    # 图的存储：邻接表，邻接矩阵
    def addEdge(self,edge):
        self.adjacency[edge[0]].append(edge[1])
        self.in_degree[edge[1]] += 1

    # 深度优先搜索
    def DFS(self):
        """
        递归
        :return: 
        """
        def DfsUnit(v,adjacency):
            result.append(v)
            visited[v] = True

            for i in adjacency[v]:
                if not visited[i]:
                    DfsUnit(i,adjacency)


        assert self.vertexes >= 0, "顶点数量不能为负值！"
        if self.vertexes <= 1:
            return self.vertexes

        result = []
        visited = [False]*self.vertexes
        for i in range(self.vertexes):
            if not visited[i]:
                DfsUnit(i,self.adjacency)
        return result
    # 广度优先搜素
    def BFS(self):
        """
        队列
        :return: 
        """
        assert self.vertexes >= 0,"顶点数量不能为负值！"
        if self.vertexes <= 1:
            return self.vertexes

        visited = [False]*self.vertexes # 记录节点状态
        queue = Queue()
        result = [0]
        queue.put(0) # 第一个顶点
        visited[0] = True


        while not queue.empty():
            for v in self.adjacency[queue.get()]:
                # 如果没有被访问：则入队，追加结果
                if not visited[v]:
                    result.append(v)
                    queue.put(v)
                    visited[v] = True
        return result

    # 拓扑排序
    def topological(self):
        """
        返回拓扑排序
        时间复杂度=O(n+e)
        :return:
        """
        result = [] # 记录拓扑排序
        stack = []
        # 1.采用队列的形式依次将入度为0的顶点压入栈中
        # 时间复杂度=顶点数
        for v,d in enumerate(self.in_degree):
            if d == 0:
                stack.append(v)
                result.append(v) # 记录结果

        # 时间复杂度=边数
        while len(stack) != 0:
            p = stack.pop()  # 出栈
            for v in self.adjacency[p]:
                self.in_degree[v] -= 1 # 入度为0的临界点入度-1
                if self.in_degree[v] == 0:
                    stack.append(v)
                    result.append(v)

        return result,len(result) == self.vertexes
    # 有向无环图的判断
    def IsDag(self)->bool:
        """
        :return:
        """
        def DfsUnit(v):
            if flags[v] == 1:
                return False
            if flags[v] == -1:
                return True
            flags[v] = 1 # 非1和-1，就是0表示未访问。递归单元的每一个顶点都是当前启动的顶点

            for i in self.adjacency[v]:
                if not DfsUnit(i):
                    return False
            flags[v] = -1 # 如果基于当前顶点的深度搜索结束后不存在环，当前顶点对于之后的顶点为“之前顶点”
            return True
        flags = [0]*self.vertexes # 0表示未访问，1表示被当前启动的顶点访问，-1表示之前启动的顶点访问
        for i in range(self.vertexes):
            if not DfsUnit(i):
                return False
        return True

if __name__ == '__main__':
    # 添加边,该图存在环，去除[3,2]边环不存在
    graph = Graph(6)
    graph.addEdge([0,1])
    graph.addEdge([0,2])
    graph.addEdge([1,3])
    graph.addEdge([2,1])
    graph.addEdge([2,4])
    graph.addEdge([3,2])
    graph.addEdge([3,5])
    graph.addEdge([4,3])
    graph.addEdge([4,5])
    print(graph.BFS())
    print(graph.DFS())
    print("该图是否存在环：{}".format(not graph.IsDag()))
    t_sort,exist_loop = graph.topological()
    print("该图的拓扑排序：{},是否存在环：{}".format(t_sort,exist_loop))


