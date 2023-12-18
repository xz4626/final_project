package xingchen.test;

import xingchen.util.LayerUtil;
import xingchen.model.*;
import xingchen.view.Start;

import javax.swing.*;

/**
 * test rander
 */
public class TestRenderLayer extends JFrame {
    private  Layer layer = LayerUtil.buildLayer(6,6);
    public TestRenderLayer(){
        //初始化
        init();
        //渲染

        renderLayer();

        //刷新
        autoRefresh();
    }
    private void renderLayer(){
        Cell[][] cells = layer.getCells();
        for(int row = 0; row <cells.length;row++){
            for (int col = 0;col<cells[row].length;col++){
                int x = col * 59; //+ layer.getOffSetX();
                int y = row * 66; //+ layer.getOffSetY() + 40;
                Card brands1 = cells[row][col].getCard();
//                this.getContentPane().add(brands1);
                if (!brands1.getName().equals("empty")) {
                    brands1.setBounds(x, y, 59, 66);
                    this.getContentPane().add(brands1);
                }
            }
            System.out.println();
        }
    }
    private void init(){
        this.setTitle("3 Tiles");
        this.setSize(492,822);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);

        this.setLayout(null);
        this.setVisible(true);
        autoRefresh();
    }
    private void autoRefresh(){
        JFrame start = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    start.repaint();
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public static void main(String[] args) {
        new TestRenderLayer();
    }
}
