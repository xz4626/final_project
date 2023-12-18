package xingchen.view;


import java.net.URISyntaxException;

public class Start {
    public static void main(String[] args) throws URISyntaxException {

        /*
        准备实现关卡，在主界面里实现，通过iswin()来判断，赢了，就直接关闭当前JFrame然后新创建一个JFrame
        只有通过了指定关卡，才显示游戏胜利
        那么，iswin也要改的
         */

        /*
        实现的关卡
        但是会出现某一关卡片消除不完的死局情况
        可能是因为卡片重复

        可以每一层的牌不重复
        就是每种只有三个
        这样计算稀疏后的卡片数就容易了

        把
         */
        new YangLeGeYang(2);



    }
}
