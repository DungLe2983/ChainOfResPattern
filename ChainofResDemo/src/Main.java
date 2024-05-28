
abstract class DiscountHandler {
    protected DiscountHandler nextHandler;

    public void setNextHandler(DiscountHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract double applyDiscount(double originalPrice);
}

class MembershipDiscountHandler extends DiscountHandler {
    public double applyDiscount(double originalPrice) {
        double discountedPrice = originalPrice;
        if (originalPrice >= 100 && originalPrice < 200) {
            discountedPrice *= 0.9; // Giảm giá 10%
        }
        if (nextHandler != null) {
            return nextHandler.applyDiscount(discountedPrice);
        }
        return discountedPrice;
    }
}

class ProductTypeDiscountHandler extends DiscountHandler {
    public double applyDiscount(double originalPrice) {
        double discountedPrice = originalPrice;
        if (originalPrice >= 200 && originalPrice < 500) {
            discountedPrice -= 50; // Giảm giá 50 đơn vị
        }
        if (nextHandler != null) {
            return nextHandler.applyDiscount(discountedPrice);
        }
        return discountedPrice;
    }
}

class SpecialOfferDiscountHandler extends DiscountHandler {
    public double applyDiscount(double originalPrice) {
        double discountedPrice = originalPrice;
        if (originalPrice >= 500) {
            discountedPrice *= 0.8; // Giảm giá 20%
        }
        if (nextHandler != null) {
            return nextHandler.applyDiscount(discountedPrice);
        }
        return discountedPrice;
    }
}

public class Main {
    public static void main(String[] args) {
        // Khởi tạo các bước xử lý
        DiscountHandler membershipDiscountHandler = new MembershipDiscountHandler();
        DiscountHandler productTypeDiscountHandler = new ProductTypeDiscountHandler();
        DiscountHandler specialOfferDiscountHandler = new SpecialOfferDiscountHandler();

        // Thiết lập chuỗi xử lý
        membershipDiscountHandler.setNextHandler(productTypeDiscountHandler);
        productTypeDiscountHandler.setNextHandler(specialOfferDiscountHandler);

        // Tạo yêu cầu giảm giá
        double originalPrice = 600;

        // Áp dụng giảm giá vào chuỗi xử lý
        double finalPrice = membershipDiscountHandler.applyDiscount(originalPrice);

        System.out.println("Giá gốc: " + originalPrice);
        System.out.println("Giá sau khi giảm giá: " + finalPrice);
    }
}