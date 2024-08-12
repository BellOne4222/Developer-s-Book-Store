package com.shop.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.shop.shop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>{

	List<Item> findByItemNm(String itemNm); // 상품 조회

	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail); // 상품명, 상품 상셋 설명 OR 조건 조회

	List<Item> findByPriceLessThan(Integer price); // price 이하의 상품 데이터 조회

	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price); // 내림차순 정렬 조회

	@Query("select i from Item i where i.itemDetail like " +
		"%:itemDetail% order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); // JPQL

	@Query(value="select * from item i where i.item_detail like " +
		"%:itemDetail% order by i.price desc", nativeQuery = true)
	List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail); // Native Query


}
