package main;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 出租车实体类
 */
@SuppressWarnings("unchecked")
public class Taxi {
    private String carNumber;/* 车牌号 */
    private Date purchaseTime;/* 购买日期 */
    private String brand;/* 品牌名称 */
    private int mileage;/* 行驶公里数 */
    private boolean overHaul;/* 有无大修 */

    public boolean isWriteOff(Date submitDate) {
        return submitDate.compareTo(getWriteOffDate()) >= 0;
    }

    public boolean isBelongToDistanceRelatedNotice(Date submitDate) {
        int mileageAfterLastMaintenance = getMileage() % 10000;
        return !isBelongToWriteOffNotice(submitDate) && mileageAfterLastMaintenance == 0 || mileageAfterLastMaintenance >= 9500;
    }

    public Date getMaintenanceNoticeDate(Date submitDate) {
        int cycle;//保养周期，单位为月
        boolean moreThan3Years = DateUtils.truncatedCompareTo(DateUtils.addYears(getPurchaseTime(), 3), submitDate, Calendar.YEAR) <= 0;
        if (isOverHaul()) {
            cycle = 3;
        } else if (moreThan3Years) {
            cycle = 6;
        } else {
            cycle = 12;
        }
        Date noticeDate = getPurchaseTime();
        while (noticeDate.compareTo(submitDate) <= 0) {
            noticeDate = DateUtils.addMonths(noticeDate, cycle);
        }
        noticeDate = DateUtils.addMonths(noticeDate, -1);//提前一个月提醒
        noticeDate = DateUtils.setDays(noticeDate, 1);
        return noticeDate;
    }

    public boolean isBelongToTimeRelatedNotice(Date submitDate) {
        if (isWriteOff(submitDate) || isBelongToWriteOffNotice(submitDate) || isBelongToDistanceRelatedNotice(submitDate)) {
            return false;
        }
        Date noticeDate = getMaintenanceNoticeDate(submitDate);

        return noticeDate.compareTo(submitDate) <= 0;
    }

    public boolean isBelongToWriteOffNotice(Date submitDate) {
        if (isWriteOff(submitDate)) {
            return false;
        }
        Date noticeDate = DateUtils.setDays(DateUtils.addMonths(getWriteOffDate(), -1), 1);//提醒时间设置为报废时间前一个月的1号
        return noticeDate.compareTo(submitDate) <= 0;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String formattedDateString) {
        try {
            purchaseTime = new SimpleDateFormat("yyyy/MM/dd").parse(formattedDateString);
        } catch (ParseException e) {
            System.out.println("时间格式不正确！");
        }
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isOverHaul() {
        return overHaul;
    }

    public void setOverHaul(boolean overHaul) {
        this.overHaul = overHaul;
    }

    public Date getWriteOffDate() {
        if (isOverHaul()) {
            return DateUtils.addDays(getPurchaseTime(), 3 * 365);
        } else {
            return DateUtils.addDays(getPurchaseTime(), 6 * 365);
        }
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "carNumber='" + carNumber + '\'' +
                ", purchaseTime=" + purchaseTime +
                ", mileage=" + mileage +
                ", brand='" + brand + '\'' +
                ", overHaul=" + overHaul +
                '}';
    }

}
