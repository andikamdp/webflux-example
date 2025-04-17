package id.co.practice.webflux.dao;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Dao<D, I> {

    Mono<D> save(D data);

    Mono<D> findById(I id);

    Flux<D> findAllPaged(Pageable pageable);

    Mono<D> update(I id, D data);

    Mono<Void> delete(I id);

}
