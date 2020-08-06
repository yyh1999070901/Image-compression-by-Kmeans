public interface KmObj extends Comparable<KmObj> {
    public double getDistance(KmObj other); //求两点间的距离
    public int compareTo(KmObj other); //点排序，为了核对聚类中心是否重合
    public KmObj getCenter(KmObj[] objs, int[] serials); // 通过serials选定标号，求它们的中心
    public void setGroup(int group);//通过接口设置分组
    public int getGroup();//通过接口获得分组
}
