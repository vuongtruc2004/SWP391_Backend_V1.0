package com.helper;

import com.dto.response.OrderResponse;
import com.entity.OrderDetailsEntity;
import com.entity.OrderEntity;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceHelper {
    private final ModelMapper modelMapper;

    public OrderServiceHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Specification<OrderEntity> filterByPrice(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            // Tạo subquery để tính tổng price của OrderDetails theo từng OrderEntity
            Subquery<Double> subquery = query.subquery(Double.class);
            Root<OrderDetailsEntity> orderDetailsRoot = subquery.from(OrderDetailsEntity.class);

            // Điều kiện JOIN giữa OrderEntity và OrderDetailsEntity
            subquery.select(criteriaBuilder.sum(orderDetailsRoot.get("price")))
                    .where(criteriaBuilder.equal(orderDetailsRoot.get("order"), root));

            // Điều kiện lọc tổng price nằm trong khoảng minPrice - maxPrice
            if (minPrice == null && maxPrice != null) {
                return criteriaBuilder.lessThanOrEqualTo(subquery, maxPrice);
            } else if (minPrice != null && maxPrice == null) {
                return criteriaBuilder.greaterThanOrEqualTo(subquery, minPrice);
            } else {
                return criteriaBuilder.between(subquery, minPrice, maxPrice);
            }
        };
    }

    public OrderResponse convertToOrderResponse(OrderEntity orderEntity) {
        OrderResponse orderResponse = modelMapper.map(orderEntity, OrderResponse.class);
        orderResponse.setUserId(orderEntity.getUser().getUserId());
        return orderResponse;
    }
}
