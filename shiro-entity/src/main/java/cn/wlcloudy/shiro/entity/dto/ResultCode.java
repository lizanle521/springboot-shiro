package cn.wlcloudy.shiro.entity.dto;

public enum ResultCode {

    SUCCESS(0, "success"),
    FAILURE(1, "failure"),
    PARAM_CHECK_FAILURE(10001, "failure"),

    USER_NOT_EXISIT(20001, "User not exisits."),
    NOT_FOUND(20002, "Entity not found."),
    DELETE_FAILURE(20003, "Failed to delete entity."),
    ID_EXISTS(20004, "Entity id has already exists."),
    CREATE_FAILURE(20005, "Failed to create entity."),
    INVALID_ID(20006, "Invalid id."),
    UPDATE_FAILURE(20007, "Failed to update entity"),

    UNDERCARRIAGE_FAILURE(20008, "Failed to undercarriage product."),

    INVALID_FILE_TYPE(20009, "Invalid file type"),
    FAIL_PARSE_FILE(20010, "Failed to parse file,invalid file type"),
    BACK_ERROR(20011, "不可以撤回"),
    APPLY_ERROR(20012, "申请表不存在"),
    TASK_ERROR(20013, "任务不存在."),
    AWARD_ERROR(20014,"奖励积分不存在"),
    AUCTION_ERROR(20015,"竞拍信息不存在"),
    PRICE_ERROR(20016,"该价格已经被人竞拍了，请重新出价"),
    AUCITON_ERROR(20017,"竞拍信息不存在"),
    PRODUCT_ERROR(20018,"产品不存在"),
    AUCITON_RECORD_ERROR(20019,"无人参加竞拍"),


    // 用户登录认证错误码
    UNKNOWN_ACCOUNT(20020,"账户不存在"),
    INCORRECT_CREDENTIALS(20021,"用户名或密码不正确"),
    LOCKED_ACCOUNT(20022,"账户被锁定"),
    EXCESSIVE_ATTEMPTS(20023,"密码错误次数过多"),
    LOGIN_FAIL(20024,"未知错误，登录失败"),
    UNAUTHORIZED(20025,"未登录"),
    FORBIDDEN(20026,"无访问权限"),

    SERVER_ERROR(500, "Internal Server Error");


    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code() {
        return code;
    }

    public String msg() {
        return msg;
    }

}