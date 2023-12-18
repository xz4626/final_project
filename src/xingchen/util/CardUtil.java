package xingchen.util;

import xingchen.model.Card;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;


public class CardUtil {
    static ArrayList<String> cardArr = new ArrayList<>();

    /**
     * 从25张图片里随机获取名字buildLayer
     * @return 名字
     */
    private static String getRandomName() {
        //将所有的卡片的文件名添加到列表
        File res = new File("xingchen.res");
        File[] ress = res.listFiles();//得到源文件的子目录
        for (File file : ress) {
            String[] fileName = file.getName().split("\\.");

            if (fileName[1].equals("png")) {
                if (fileName[0].length() == 2) {
                    cardArr.add(fileName[0]);
                }
            }
        }
        Random random = new Random();
        return cardArr.get(random.nextInt(25));
    }


    /**
     * 构建卡片数组
     * @param capacity 数组大小
     * @return  数组
     */
    public static Card[] buildCards(Integer capacity) {


        Card[] cards = new Card[capacity];

        Random random = new Random();
        //生成图层的一维数组
        //一次生产三张卡片，但是注意图层要是3的倍数！！！！！！ 三个才能消除
        for (int i = 0; i < cards.length; i = i + 3) {

            String randomName = getRandomName();
            Card card1 = new Card(randomName);
            Card card2 = new Card(randomName);
            Card card3 = new Card(randomName);

            cards[i] = card1;
            cards[i + 1] = card2;
            cards[i + 2] = card3;
        }


//打乱数组
        //再用个循环遍历数组把里面元素打乱
        for (int i = 0; i < cards.length; i++) {
            //暂存变量
            Card temp = cards[i];
            //i位置与随机索引的值交换
            int r = random.nextInt(cards.length);
            cards[i] = cards[r];
            cards[r] = temp;
        }
        return cards;
    }


}
