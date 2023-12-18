package xingchen.util;

import xingchen.model.Card;
import xingchen.model.Cell;
import xingchen.model.Layer;

import java.net.URISyntaxException;
import java.util.Random;

import static xingchen.util.CardUtil.buildCards;


public class LayerUtil {

    /**
     * 构建一个指定行列大小的图层
     * @param row 行
     * @param col 列
     * @return 图层
     */
    public static Layer buildLayer(Integer row ,Integer col) throws URISyntaxException {


        Layer layer = new Layer(row,col);

        //单元格数组
        Cell[][] cells = layer.getCells();

        //对应的卡片数组
        Card[] cards = buildCards(layer.getCapacity());

        int index=0;
        for (int row1 = 0; row1 < cells.length; row1++) {
            for (int col1 = 0; col1 < cells[row1].length; col1++) {

                //创建一个单元格
                Cell cell = new Cell();
                //当前循环的卡片
                Card card = cards[index++];

                //设置单元格状态
                cell.setState(true);
                //给单元格添加卡片
                cell.setCard(card);
                //给卡片中留住她所存放的单元格
                card.setCell(cell);

                cells[row1][col1]=cell;
            }
        }
        //稀疏化
        initLayer(cards);
        return layer;
    }
    /**
     * 让图层稀疏
     * 将部分卡片名字置为：空空
     *
     * @param cards 要被变成稀疏数组的数组
     */
    private static void initLayer(Card[] cards) {
        Random random = new Random();
        for (int j = 0; j < 6; j++) {
            int i1 = random.nextInt(36);
            String name1 = cards[i1].getName();
            for (int i = 0; i < cards.length; i++) {
                if (cards[i].getName().equals(name1)) {

                    cards[i].setName("空空");

                }
            }
        }
    }
}
