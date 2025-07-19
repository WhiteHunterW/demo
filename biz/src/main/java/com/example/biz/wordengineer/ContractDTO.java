package com.example.biz.wordengineer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wenzeng
 * @date 2025/6/24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContractDTO {

    private String contractNo;

    private String city;

    private String signName;

    private String settlementType;

    private String statisDate;

    private String premisesName;

    private String settlementAmount;
}
