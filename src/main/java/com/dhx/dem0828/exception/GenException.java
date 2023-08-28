package com.dhx.dem0828.exception;

import lombok.Data;

/**
 * @author adorabled4
 * @className GenChartException
 * @date : 2023/08/28/ 16:38
 **/
@Data
public class GenException extends RuntimeException{

    private int code=500;

    private String message="error";

    private String description;
}
