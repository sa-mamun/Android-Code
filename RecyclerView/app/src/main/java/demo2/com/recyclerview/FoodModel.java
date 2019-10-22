package demo2.com.recyclerview;

public class FoodModel {

    String foodName;
    String foodDesc;
    int foodImage;

    public FoodModel(String foodName, String foodDesc, int foodImage) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }
}
