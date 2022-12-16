площадь острова = 1 + площадь его соседей. Подумайте о том, что два раза заходить не надо в клеточку.
class Solution {
    public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int ans = 0;

        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid[0].size(); ++j)
                ans = max(ans, dfs(grid, i, j));

        return ans;
    }

    private:
    int dfs(vector<vector<int>>& grid, int i, int j) {
        if (i < 0 || i == grid.size() || j < 0 || j == grid[0].size())
            return 0;
        if (grid[i][j] != 1)
            return 0;

        grid[i][j] = 2;

        return 1 + dfs(grid, i + 1, j) + dfs(grid, i - 1, j)
                + dfs(grid, i, j + 1) + dfs(grid, i, j - 1);
    }
};

заходим в данную клеточку и помечаем ее и всех соседей такого же цвета, как изначальная клетка, заданным цветом

class Solution {
    public:
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc,
                                  int newColor) {
        dfs(image, sr, sc,
                vector<vector<bool>>(image.size(), vector<bool>(image[0].size())),
                image[sr][sc], newColor);
        return image;
    }

    private:
    void dfs(vector<vector<int>>& image, int i, int j,
             vector<vector<bool>>&& seen, int startColor, int newColor) {
        if (i < 0 || i == image.size() || j < 0 || j == image[0].size())
            return;
        if (image[i][j] != startColor || seen[i][j])
            return;

        image[i][j] = newColor;
        seen[i][j] = true;

        dfs(image, i + 1, j, move(seen), startColor, newColor);
        dfs(image, i - 1, j, move(seen), startColor, newColor);
        dfs(image, i, j + 1, move(seen), startColor, newColor);
        dfs(image, i, j - 1, move(seen), startColor, newColor);
    }
};

Задача 3

class Solution {
    public:
    bool isSymmetric(TreeNode* root) {
        return isSymmetric(root, root);
    }

    private:
    bool isSymmetric(TreeNode* p, TreeNode* q) {
        if (!p || !q)
            return p == q;

        return p->val == q->val &&
                isSymmetric(p->left, q->right) &&
                isSymmetric(p->right, q->left);
    }
};