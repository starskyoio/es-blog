package io.starskyoio.esblog.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Long cost;
    private Object data;

    public static Result ok(Long cost, Object data) {
        return new Result(cost, data);
    }
}
