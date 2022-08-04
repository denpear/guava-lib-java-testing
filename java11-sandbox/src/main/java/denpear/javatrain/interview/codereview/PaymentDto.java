package denpear.javatrain.interview.codereview;

import java.io.Serializable;

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
/*
   //автогенерация Guava та же, что и Java 7+, только ограничение по notnull description не запрашивается
   // Objects.equals(this.description,that.description); // нужно применять в случае nullable объектов

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentDto)) return false;
        PaymentDto that = (PaymentDto) o;
        return Objects.equal(description, that.description) && Objects.equal(type, that.type) && Objects.equal(paymentAmount, that.paymentAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description, type, paymentAmount);
    }*/

    /*
    // автогенерация от Java 7+ потому что указано было, что description мб нулевым
    // Objects.equals(this.description,that.description); // нужно применять в случае nullable объектов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentDto)) return false;
        PaymentDto that = (PaymentDto) o;
        return Objects.equals(description, that.description) && type.equals(that.type) && paymentAmount.equals(that.paymentAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type, paymentAmount);
    }
*/

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

    // Из книги про Hibernate

    /**
     * Java определяет два различных понятия тождественности:
     * 1) идентичность экземпляров (грубо говоря, совпадение адресов в памяти;
     * проверяется как a == b);
     * 2) равенство экземпляров, определяемое методом equals() (также называется равенством по значению).
     * 3) Objects.equals(this.description,that.description); // нужно применять в случае nullable (мб нулевыми, так в коде) объектов
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDto that = (PaymentDto) o;
        if (!this.paymentAmount.equals(that.paymentAmount)) return false;
        if (!this.type.equals(that.type)) return false;
        return true;
    }

    // Из книги про Hibernate
    @Override
    public int hashCode() {
        int result = paymentAmount.hashCode();
        result = 31 * result + this.type.hashCode();
        return result;
    }
/*
    //Было так, но:
    // 1) описание может быть необязательным
    // 2) в хеш-код лучше загонять Immutable Objects
    // 3)
    @Override
    public boolean equals(Object o) {
        PaymentDto that = (PaymentDto) o;
        return description == that.description && this.compareTo(that) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type, paymentAmount);
    }*/

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
