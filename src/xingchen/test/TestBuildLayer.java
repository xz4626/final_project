//package xingchen.test;
//
//import xingchen.model.Brand;
//import xingchen.model.Cell;
//import xingchen.model.Layer;
//
//import java.util.Random;
//
//public class TestBuildLayer {
//    public static Random random = new Random();
//    public static String[] brandNames= {
//            "apple","bee","","bell",
//            "brush","bottle","bonfire",
//            "bucket","cabbage","chestnut",
//            "hat","hay","gloves",
//            "jar","mask","grass",
//            "moon","pumpkin","radish",
//            "rabbit","rake","rice",
//            "scissors","stump","tree",
//            "yarn"
//    };
//
//    public static String getBrandName() {
//        int randomIndex=random.nextInt(brandNames.length);
//        return brandNames[randomIndex];
//    }
//
//    public static void main(String[] args)  {
//        Layer layer = null;
//        try {
//            layer = new Layer(6,6);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        //        System.out.println(getBrandName());
//        Brand[] brands = new Brand[layer.getCapacity()];
//        for (int i = 0; i<brands.length;i=i+3){
//            String randomBrandName = getBrandName();
//            Brand brand1 = new Brand(randomBrandName);
//            Brand brand2 = new Brand(randomBrandName);
//            Brand brand3 = new Brand(randomBrandName);
//            brands[i] = brand1;
//            brands[i+1] = brand2;
//            brands[i+2] = brand3;
//        }
//        for (int i = 0; i<brands.length;i++){
//            Brand brandA = brands[i];
//            int randomIndex = random.nextInt(brands.length);
//            Brand brandB = brands[randomIndex];
//            Brand temp = brandA;
//            brands[i]=brandB;
//            brands[randomIndex]=temp;
//        }
//
////        for (int i = 0; i<brands.length;i++){
////            System.out.print(brands[i].getName()+"-");
////        }
//
//        Cell[][] cells = layer.getCells();
//
//        int flag=0;
//        for(int row = 0; row <cells.length;row++){
//            for (int col = 0;col<cells[row].length;col++){
//                Cell cell = new Cell();
//                cell.setState(1);
//                cell.setBrand(brands[flag++]);
//
//                cells[row][col]=cell;
//            }
//        }
//
//        for(int row = 0; row <cells.length;row++){
//            for (int col = 0;col<cells[row].length;col++){
//                 Brand brands1 = cells[row][col].getBrand();
//                System.out.print(brands1.getName()+"-");
//            }
//            System.out.println();
//        }
//    }
//}
