package imeng.effectivejava.catalog.chapter2.section2;

/**
 * @Author : Administrator
 * @Date : 2016/5/25 15:24
 * @Version:
 */
public class NutrutionFacts {
    private int servingSize; // (mL) required
    private int servings; // (per container) required
    private int calories; // optional
    private int fat; // (g) optional
    private int sodium; // (mg) optional
    private int carbohybrate; // (g) optional


    public static class Builder { //static 是为了能够直接通过类名字进行访问的。
        // Required parameters
        private int servingSize; // (mL)
        private int servings; // (per container)

        // Optional parameters
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohybrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder carbohybrate(int val) {
            carbohybrate = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public NutrutionFacts build() {
            return new NutrutionFacts(this);
        }
    }

    private NutrutionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohybrate = builder.carbohybrate;
    }


}









































