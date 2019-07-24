1.前置条件：
确认你有pip和python，并配置了环境变量

2.安装依赖：
1>在cmd中，在当前目录下执行：
pip install -r  requirements.txt
2>在C:\Users\xyp\.keras\datasets中加入包含fashion_mnist图片集的文件夹

3.测试：
1>在cmd中，在当前目录下执行：
python main.py
2>每产生的连续两行中，前一行是当前图片的预测期望，后一行是当前图片生成的攻击样本的预测期望
3>当图片遍历完成后会打印攻击样本集合

4.来源说明：
1>学习的是github上的one_pixel_attack项目