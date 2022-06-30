package denpear.javatrain.interview.codereview;

import java.io.Serializable;
import java.util.Objects;

/**
 * Данные о платеже
 * поля description, type, paymentAmount обязательные
 * type: один из вариантов значений "currencySell", "currencyBuy" или "paymentOrder"
 * paymentAmount не должно быть отрицательным
 */
public class PaymentDto implements Comparable<PaymentDto>, Serializable {

    /**
     * Версия экземпляра сущности, для удобства берем timestamp
     */
    private static final long serialVersionUID = System.currentTimeMillis();
    String description;
    String type;
    Double paymentAmount;

    public String getDescription() {
        return description;
    }

    /**
     * Установить описание платежа
     *
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    /**
     * Установить тип платежа
     */
    public void setType(String type) {
        if (type != null) {
            if (!type.equals("currencySell") &&
                    !type.equals("currencyBuy") &&
                    !type.equals("paymentOrder")) {
                throw new IllegalArgumentException("Invalid type value :" + type);
            }
        } else {
            throw new IllegalArgumentException(" type value cannot be NULL! ");
        }
        this.type = type;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Установить сумму платежа
     *
     * @param paymentAmount сумма
     */
    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Boolean checkIfPaymentIsCorrect() throws Exception {
        if (type != null) {
            if (!type.equals("currencySell") &&
                    !type.equals("currencyBuy") &&
                    !type.equals("paymentOrder")) {
                return false;
            }
        } else {
            return false;
        }
        if (description.length() < 1) {
            return false;
        }
        if (paymentAmount < -1) {
            throw new Exception("Amount must be positive!");
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean equals(Object o) {
        PaymentDto that = (PaymentDto) o;
        return description == that.description && this.compareTo(that) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type, paymentAmount);
    }

    @Override
    public String toString() {
        String result = "{";
        result = result + "'description':'" + description + "',";
        result = result + "'type':'" + type + "',";
        result = result + "'paymentAmount':'" + paymentAmount.toString() + "',";
        result = result + "}";
        return result;
    }

    @Override
    public int compareTo(PaymentDto other) {
        if (other.paymentAmount == this.paymentAmount) {
            return 0;
        } else {
            return other.paymentAmount.compareTo(this.getPaymentAmount());
        }
    }

}
