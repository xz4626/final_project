package xingchen.test;

import xingchen.model.Card;
import xingchen.model.Cell;
import xingchen.model.Layer;
import xingchen.model.Map;
import xingchen.util.LayerUtil;
import xingchen.util.MapUtil;

import javax.swing.*;
import java.util.List;

/**
 * test rander
 */
public class TestRenderMap extends JFrame {
    private Map map = MapUtil.build(3);
    public TestRenderMap(){
        //初始化
        init();
        //渲染
        List<Layer> list = map.getList();
        for(int i=0;i<list.size();i++){
            renderLayer(list.get(i));
        }

        //刷新
        autoRefresh();
    }
    private void renderLayer(Layer layer){
        Cell[][] cells = layer.getCells();
        layer.showCells();
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
        this.setSize(550,822);
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
        new TestRenderMap();
    }
}
