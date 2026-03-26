package io.github.hyperliquid.sdk.model.order;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Order group, containing order list and grouping type information.
 * <p>
 * Used to automatically infer the grouping parameter of bulkOrders, simplifying API calls.
 * </p>
 */
@Getter
@ToString
@EqualsAndHashCode
public class OrderGroup {
    /**
     * Order list
     */
    private final List<OrderRequest> orders;

    /**
     * Grouping type
     */
    private final GroupingType groupingType;

    public OrderGroup(List<OrderRequest> orders, GroupingType groupingType) {
        this.orders = orders;
        this.groupingType = groupingType;
    }
}
