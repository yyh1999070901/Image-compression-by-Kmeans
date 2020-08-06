import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PhotoCompression {
    //public static String path="D:\\java_workplace\\DM_ex1\\src\\img\\Trump.jpg";
    public static String path ;
    public static String path2;
    public static int[][] readImage() {
        BufferedImage bi = null;
        try
        {
            bi = ImageIO.read(new File(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int [][] data = new int[width][height];
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                data[i][j] = bi.getRGB(i, j);//rgb
            }
        }
        return data;
    }

    public static void writeImage(int[][] data) {
        BufferedImage nbi = new BufferedImage(data.length, data[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < data.length; i++)
        {
            for (int j = 0; j < data[0].length; j++)
            {
                int color = data[i][j];
                int red = ((color & 0x00FF0000) >> 16);
                int green = ((color & 0x0000FF00) >> 8);
                int blue = color & 0x000000FF;
                Color c = new Color(red,green,blue);
                nbi.setRGB(i, j, c.getRGB());
            }
        }
        try
        {
            ImageIO.write(nbi, "jpg", new File(path));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Pixel[] toPiexl(int [][]data)//将读入的图片转换为像素矩阵
    {
        Pixel[] pixels = new Pixel[data.length*data[0].length];
        int cnt =0;
        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[0].length;j++)
            {
                int color = data[i][j];
                int red = ((color & 0x00FF0000) >> 16);
                int green = ((color & 0x0000FF00) >> 8);
                int blue = color & 0x000000FF;
                pixels[cnt]=new Pixel(i,j,red,green,blue);
                cnt++;
            }
        }
        return pixels;
    }

    public static Pixel[] afterCompressed(Pixel[]pixels,int[][] data, Pixel[] oldCenter,int wideth,int height)
    {
        Pixel[] ans = new Pixel[wideth*height];
        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[0].length;j++)
            {
                ans[data[i][j]]=new Pixel();
                int red = oldCenter[i].red;
                ans[data[i][j]].red=red;
                int blue = oldCenter[i].blue;
                ans[data[i][j]].blue=blue;
                int green=oldCenter[i].green;
                ans[data[i][j]].green=green;
                ans[data[i][j]].x=pixels[data[i][j]].x;
                ans[data[i][j]].y=pixels[data[i][j]].y;
            }
        }
        return ans;
    }

    //根据像素数组得到rgb矩阵
    public static void RGBImage(Pixel[] pixels,int width,int height)
    {
        BufferedImage nbi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<pixels.length;i++)
        {
            Color c = new Color(pixels[i].red,pixels[i].green,pixels[i].blue);
            nbi.setRGB(pixels[i].x,pixels[i].y,c.getRGB());
        }
        try
        {
            ImageIO.write(nbi, "jpg", new File(path2));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void  writeImage(Pixel[] pixels,int wideth,int height,int k)//根据像素数组压缩图片输出
    {
        Kmean hangKmean = new Kmean();
        int compressed[][]=hangKmean.kmean(pixels,k);

        //将结果转换为像素数组进行输出
        Pixel[] oldCenter = new Pixel[hangKmean.old_center.length];
        for(int i=0;i<hangKmean.old_center.length;i++)
        {
            oldCenter[i]=(Pixel)hangKmean.old_center[i];
        }
        Pixel[] compressedPiexl = afterCompressed(pixels,compressed,oldCenter,wideth,height);

        //将像素数组转换为rgb矩阵输出
        PhotoCompression.RGBImage(compressedPiexl,wideth,height);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入读入图片的绝对路径：");
        path = scanner.next();
        System.out.println("请输入保存图片的绝对路径：");
        String path3=scanner.next();
        int image[][]=PhotoCompression.readImage();//得到像素矩阵
        int wideth=image.length;
        int height=image[0].length;
        Pixel[] pixels = PhotoCompression.toPiexl(image);//转换为像素数组
        int kk[] = {1,5,10,20,30,50,100};
        for(int i=0;i<kk.length;i++)
        {
            path2=path3+"\\Trump_"+String.valueOf(kk[i])+".jpg";
            writeImage(pixels,wideth,height,kk[i]);
        }
    }
}
