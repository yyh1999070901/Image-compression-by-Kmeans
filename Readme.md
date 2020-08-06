实现方法:

一、 定义接口类（KmObj）

接口类KmObj定义类五个抽象方法，在实际的子类具体实现。这样做可以实现上转型，通过子类对象(小范围)实例化父类对象(大范围)。即静态方法public static int[][] kmean(KmObj[] objs, int k)中的形参为接口类KmObj，但实参为具体类，这样做的目的在于，当我们需要多个同父的对象调用某个方法时,通过向上转换后,则可以确定参数的统一.方便程序设计。

![img](https://i.loli.net/2020/08/06/yU7TjhQndfJEvNo.jpg)

二、 定义具体类实现接口

a)  定义像素类

i．定义像素类属性，分别为像素点横纵坐标，和RGB三通道的值。定义分组属性便于后续函数筛选属于同一簇像素点。

![img](https://i.loli.net/2020/08/06/AkgjFdi4MQluLPt.jpg)

ii. 在像素类中实现接口中的方法。

计算像素点之间的欧式距离

![img](https://i.loli.net/2020/08/06/dI9A5SnVzC3KW7J.jpg)

通过距离判断两点是否可以重合

![img](https://i.loli.net/2020/08/06/qsz2rx84bTFmpVD.jpg)

 

根据下标数组，确定簇心，实现方法计算各个簇分向量的算术平均值

![img](https://i.loli.net/2020/08/06/3rdo9a4fysmbizK.jpg)

b)  定义点类（实现方法和像素类类似，详细代码可参见源码）

三、 核心类Kmean，定义接口类为各个函数形参，可以处理各种类型数据输入

a)  定义Kmean的属性

![img](https://i.loli.net/2020/08/06/t3M6kOHxj7fSXzU.jpg)

b)  初始随机选择k个中心点

通过flag变量，确定是否产生了重复的中心点。

![img](https://i.loli.net/2020/08/06/e78rdwxmWB3Coai.jpg)

 

c)   根据每个点到簇心的距离，把每个点划归到距离最近的中心点所在簇中

![img](https://i.loli.net/2020/08/06/BrILv8S23EDyalN.jpg)

d)  更新每个簇中的新中心点，调用抽象方法接口中抽象方法getCenter

![img](https://i.loli.net/2020/08/06/LUMoqwebR1GpvBs.jpg)

e)  判断终止条件：当迭代次数达到最大或者所以中心点几乎不移动

![img](https://i.loli.net/2020/08/06/Ln57tvlgQAPcD2E.jpg)

f)   Kmean方法，初始化k个中心点，划归各个点到相异度最小的簇中，更新簇心，直到中心点不再变化或者迭代次数达到最大，调整输出结果，为要求格式。

![img](https://i.loli.net/2020/08/06/NrSchn8ZyCPRVOs.jpg)

![img](https://i.loli.net/2020/08/06/LvxAIY2skaOzr85.jpg)

四、 图片压缩类

a)  将图像的rgb图像转化为像素数组作为输入

![img](https://i.loli.net/2020/08/06/6HwBMsXlb3FvL7t.jpg)

b)  根据Kmean结果获得转换压缩后的像素数组

![img](https://i.loli.net/2020/08/06/Wgt7UrTiN1bl9PR.jpg)

c)   根据像素数组输出图像

![img](https://i.loli.net/2020/08/06/OTLZb4aBdnIsq7P.jpg)

五、 Java GUI

设置鼠标监听器，将界面上的点转换为点数组，输入模型后，保存结果，绘制中心点

![img](https://i.loli.net/2020/08/06/o63pIgaNMFRzXlL.jpg)

遍历点集合，将中心点和样本集用不同颜色区分开

![img](https://i.loli.net/2020/08/06/ox1IXEjgCB6vluO.jpg)

结果展示：

一、 java GUI

![img](https://i.loli.net/2020/08/06/CEumpsD29Vl6gMb.jpg)

K=2

 

![img](https://i.loli.net/2020/08/06/xB8aecnOqtS72jg.jpg)

K=3

二、 图像压缩

![img](https://i.loli.net/2020/08/06/tkyadi8usB6CpSz.jpg)

K=1

![img](https://i.loli.net/2020/08/06/6oEswt18KvjIDNn.jpg)

K=5

![img](https://i.loli.net/2020/08/06/eIcKDCNuMFZR2LG.jpg)

K=10

![img](https://i.loli.net/2020/08/06/QedBFtanGPx6EY1.jpg)

K=20

![img](https://i.loli.net/2020/08/06/14ciPjz6hMIuQtf.jpg)

K=30

![img](https://i.loli.net/2020/08/06/d3pE9NzGsKDMLtO.jpg)

K=50

![img](https://i.loli.net/2020/08/06/RUutZj5pe4nwiKH.jpg)

K=100