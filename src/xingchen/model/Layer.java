package xingchen.model;


public class Layer {

    //图层的前驱
    private Layer parent ;

    public Layer getParent() {
        return parent;
    }

    public void setParent(Layer parent) {
        this.parent = parent;
    }

    private Integer offSetX;
    private Integer offSetY;

    //行
    private Integer rowNum;

    //列
    private Integer colNum;

    public Integer getOffSetX() {
        return offSetX;
    }

    public void setOffSetX(Integer offSetX) {
        this.offSetX = offSetX;
    }

    public Integer getOffSetY() {
        return offSetY;
    }

    public void setOffSetY(Integer offSetY) {
        this.offSetY = offSetY;
    }

    //最大容量
    private Integer capacity;


    //当前图层目前有多少卡片,动态可变的，不能超过最大容量
    private Integer size;


    //存放卡片的数组
    private Cell[][] cells=null;//[4][5]



    public Layer(Integer rowNum, Integer colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;

        this.capacity = this.rowNum*this.colNum;

        this.cells = new Cell[this.rowNum][this.colNum];

        this.size=0;
    }




    public void showCells(){
        System.out.println("-------------------------------------");
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                System.out.print(cells[row][col].getCard().getName()+"  ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");

    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getColNum() {
        return colNum;
    }

    public void setColNum(Integer colNum) {
        this.colNum = colNum;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
