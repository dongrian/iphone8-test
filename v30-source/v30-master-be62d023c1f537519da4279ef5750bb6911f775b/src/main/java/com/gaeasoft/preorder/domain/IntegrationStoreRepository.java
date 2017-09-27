package com.gaeasoft.preorder.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IntegrationStoreRepository extends JpaRepository<IntegrationStore, Long> {

    List<IntegrationStore> findByAddress1AndAddress2AndExperience(String address1, String address2, boolean experience);
    List<IntegrationStore> findByAddress1AndAddress2(String address1, String address2);
    
    @Query("select s.address1 as address1 "
    		+ "from IntegrationStore s group by s.address1")
    List<String> findGroupByAddress1();
    
    @Query("select s.address1 as address1 "
            + "from IntegrationStore s where s.experience is true group by s.address1")
    List<String> findGroupByExAddress1();
    
    @Query("select s.address2 as address2 "
    		+ "from IntegrationStore s where s.address1 = ?1 group by s.address2")
    List<String> findGroupByAddress2(String address1);
    
    @Query("select s.address2 as address2 "
            + "from IntegrationStore s where s.address1 = ?1 and s.experience is true group by s.address2")
    List<String> findGroupByExAddress2(String address1);
    
    @Query("select s "
    		+ "from IntegrationStore s where s.dlrNm like %?1%")
    List<IntegrationStore> findByKeyword(String keyword);
    
    @Query("select s "
            + "from IntegrationStore s where s.dlrNm like %?1% and s.experience is true")
    List<IntegrationStore> findByExKeyword(String keyword);
    
    List<IntegrationStore> findByOrgCdAndExperience(String orgCd, boolean experience);
    
    List<IntegrationStore> findByOrgCd(String orgCd);
}
