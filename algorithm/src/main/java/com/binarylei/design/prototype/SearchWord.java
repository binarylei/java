package com.binarylei.design.prototype;

import lombok.Data;

/**
 * @author binarylei
 * @version 2020-03-01
 */
@Data
public class SearchWord {

    private String keyword;
    private long lastUpdateTime;
    private long count;
}
