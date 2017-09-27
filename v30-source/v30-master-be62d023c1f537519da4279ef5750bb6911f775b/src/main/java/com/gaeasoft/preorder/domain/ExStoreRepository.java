package com.gaeasoft.preorder.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExStoreRepository extends JpaRepository<ExStore, Long> {

    List<ExStore> findByAddress1AndAddress2(String address1, String address2);
    
    @Query("select s.address1 as address1 "
    		+ "from ExStore s group by s.address1")
    List<String> findGroupByAddress1();
    
    @Query("select s.address2 as address2 "
    		+ "from ExStore s where s.address1 = ?1 group by s.address2")
    List<String> findGroupByAddress2(String address1);
    
    @Query("select s "
    		+ "from ExStore s where s.dlrNm like %?1%")
    List<ExStore> findByKeyword(String keyword);

}