package xingchen.util;

import xingchen.model.Card;
import xingchen.model.Cell;
import xingchen.model.Layer;
import xingchen.model.Map;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * //TODO
 *
 * 地图工具类
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 16:00
 */
public class MapUtil {
    public static int getAllCard() {
        return allCard;
    }

    //所有图层上的所有卡片  再减去置空的卡片就是游戏界面上的卡片
    private static int allCard;

    //每个图层的默认大小  6*6
    private static final int ROW = 6;
    private static final int COL = 6;

    /**
     * 遍历地图每一层上的每一个卡片
     * 找到一个空卡片就allCard--
     *
     * @param map 图层
     */
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

    /**
     * 构建地图
     * 可以改变层数了
     * @param floorHeight 层数
     * @return 地图
     */
    public static Map build(Integer floorHeight) {

        Map map = new Map();

        //未稀疏化的卡片总数
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

//            int bound1 = random.nextInt(8)*10 + 50;
            int bound1 = 50;

//            //分别设置每个图层的偏移量
//            layer.setOffSetX(random.nextInt(bound1));
//            layer.setOffSetY(random.nextInt(bound1 + 30));


            //一个暂存变量会覆盖，没有效果
            //用两个变量来交替暂存，这样就能构建图层的链式结构！
            if (i%2==0){//偶
                //分别设置每个图层的偏移量  修改了随即偏移量，变成了同样的固定遮盖偏移量
                layer.setOffSetX(bound1+29);
                layer.setOffSetY(bound1+33);
                layerOf1 = layer;
                layer.setParent(layerOf2);//链式关系
            }else { //奇数
                //分别设置每个图层的偏移量
                layer.setOffSetX(bound1);
                layer.setOffSetY(bound1);
                layerOf2 = layer;
                layer.setParent(layerOf1);//链式关系
            }

            //添加到集合
            list.add(layer);
        }


        //遍历一下，得到所有卡片的总数
        visitGetNullCard(map);
        return map;
    }


    /**
     * 比较卡片又没有被图层遮盖，被遮盖
     *
     * @param card  卡片
     * @param layer 图层
     * @return 被遮盖true 没有false
     */

    public static boolean compare(Card card, Layer layer) {

        Cell[][] cells = layer.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];

                //如果当前格子有牌，就要判断，
                if (cell.isState()) {
                    //这是Java里的比较矩阵有没有交际的方法
                    Rectangle bounds = card.getBounds();
                    Rectangle bounds1 = cell.getCard().getBounds();

                    //ture 说明被遮盖
                    if (bounds1.intersects(bounds)) {
                        return true;
                    }
                }//无牌，就不用比较了，直接去看下一个格子
            }
        }

        //如果要是没有父亲，说明是顶层了，就没有遮盖
        if (layer.getParent() == null) {
            return false;
        } else {
            //再次递归去调用
            return compare(card, layer.getParent());
        }
    }


}
