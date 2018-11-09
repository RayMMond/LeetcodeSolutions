public class Solution {
    public int NumIslands(char[,] grid) {
        int result = 0;
        var nodes = new Queue<(int X, int Y)>();
        for (int i = 0; i < grid.GetLength(0); i++)
        {
            for (int j = 0; j < grid.GetLength(1); j++)
            {
                if (grid[i, j] == '1')
                {
                    result++;
                    nodes.Enqueue((i, j));

                    while (nodes.Count > 0)
                    {
                        var n = nodes.Dequeue();
                        foreach (var node in GetNeighbors(n, grid))
                        {
                            nodes.Enqueue(node);
                        }
                    }
                }
            }
        }

        return result;
    }
    private IEnumerable<(int X, int Y)> GetNeighbors((int X, int Y) n, char[,] grid)
    {
        if (n.X + 1 < grid.GetLength(0) && grid[n.X + 1, n.Y] == '1')
        {
            grid[n.X + 1, n.Y] = '#';
            yield return (n.X + 1, n.Y);
        }

        if (0 <= n.X - 1 && n.X - 1 < grid.GetLength(0) && grid[n.X - 1, n.Y] == '1')
        {
            grid[n.X - 1, n.Y] = '#';
            yield return (n.X - 1, n.Y);
        }

        if (n.Y + 1 < grid.GetLength(1) && grid[n.X, n.Y + 1] == '1')
        {
            grid[n.X, n.Y + 1] = '#';
            yield return (n.X, n.Y + 1);
        }

        if (0 <= n.Y - 1 && n.Y - 1 < grid.GetLength(1) && grid[n.X, n.Y - 1] == '1')
        {
            grid[n.X, n.Y - 1] = '#';
            yield return (n.X, n.Y - 1);
        }
    }

}