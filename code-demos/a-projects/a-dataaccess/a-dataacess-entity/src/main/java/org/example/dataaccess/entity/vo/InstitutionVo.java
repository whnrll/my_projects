package org.example.dataaccess.entity.vo;

import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import org.example.dataaccess.entity.base.BaseEntity;

/**
 * 
 * t_institution
 *
 */
@Entity
@Table(name="t_institution")
@Data
public class InstitutionVo extends BaseEntity {

    /**i
     * 机构类型id
     */
    private String institutionTypeId;

    /**
     * 机构关联id
     */
    private Long detailInstitutionId;

    /**
     * 机构关联名称
     */
    private String detailInstitutionName;

    /**
     */
    private static final long serialVersionUID = 1L;

}