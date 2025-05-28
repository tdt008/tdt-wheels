package com.tdt.boot.elasticsearch.domain;

import com.tdt.boot.elasticsearch.annotation.EsDocumnetId;
import com.tdt.boot.elasticsearch.annotation.EsIndex;
import com.tdt.boot.elasticsearch.constant.IndexNameConstant;
import com.tdt.boot.elasticsearch.protocol.DepositOrderDetailV2Protocol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description：定金单
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EsIndex(indexName = IndexNameConstant.ORDER_DEPOSIT_ORDER)
public class DepositOrderEntity {
    /**
     * id
     */
    @EsDocumnetId
    private Long id;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 会员姓名
     */
    private String memberName;
    /**
     * 会员卡号（电话）
     */
    private String memberPhone;
    /**
     * 会员等级
     */
    private String memberLevel;
    /**
     * 会员头像
     */
    private String memberImageUrl;

    /**
     * 会员身份
     */
    private Integer memberIdentity;
    /**
     * 下单门店id
     */
    private Long storeId;
    /**
     * 下单门店name
     */
    private String storeName;
    /**
     * 定金单编号
     */
    private String code;
    /**
     * 状态  1待结算  2待还款 3已关闭  4已结清(全款) 5已结清(已退款)   6已结清(已过期)   7已结清(已改物料)
     */
    private Integer status;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 已付总额
     */
    private BigDecimal payTotalAmount;
    /**
     * 有效期
     */
    private Long expirationTime;
    /**
     * 是否申请延期
     */
    private Boolean isDelayed;
    /**
     * 录单时间
     */
    private Long gmtInput;
    /**
     * 配送方式 1自提;2送货上门
     */
    private Integer deliverType;
    /**
     * 收货地址id
     */
    private Long deliverAddrId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private Long createUser;
    /**
     * create_user_name
     */
    private String createUserName;
    /**
     * 购买条目
     */
    private DepositOrderGoodsEntity depositOrderGoods;

    /**
     * 还款记录
     */
    private List<DepositRepaymentHistoryEntity> repaymentHistoryList;

    private Long gmtCreate;

    private Long gmtModified;

    /**
     * 销售单关闭类型：
     * 1、待结算订单12小时未支付，系统自动关闭
     * 2、待结算订单，手工取消订单
     * 3、已支付的订单整单退款
     */
    private Integer closeType;

    /**
     * 发票状态:
     * 0未录入发票信息（录单时不选择开票）
     * 1已经录入发票信息(待审核)
     * 2审核不通过
     * 3开票中（审核通过,待开票）
     * 4开票完成（已开票）
     */
    private Integer invoiceStatus;

    /**
     * 商品数据来源：0：iPad  1：小程序 2: 有赞
     */
    private Integer dataSource;

    /**
     * 运费金额
     */
    private BigDecimal freightAmount;
    /**
     * 关闭时间
     */
    private Long gmtClose;
    /**
     * 完成时间
     */
    private Long gmtFinish;
    /**
     * 退款时间
     */
    private Long gmtRefund;
    /**
     * 已经退款总金额
     */
    private BigDecimal totalRefundAmount;
    /**
     * 订单创建后超时关闭的时间
     */
    private Long gmtExpire;
    /**
     * 用户订单是否已经删除
     */
    private Boolean memberDeleted;
    /**
     * 是否补录
     */
    private Boolean isAdditional;


    public static DepositOrderEntity buildDepositOrder(DepositOrderDetailV2Protocol param) {

        DepositOrderGoodsEntity depositOrderGoods = DepositOrderGoodsEntity.buildDepositOrderGoodsDTO(param.getPurchaseGoodsItem());
        List<DepositRepaymentHistoryEntity> repaymentHistoryList = null;
        if (param.getRepaymentHistoryList() != null && param.getRepaymentHistoryList().size() > 0) {
            repaymentHistoryList = param.getRepaymentHistoryList().stream()
                    .map(it -> DepositRepaymentHistoryEntity.buildDepositRepaymentHistory(it, depositOrderGoods)).collect(Collectors.toList());
        }

        return DepositOrderEntity.builder()
                .id(param.getId())
                .memberId(param.getMemberId())
                .memberName(param.getMemberName())
                .memberPhone(param.getMemberPhone())
                .memberLevel(param.getMemberLevel())
                .code(param.getCode())
                .storeId(param.getStoreId())
                .storeName(param.getStoreName())
                .totalAmount(param.getTotalAmount())
                .gmtInput(param.getGmtInput() != null ? param.getGmtInput().getTime() : null)
                .status(param.getStatus())
                .remark(param.getRemark())
                .createUser(param.getCreateUser())
                .createUserName(param.getCreateUserName())
                .gmtCreate(param.getGmtCreate() != null ? param.getGmtCreate().getTime() : System.currentTimeMillis())
                .gmtModified(param.getGmtModified() != null ? param.getGmtModified().getTime() : null)
                .payTotalAmount(param.getPayTotalAmount())
                .expirationTime(param.getExpirationTime() != null ? param.getExpirationTime().getTime() : null)
                .isDelayed(param.getIsDelayed() != null && param.getIsDelayed() == 1)
                .depositOrderGoods(depositOrderGoods)
                .deliverType(param.getDeliverType())
                .deliverAddrId(param.getDeliverAddrId())
                .repaymentHistoryList(repaymentHistoryList)
                .invoiceStatus(param.getInvoiceStatus())
                .memberImageUrl(param.getMemberAvatar())
                .memberIdentity(param.getMemberIdentity())
                //V2添加字段
                .dataSource(param.getDataSource())
                .freightAmount(param.getFreightAmount())
                .gmtClose(param.getGmtClose() != null ? param.getGmtClose().getTime() : null)
                .gmtFinish(param.getGmtFinish() != null ? param.getGmtFinish().getTime() : null)
                .gmtRefund(param.getGmtRefund() != null ? param.getGmtRefund().getTime() : null)
                .totalRefundAmount(param.getTotalRefundAmount())
                .gmtExpire(param.getGmtExpire() != null ? param.getGmtExpire().getTime() : null)
                .memberDeleted(param.getMemberDeleted() != null && param.getMemberDeleted() == 1)
                .isAdditional(param.getIsAdditional() != null && param.getIsAdditional() == 1)
                .build();
    }
}
