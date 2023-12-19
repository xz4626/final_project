package xingchen.model;

import xingchen.util.MapUtil;

import java.util.ArrayList;
import java.util.List;

public class Map {
    //层数
    private Integer floorHeight;

    //图层集合
    private List<Layer> list = new ArrayList<>();


    public Map() {

    }

    public Integer getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(Integer floorHeight) {
        this.floorHeight = floorHeight;
    }

    public List<Layer> getList() {
        return list;
    }

    public void setList(List<Layer> list) {
        this.list = list;
    }

    /**
     * 比较所有的卡片，判断是否置灰
     */
    public void compareAll() {

        List<Layer> layers = this.getList();

        for (int i = 1; i < this.floorHeight; i++) {

            Layer layer = layers.get(i);
            Cell[][] cells = layer.getCells();

            for (int row = 0; row < cells.length; row++) {
                for (int col = 0; col < cells[row].length; col++) {
                    Cell cell = cells[row][col];
                    //先判断这个格子有没有牌
                    if (cell.isState()) {//有
                        Card card = cell.getCard();
                        boolean compare = MapUtil.compare(card, layer.getParent());
                        card.setGray(compare);
                    }

                }

            }


        }


    }


}
