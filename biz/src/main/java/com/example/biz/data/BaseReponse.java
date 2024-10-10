package com.example.biz.data;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wenzeng
 * @date 2024/10/9
 */
@Data
public class BaseReponse {

    /**
     * 经营周ID
     */
    private String jWeekId;
    /**
     * 经营月ID
     */
    private String jMonthId;

    /**
     * 现金预锁上刊率分子
     */
    private BigDecimal xjProlockRateFz;
    /**
     * 现金上刊率分子
     */
    private BigDecimal xjRateFz;
    /**
     * 综合上刊率分子
     */
    private BigDecimal allRateFz;
    /**
     * 上刊率分母
     */
    private BigDecimal skFenmu;

}
