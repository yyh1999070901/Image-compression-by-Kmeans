public class Point implements KmObj {
    int x;
    int y;
    int group;
    static double threshold = 0.01;
    public Point()
    {

    }

    public Point(int x, int y, int group)
    {
        this.x = x;
        this.y = y;
        this.group = group;
    }

    @Override
    public double getDistance(KmObj other) {
        Point otherPoint = (Point)other;
        return Math.sqrt((this.x-otherPoint.x)*(this.x-otherPoint.x)
                        +(this.y-otherPoint.y)*(this.y-otherPoint.y));
    }

    @Override
    public int compareTo(KmObj other) {
        if(getDistance((Point)other)<=threshold)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public KmObj getCenter(KmObj[] objs, int[] serials) {
        Point[] points = (Point[])objs;
        Point center = new Point();
        int x_sum = 0;
        int y_sum = 0;
        int length = serials.length;
        for(int i=0;i<length;i++)
        {
            int t = serials[i];
            x_sum += points[t].x;
            y_sum += points[t].y;
        }
        center.x=x_sum/length;
        center.y=y_sum/length;
        return center;
    }

    @Override
    public void setGroup(int group) {
        this.group=group;
    }

    @Override
    public int getGroup() {
        return this.group;
    }
}
