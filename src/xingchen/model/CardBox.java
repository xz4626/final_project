package xingchen.model;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CardBox {

    //公共的底部卡尺pool
    private static List<Card> pool=new ArrayList<>();

    //将card放到底部卡尺
    public void addPool(Card card){

        //先添加到pool
        pool.add(card);

        //每次添加都要重新排序并判断是否删除
        sortAndDel();

        //是否游戏结束
        isOver();

        //绘制界面
        paint();

    }

    /**
     * 排序并判断是否清楚三个相同卡片
     */
    private void sortAndDel() {
        //对卡片排序，实现分类
        pool.sort(Comparator.comparing(Card::getName));

        //消除
        //用steam流将卡片对象与他的名字放到map中
        Map<String, List<Card>> map = pool.stream().collect(Collectors.groupingBy(Card::getName));

        //去重
        Set<String> keySet = map.keySet();

        //然后拿着去重后的名字集合去遍历
        for (String s : keySet) {
            //拿名字去map里找对应的值，会返回一个集合，要是集合长度超过三，就把pool里的这个名字的卡片删除
            List<Card> cards = map.get(s);
            if (cards.size()==3){

                //延迟删除卡片，实现动画效果
                CardBox start = this;
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(500);
                            delCard(s);
                            Thread.sleep(500);
//                            isWin();
                            paint();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }).start();
            }
        }

    }

    /**
     * 删除pool里的指定名字的卡片
     */
    private void delCard(String name){

        //用迭代器删除
        //创建pool的迭代器
        Iterator<Card> iterator=pool.iterator();

        while (iterator.hasNext()){
            Card next = iterator.next();
            if (next.getName().equals(name)){
                //删除UI组件
                next.getParent().remove(next);
                //删除pool里的对应元素
                iterator.remove();
            }
        }


    }

    /**
     * 绘制底部卡池上的卡片
     */
    private void paint(){
        for (int i = 0; i < pool.size(); i++) {
            Card card = pool.get(i);
            int x=i*card.getWidth()+26;
            card.setBounds(x,643,card.getWidth(),card.getHeight());

        }
    }


    /**
     * 判断当前再添加了这个卡片后游戏是否失败
     */

    private void isOver() {
        if (pool.size() > 7) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //给弹框设置大小
            jDialog.setSize(256,268);

            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭永远无法操作下面的界面
            jDialog.setModal(true);


            //设置不可变大小
            jDialog.setResizable(false);
            //头部名称
            jDialog.setTitle("游戏结束！");

            //添加监听
            jDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // 在窗口关闭时执行自定义操作
                    System.exit(0);
                }
            });

            //创建Jlabel对象管理文字并添加到弹框当中
            JLabel warning = new JLabel(new ImageIcon("res\\寄.了.png"));

            warning.setSize(256,268);

            jDialog.getContentPane().add(warning);
            //让弹框展示出来
            jDialog.setVisible(true);

        }
    }





}










