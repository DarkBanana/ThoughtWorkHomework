package main;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static constant.Constant.*;

/**
 * 出租车实体类
 */
public class Taxi {
    private String carNumber;/* 车牌号 */
    private Date purchaseTime;/* 购买日期 */
    private String brand;/* 品牌名称 */
    private int mileage;/* 行驶公里数 */
    private boolean overHaul;/* 有无大修 */

    private boolean isWriteOff(Date submitDate) {
        return submitDate.compareTo(getWriteOffDate()) >= 0;
    }

    boolean isBelongToDistanceRelatedNotice(Date submitDate) {
        int mileageAfterLastMaintenance = getMileage() % MAINTENANCE_PER_KILOMETER;
        return !isBelongToWriteOffNotice(submitDate) && mileageAfterLastMaintenance == 0 || mileageAfterLastMaintenance >= NOTICE_KILOMETER;
    }

    private Date getMaintenanceNoticeDate(Date submitDate) {
        int cycle;
        boolean moreThan3Years = DateUtils.truncatedCompareTo(DateUtils.addYears(getPurchaseTime(), BOUNDARY_YEAR), submitDate, Calendar.YEAR) <= 0;
        if (isOverHaul()) {
            cycle = OVERHAUL_CYCLE_MONTH;
        } else if (moreThan3Years) {
            cycle = MORE_THAN_BOUNDARY_YEAR_CYCLE_MONTH;
        } else {
            cycle = LESS_THAN_BOUNDARY_YEAR_CYCLE_MONTH;
        }
        Date noticeDate = getPurchaseTime();
        while (noticeDate.compareTo(submitDate) <= 0) {
            noticeDate = DateUtils.addMonths(noticeDate, cycle);
        }
        noticeDate = DateUtils.addMonths(noticeDate, FORWARD_ONE_MONTH);
        noticeDate = DateUtils.setDays(noticeDate, FIRST_DAY);
        return noticeDate;
    }

    boolean isBelongToTimeRelatedNotice(Date submitDate) {
        if (isWriteOff(submitDate) || isBelongToWriteOffNotice(submitDate) || isBelongToDistanceRelatedNotice(submitDate)) {
            return false;
        }
        Date noticeDate = getMaintenanceNoticeDate(submitDate);

        return noticeDate.compareTo(submitDate) <= 0;
    }

    boolean isBelongToWriteOffNotice(Date submitDate) {
        if (isWriteOff(submitDate)) {
            return false;
        }
        Date noticeDate = DateUtils.setDays(DateUtils.addMonths(getWriteOffDate(), FORWARD_ONE_MONTH), FIRST_DAY);//提醒时间设置为报废时间前一个月的1号
        return noticeDate.compareTo(submitDate) <= 0;
    }

    private Date getWriteOffDate() {
        if (isOverHaul()) {
            return DateUtils.addDays(getPurchaseTime(), OVERHAUL_WRITE_OFF_DAY);
        } else {
            return DateUtils.addDays(getPurchaseTime(), NOT_OVERHAUL_WRITE_OFF_DAY);
        }
    }

    String getCarNumber() {
        return carNumber;
    }

    void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    private Date getPurchaseTime() {
        return purchaseTime;
    }

    void setPurchaseTime(String formattedDateString) {
        try {
            purchaseTime = new SimpleDateFormat("yyyy/MM/dd").parse(formattedDateString);
        } catch (ParseException e) {
            System.out.println("时间格式不正确！");
        }
    }

    private int getMileage() {
        return mileage;
    }

    void setMileage(int mileage) {
        this.mileage = mileage;
    }

    String getBrand() {
        return brand;
    }

    void setBrand(String brand) {
        this.brand = brand;
    }

    private boolean isOverHaul() {
        return overHaul;
    }

    void setOverHaul(boolean overHaul) {
        this.overHaul = overHaul;
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
