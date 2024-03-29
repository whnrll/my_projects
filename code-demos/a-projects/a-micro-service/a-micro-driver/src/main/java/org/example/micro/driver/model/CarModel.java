package org.example.micro.driver.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/***
 * 车型
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarModel  implements Serializable {

    //车型  0  快车   1   专车  2  出租车   3  六座商务车
    private Integer carNo;
    //车型描述
    private String name;
}
