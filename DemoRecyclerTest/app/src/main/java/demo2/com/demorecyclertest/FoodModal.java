package demo2.com.demorecyclertest;

public class FoodModal {

    String foodName;
    String foodDesc;
    int foodImage;

    public FoodModal(String foodName, String foodDesc, int foodImage) {
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
