package xingchen.util;

import xingchen.model.Card;
import xingchen.model.Cell;
import xingchen.model.Layer;
import xingchen.model.Map;

import java.awt.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;


public class MapUtil {
    public static int getAllCard() {
        return allCard;
    }

    //所有图层上的所有卡片  再减去置空的卡片就是游戏界面上的卡片
    private static int allCard;

    //每个图层的默认大小  6*6
    private static final int ROW = 6;
    private static final int COL = 6;


    private static void visitGetNullCard(Map map) {

        List<Layer> list = map.getList();

        //狠狠的遍历
        for (Layer layer : list) {

            Cell[][] cells = layer.getCells();

            for (int row1 = 0; row1 < cells.length; row1++) {
                for (int col1 = 0; col1 < cells[row1].length; col1++) {

                    Card card = cells[row1][col1].getCard();
                    if (card.getName().equals("空空")) {
                        allCard--;
                    }

                }
            }
        }

    }

    public static Map build(Integer floorHeight) throws URISyntaxException {

        Map map = new Map();


        allCard = floorHeight * ROW * COL;

        map.setFloorHeight(floorHeight);

        List<Layer> list = map.getList();

        Random random = new Random();

        //两个暂存变量 要先置空
        Layer layerOf1 = null;
        Layer layerOf2 = null;


        for (int i = 0; i < floorHeight; i++) {

            //构建图层
            Layer layer = LayerUtil.buildLayer(ROW, COL);


            int bound1 = 50;




            if (i%2==0){//偶

                layer.setOffSetX(bound1+29);
                layer.setOffSetY(bound1+33);
                layerOf1 = layer;
                layer.setParent(layerOf2);//链式关系
            }else { //奇数

                layer.setOffSetX(bound1);
                layer.setOffSetY(bound1);
                layerOf2 = layer;
                layer.setParent(layerOf1);//链式关系
            }

            list.add(layer);
        }



        visitGetNullCard(map);
        return map;
    }




    public static boolean compare(Card card, Layer layer) {

        Cell[][] cells = layer.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];


                if (cell.isState()) {
                    Rectangle bounds = card.getBounds();
                    Rectangle bounds1 = cell.getCard().getBounds();

                    if (bounds1.intersects(bounds)) {
                        return true;
                    }
                }
            }
        }


        if (layer.getParent() == null) {
            return false;
        } else {

            return compare(card, layer.getParent());
        }
    }


}
