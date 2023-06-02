package borysenko.examples.rickandmorty.service;

import org.springframework.data.domain.PageRequest;

public interface PageRequestService {
    PageRequest createPageRequest(Integer page, Integer count, String sortBy);
}
