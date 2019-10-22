package demo2.com.demorecyclertest;

public class FashionModel {

    String fashionName;
    String fashionDesc;
    int fashionImage;

    public FashionModel(String fashionName, String fashionDesc, int fashionImage) {
        this.fashionName = fashionName;
        this.fashionDesc = fashionDesc;
        this.fashionImage = fashionImage;
    }

    public String getFashionName() {
        return fashionName;
    }

    public void setFashionName(String fashionName) {
        this.fashionName = fashionName;
    }

    public String getFashionDesc() {
        return fashionDesc;
    }

    public void setFashionDesc(String fashionDesc) {
        this.fashionDesc = fashionDesc;
    }

    public int getFashionImage() {
        return fashionImage;
    }

    public void setFashionImage(int fashionImage) {
        this.fashionImage = fashionImage;
    }
}
