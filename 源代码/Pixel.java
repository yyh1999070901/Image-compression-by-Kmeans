public class Pixel implements KmObj {
    int x;
    int y;
    int red;
    int green;
    int blue;
    int group;//记录分组

    static double threshold = 0.01;
    public Pixel()
    {

    }

    public Pixel(int x, int y, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Pixel(int x, int y, int red, int green, int blue, int group) {
        this.x = x;
        this.y = y;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.group = group;
    }

    @Override
    public double getDistance(KmObj other) {
        Pixel otherPixel = (Pixel)other;
        return Math.sqrt(
                (this.x-otherPixel.x) * (this.x-otherPixel.x)
                +(this.y-otherPixel.y)*(this.y-otherPixel.y)
                +(this.red-otherPixel.red)*(this.red-otherPixel.red)
                +(this.green-otherPixel.green)*(this.green-otherPixel.green)
                +(this.blue-otherPixel.blue)*(this.blue-otherPixel.blue)
        );
    }

    @Override
    public int compareTo(KmObj other) {
        if(getDistance((Pixel)other)<threshold)
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
        Pixel[] pixels = (Pixel[])objs; //转换成像素类数组
        Pixel center = new Pixel();//保存中心节点
        int x_sum = 0;
        int y_sum = 0;
        int red_sum = 0;
        int green_sum = 0;
        int blue_sum = 0;
        int length = serials.length;
        for(int i=0;i<length;i++)
        {
            int t = serials[i];
            x_sum += pixels[t].x;
            y_sum += pixels[t].y;
            red_sum += pixels[t].red;
            green_sum += pixels[t].green;
            blue_sum += pixels[t].blue;
        }
        center.x=x_sum/length;
        center.y=y_sum/length;
        center.red=red_sum/length;
        center.green=green_sum/length;
        center.blue=blue_sum/length;
        return center;
    }

    @Override
    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public int getGroup() {
        return group;
    }
}
