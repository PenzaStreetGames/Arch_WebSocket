package com.penzastreet.spring_web_socket.repositories

import com.penzastreet.spring_web_socket.models.KeyValue
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface KeyValueRepository : JpaRepository<KeyValue, String> {
    @Query("select k from key_value k")
    fun getAll(): List<KeyValue>

    fun getByKey(key: String): KeyValue?

    @Modifying
    @Transactional
    @Query("delete from key_value k where k.key = ?1")
    fun deleteByKey(key: String)
}