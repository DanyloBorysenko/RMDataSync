package borysenko.examples.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageRequestServiceImpl implements PageRequestService {
    private static final int DIRECTION_INDEX = 1;
    private static final int FIELD_INDEX = 0;

    @Override
    public PageRequest createPageRequest(Integer page, Integer count, String sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy.contains(":")) {
            String[] sortParts = sortBy.split(";");
            for (String sortPart : sortParts) {
                Sort.Order order;
                if (sortPart.contains(":")) {
                    String[] fieldAndDirection = sortPart.split(":");
                    order = new Sort.Order(
                            Sort.Direction.valueOf(fieldAndDirection[DIRECTION_INDEX]),
                            fieldAndDirection[FIELD_INDEX]);
                } else {
                    order = new Sort.Order(Sort.Direction.ASC, sortPart);
                }
                orders.add(order);
            }
        } else {
            Sort.Order order = new Sort.Order(Sort.Direction.ASC, sortBy);
            orders.add(order);
        }
        Sort sort = Sort.by(orders);
        return PageRequest.of(page, count, sort);
    }
}
