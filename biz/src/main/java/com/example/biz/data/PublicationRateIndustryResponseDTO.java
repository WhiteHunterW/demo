package com.example.biz.data;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wenzeng
 * @date 2024/10/9
 */
@Data
public class PublicationRateIndustryResponseDTO extends BaseReponse{

    /**
     * 新产品线：A0:智慧屏，B0:LCD，C0:电梯海报
     */
    private String newProductLine;
    /**
     * 新产品线：A0:智慧屏，B0:LCD，C0:电梯海报
     */
    private String newProductLineDesc;
    /**
     * 产品线子集：10：社区网智能屏，11，社区网LCD,20：商务网LCD，21：商务网智能屏, 3:电梯海报, 0000：全部
     */
    private String newProductLineSub;
    /**
     * 产品线子集：10：社区网智能屏，11，社区网LCD,20：商务网LCD，21：商务网智能屏, 3:电梯海报, 0000：全部
     */
    private String newProductLineSubDesc;

    /**
     * 行业code
     */
    private String industryCode;

    /**
     * 行业名称
     */
    private String industryName;
}
