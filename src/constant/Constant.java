package constant;

public class Constant {
    /* 车辆报废时间 */
    public static final int OVERHAUL_WRITE_OFF_DAY = 3 * 365;
    public static final int NOT_OVERHAUL_WRITE_OFF_DAY = 6 * 365;

    /* 定时保养相关 */
    public static final int BOUNDARY_YEAR = 3;
    public static final int OVERHAUL_CYCLE_MONTH = 3;
    public static final int MORE_THAN_THREE_YEARS_CYCLE_MONTH = 6;
    public static final int LESS_THAN_THREE_YEARS_CYCLE_MONTH = 12;

    /* 行驶里程保养 */
    public static final int MAINTENANCE_PER_KILOMETER = 10000;
    public static final int NOTICE_KILOMETER = 9500;

    /* 提醒相关 */
    public static final int FORWARD_ONE_MONTH = -1;
    public static final int FIRST_DAY = 1;

}
