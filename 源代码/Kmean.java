import java.util.ArrayList;
import java.util.Random;

public class Kmean {
    static public KmObj[] old_center = null;//用于存放老的簇中心
    static public KmObj[] new_center = null;//用于存放新的簇中心
    static double threshold = 0.01;//停止迭代的距离阈值
    static double max_time=100;//最大迭代次数

    public static void init_center(KmObj[] objs, int k ){
        //随机产生k个簇中心
        old_center = new KmObj[k];
        new_center = new KmObj[k];
        Random random = new Random();
        int rands[]=new int[k];//用于存放k个随机数字
        for(int i=0;i<k;i++)
        {
            int flag=0;
            int rand = random.nextInt(objs.length-1);
            for(int j=0;j<i;j++)//用于避免产生重复数字
            {
                if(rands[j]==rand)
                {
                    flag=1;//产生重复数字
                    break;
                }
            }
            if(flag == 1)
            {
                i--;
            }
            else
            {
                old_center[i] = objs[rand];
                rands[i]=rand;
            }
        }
    }

    public static void search_center(KmObj[] objs)//找到样本集中每一个点属于的簇，并且更新每个点的簇号
    {
        for(int i=0;i<objs.length;i++)
        {
            double min_distance = 1000000;
            for(int j=0;j<old_center.length;j++)
            {
                double distance = objs[i].getDistance(old_center[j]);//计算每一个点和簇心的距离
                if(distance<min_distance)//如果发现相异度更小的簇心
                {
                    min_distance=distance;//更新最小距离
                    objs[i].setGroup(j);//将样本集中的点划归到新的簇中，点i的group是第簇心集合的第j个。
                }
            }
        }
    }

    //通过接口getcenter(),找到每一个簇的新的簇心,并且更新簇心操作
    public static void update_center(KmObj[] objs)
    {
        KmObj temp = null;
        for(int j=0;j<old_center.length;j++)//找到原始k个簇的簇心
        {
            ArrayList<Integer> serials = new ArrayList<>();
            for(int i =0;i<objs.length;i++)//遍历样本集中每一个点，记录属于第j个簇的点
            {
                if (objs[i].getGroup() == j) {
                    serials.add(i);//把第i个点下标加入到第j个簇中
                    temp = objs[i];
                }
            }
            int []serial=new int[serials.size()];
            for(int k=0;k<serial.length;k++)//getCenter的参数为int[],需要转换动态数组到int[],赋值操作比较安全
            {
                serial[k]=serials.get(k);
            }
            new_center[j]=temp.getCenter(objs,serial);//找到和temp属于同一个簇的新中心
        }
    }

    //判断终止条件:如果k个旧中心和新中心的距离都小于阈值，则停止迭代，返回true
    public static Boolean checkFinish(int k)
    {
        Boolean flag=true;
        for(int j=0;j<old_center.length;j++)
        {
            if(old_center[j].getDistance(new_center[j])>=threshold)//如果发现某一个中心点位移过大，不停止迭代
            {
                flag=false;
                break;
            }
        }
        return flag;
    }

    public static void copyNewCenter(int k)//将更新后的簇心作为新簇心
    {
        for(int j=0;j<k;j++)
        {
            old_center[j]=new_center[j];
        }
    }

    public static KmObj[] getOld_center()//获得最后的簇中心
    {
        return old_center;
    }

    public static int[][] kmean(KmObj[] objs, int k){
        //随机选取k个点作为初始的中心
        init_center(objs,k);

        //计算所有点到簇中心的距离，并将元素划归到距离最小的簇
        search_center(objs);

        //更新簇心操作
        update_center(objs);

        //判断是否可以终止，循环迭代
        while(!checkFinish(k)||max_time==0)
        {
            max_time--;//最大迭代次数减小
            copyNewCenter(k);//将更新后的簇心作为新簇心
            search_center(objs);
            update_center(objs);
        }

        //调整输出,ans[i]代表了属于第i个簇的所有点的下标
        int ans[][]=new int[k][objs.length];
        int occupy[] = new int[k];//occupy表示第i个簇有多少个点
        for(int i=0;i<objs.length;i++)
        {
            ans[objs[i].getGroup()][occupy[objs[i].getGroup()]]=i;
            occupy[objs[i].getGroup()]++;
        }
        return ans;
    }
}
